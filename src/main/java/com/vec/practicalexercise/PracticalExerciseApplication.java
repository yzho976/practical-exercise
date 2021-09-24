package com.vec.practicalexercise;

import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vec.practicalexercise.mapper")
public class PracticalExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticalExerciseApplication.class, args);
    }

}
