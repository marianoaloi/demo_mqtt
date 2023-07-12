package br.com.aloi.demo_mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoMqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMqttApplication.class, args);
	}

}
