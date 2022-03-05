package edu.njnu.qaserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.njnu.qaserver.mapper")
public class QaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaServerApplication.class, args);
	}

}
