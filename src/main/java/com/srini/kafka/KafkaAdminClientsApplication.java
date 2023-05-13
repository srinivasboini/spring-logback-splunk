package com.srini.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class KafkaAdminClientsApplication implements CommandLineRunner {

	private final AdminClient adminClient ;

	public static void main(String[] args) {
		SpringApplication.run(KafkaAdminClientsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createTopic("test-topic-3");
		//deleteTopic("test-topic-3");
	}

	void createTopic(String topicName) throws ExecutionException, InterruptedException {
		NewTopic topic = new NewTopic(topicName, 4, (short)3) ;
		adminClient.createTopics(Collections.singletonList(topic)).all().get() ;
		log.info("{} created successfully",topicName);
	}

	void deleteTopic(String topicName) throws ExecutionException, InterruptedException {
		adminClient.deleteTopics(Collections.singletonList(topicName)).all().get() ;
		log.info("{} deleted successfully",topicName);

	}
}
