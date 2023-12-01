package com.backend.PIBack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiBackApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiBackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PiBackApplication.class, args);
        LOGGER.info("Inicializando Sinfonía...");
    }

}
