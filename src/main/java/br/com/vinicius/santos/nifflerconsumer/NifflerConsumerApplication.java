package br.com.vinicius.santos.nifflerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NifflerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NifflerConsumerApplication.class, args);
    }

}
