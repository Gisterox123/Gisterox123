package com.msb.caweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CaWeb2Application {

	public static void main(String[] args) {
		SpringApplication.run(CaWeb2Application.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		return "hello nginx";
	}
}
