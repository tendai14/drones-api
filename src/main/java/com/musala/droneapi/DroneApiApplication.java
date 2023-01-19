package com.musala.droneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DroneApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneApiApplication.class, args);

    }

//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.musala.droneapi"))
//                .build();
//    }

}
