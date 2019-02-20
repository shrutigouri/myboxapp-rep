package com.myboxapplication.myboxapp;

import com.myboxapplication.myboxapp.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({FileStorageProperties.class})
public class MyboxappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyboxappApplication.class, args);
	}

}

