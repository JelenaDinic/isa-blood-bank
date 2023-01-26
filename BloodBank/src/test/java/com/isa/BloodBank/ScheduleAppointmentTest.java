package com.isa.BloodBank;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleAppointmentTest {
    @Autowired
    private AppointmentService service;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

            ExecutorService executor = Executors.newFixedThreadPool(2);
            Future<?> future1 = executor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Startovan Thread 1");
                    Appointment appointment = service.findById(2020);
                    appointment.setStatus(AppointmentStatus.IN_FUTURE);
                    try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                    service.save(appointment);

                }
            });
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Startovan Thread 2");
                    Appointment appointment = service.findById(2020);
                    appointment.setStatus(AppointmentStatus.IN_FUTURE);
                    service.save(appointment);
                }
            });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

}
