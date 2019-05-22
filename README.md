# kafka-producer-consumer
kafka project

Projeto desenvolvimento para complementar o workshop de Event Driven e Mensageria - Paula Macedo e Camila Nachbar


------------------------- KAFKA-----------------------------------

Dev possuir a vers

***Start/Stop ServidorZooKeeper
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
bin/windows/zookeeper-server-stop.bat config/zookeeper.properties

***Start/Stop Servidor Kafka
bin/windows/kafka-server-start.bat config/server.properties
bin/windows/kafka-server-stop.bat config/server.properties

***criar um tópico
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

***Ver um tópico
bin/windows/kafka-topics.bat --list --bootstrap-server localhost:9092

***enviar mensagem
bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic test
digite as mensagens seguidas de enter para enviar.

***iniciar um consumidor
bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning


***configurar um cluster de vários agentes***

Inicie Zookeeper
bin/windows/zookeeper-server-start.bat config/zookeeper.properties

Crie a quantidade de Server.properties necessários e configure-os.
copy config/server.properties config/server-1.properties
copy config/server.properties config/server-2.properties

Agora edite esses novos arquivos e defina as seguintes propriedades:
config/server-1.properties:
    broker.id=1
    listeners=PLAINTEXT://:9093
    log.dirs=/tmp/kafka-logs-1
 
config/server-2.properties:
    broker.id=2
    listeners=PLAINTEXT://:9094
    log.dirs=/tmp/kafka-logs-2

Inicie os nós, por exemplo:
bin/windows/kafka-server-start.bat config/server.properties
bin/windows/kafka-server-start.bat config/server-1.properties
bin/windows/kafka-server-start.bat config/server-2.properties

Crie um tópico com fator de replicação de três:
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic

Verifique qual corretor está fazendo o que?
bin/windows/kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic my-replicated-topic

Topic:my-replicated-topic   PartitionCount:1    ReplicationFactor:3 Configs:
    Topic: my-replicated-topic  Partition: 0    Leader: 1   Replicas: 1,2,0 Isr: 1,2,0

Leader: É o nó responsável por todas as leituras e gravações da partição fornecida.
		Cada nó será o líder de uma parte selecionada aleatoriamente das partições.
		
Réplicas: É a lista de nós que replicam o log dessa partição, independentemente de 
		  serem os líderes ou até mesmo se estão atualmente ativos.

isr: É o conjunto de réplicas "in-sync". Este é o subconjunto da lista de réplicas 
	 que está atualmente ativa e alcançada pelo líder.
	 
	 bin/windows/connect-standalone.bat config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties

Topico de Exemplo de Entrada
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-plaintext-input Created topic "streams-plaintext-input".

Topico de Exemplo de Saida
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact  Created topic "streams-wordcount-output".

Esses comandos a seguir inicia o aplicativo de demonstração do WordCount:
bin/windows/kafka-run-class.bat org.apache.kafka.streams.examples.wordcount.WordCountDemo

Esses comandos a seguir inicia o produtor
bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic streams-plaintext-input

Esses comandos a seguir inicia o consumidor
bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 \
    --topic streams-wordcount-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

Esses comandos a seguir inicia o produtor com mensagem
bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic streams-plaintext-input all streams lead to kafka

bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 \
    --topic streams-wordcount-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
 

