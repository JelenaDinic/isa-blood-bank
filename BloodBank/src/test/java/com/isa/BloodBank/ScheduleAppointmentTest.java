package com.isa.BloodBank;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.FreeExamination;
import com.isa.BloodBank.service.AppointmentService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
class ScheduleAppointmentTest {
    @Autowired
    private AppointmentService service;
    private Appointment appointment;
    @Before
    public void setUp() throws Exception {
        appointment = service.findById(2020);
    }
    @Test
    void contextLoads() {
        ExecutionException thrown = Assertions.assertThrows(ExecutionException.class, () -> {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            Future<?> future1 = executor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Startovan Thread 1");
                    //Appointment appointment = service.findById(2020);
                    try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                    service.codeVerification("kod");// bacice ObjectOptimisticLockingFailureException

                }
            });
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Startovan Thread 2");
                    Appointment appointment = service.findById(2020);
                    //service.changeStatusToPending(appointment);
                    service.codeVerification("kod");
                }
            });
            future1.get();
        });
    }

}
