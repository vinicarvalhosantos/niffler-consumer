package br.com.vinicius.santos.nifflerconsumer;

import br.com.vinicius.santos.nifflerlib.NifflerLibApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableRabbit
//@ComponentScan({"br.com.vinicius.santos.nifflerlib"})
@EntityScan({"br.com.vinicius.santos.nifflerlib"})
@EnableJpaRepositories({"br.com.vinicius.santos.nifflerlib"})
@NifflerLibApplication
public class NifflerConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NifflerConsumerApplication.class, args);
    }

}
