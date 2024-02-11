package com.dyroger.kstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamProcessApplication.class, args);
	}

}
