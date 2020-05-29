package com.example.momo.application;

import com.example.momo.echo.config.EchoServiceConfig;
import com.example.momo.messages.config.MessageServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackageClasses = {
//		EchoServiceConfig.class,
//		MessageServiceConfig.class
//})
@SpringBootApplication(scanBasePackages = {
		"com.example.momo.echo",
		"com.example.momo.messages"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
