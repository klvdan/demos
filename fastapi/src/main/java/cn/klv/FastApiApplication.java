package cn.klv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.klv.mapper")
public class FastApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastApiApplication.class, args);
    }
}
