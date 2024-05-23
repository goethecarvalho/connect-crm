package br.com.connect.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConnectCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectCrmApplication.class, args);
	}

}
