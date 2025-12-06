package org.example.gestinclient;

import org.example.gestinclient.model.Client;
import org.example.gestinclient.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestinClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestinClientApplication.class, args);
    }


    @Bean
    CommandLineRunner initData(ClientRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Client.builder().nom("Amine").age(22).build());
                repository.save(Client.builder().nom("Sara").age(30).build());
                repository.save(Client.builder().nom("Youssef").age(19).build());
            }
            repository.findAll().forEach(c ->
                    System.out.println("Client: " + c.getNom() + " - " + c.getAge())
            );
        };
    }

}

