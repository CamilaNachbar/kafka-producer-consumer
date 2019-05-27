package br.com.devjavagirls.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author Camila Nachbar - camila.nachbar@gmail.com -  17 de mai de 2019
 */
@EnableKafka
@Configuration
public class Receiver {

	private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

	
	//kafka não entende que deve consumir do mesmo topico que enviou, causara erro ao tentar, envie e consuma de topicos diferentes)
	
	@KafkaListener(topics = "${kafka-producer.transacao}")
	public void listen(@Payload String message) {
		LOG.info("received message='{}'", message);
	}

}
