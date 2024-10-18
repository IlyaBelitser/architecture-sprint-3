package ru.yandex.practicum.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.yandex.practicum.device.controller.DeviceController;

import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class DeviceApplication {
    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    public static void main(String[] args) {
        Map<String, String> envVariables = System.getenv();
        envVariables.forEach((key, value) -> log.info(key + ":" + value));
        SpringApplication.run(DeviceApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            log.info("spring.datasource.url = " + environment.getProperty("spring.datasource.url"));
            log.info("spring.datasource.username = " + environment.getProperty("spring.datasource.username"));
            log.info("spring.datasource.password = " + environment.getProperty("spring.datasource.password"));
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
