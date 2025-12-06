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
                repository.save(new Client(null, "Amine", 22));
                repository.save(new Client(null, "Sara", 30));
                repository.save(new Client(null, "Youssef", 19));
            }
            repository.findAll().forEach(c -> System.out.println("Client: " + c.getNom() + " - " + c.getAge()));
        };
    }
}
