package mykafka;

import java.util.*;
//import javax.security.auth.callback.*;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.log4j.net.SyslogAppender;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;


public class MyKafkaProducer {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		final String topic = "confluentTopic";
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "nwk2-bdp-kafka-04.gdcs-qa.apple.com:9092");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		
		System.out.println(" Sending 1 message every 1 second, for a total of 100 messages ");
		
		for (int i =0;i< 100;i++){
			String message = "Message Number : " + i + " at " + new Date();
			String key = "key" + i;
			
			System.out.println(" am here ..");
			
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, message);
			
			System.out.println(" In Producerrecord .. " + record);
			
			producer.send(record, new Callback() {
					public void onCompletion(RecordMetadata metadata, Exception exception){
						
						if(exception == null){
						System.out.println(" Message delivered successfully : " + message);
					}
					else{
						System.out.println(" Message not delivered : " + message + " .Cause : " + exception.getMessage());
					}
				}
			});
	
			System.out.println(" Message sent : " + message);
			Thread.sleep(1000);
		
			}
		
		producer.close();
	}
		
		
		
		
		
	}
