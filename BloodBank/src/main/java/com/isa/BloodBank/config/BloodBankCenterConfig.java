package com.isa.BloodBank.config;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class BloodBankCenterConfig {

    @Bean
    CommandLineRunner commandLineRunner(BloodBankCenterRepository repository) {
        return args -> {
            BloodBankCenter center1 = new BloodBankCenter(
                    "CenterOne",
                    "Kopernikova 12",
                    "Opissss",
                    7.8
            );
            BloodBankCenter center2 = new BloodBankCenter(
                    "CenterTwo",
                    "Kopernikova 38",
                    "Opis za drugi centar",
                    9.1
            );
            repository.saveAll(List.of(center1,center2));
        };
    }
}
