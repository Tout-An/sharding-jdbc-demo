package com.tout.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {"com.tout.sharding.entity"})
@EnableTransactionManagement
public class ShardingJdbcjpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcjpaDemoApplication.class, args);
    }

}
