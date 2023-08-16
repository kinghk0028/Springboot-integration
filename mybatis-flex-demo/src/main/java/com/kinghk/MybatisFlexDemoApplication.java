package com.kinghk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kinghk.mapper")
public class MybatisFlexDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisFlexDemoApplication.class, args);
	}

}
