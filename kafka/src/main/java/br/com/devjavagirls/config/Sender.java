package br.com.devjavagirls.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Camila Nachbar -  camila.nachbar@gmail.com-  23 de mai de 2019
 */
@Service
public class Sender {

	private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka-producer.motor}")
	private String topic;

	public void send(String message) {
		LOG.info("sending message='{}' to topic='{}'", message, topic);
		kafkaTemplate.send(topic, message);
	}
}
