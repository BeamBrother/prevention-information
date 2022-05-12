package com.bl.ep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.bl.ep.mapper")
@SpringBootApplication
@EnableCaching
public class EpidemicPreventionInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpidemicPreventionInformationApplication.class, args);
    }

}
