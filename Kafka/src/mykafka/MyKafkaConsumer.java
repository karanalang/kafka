package mykafka;

import java.util.*;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.KafkaException;



public class MyKafkaConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//inherits from Hashtable
		Properties props = new Properties();
		props.put("bootstrap.servers","localhost:9092");
		props.put("group.id","mygroup");
		props.put("enable.auto.commit","true");
		props.put("auto.commit.interval.ms","1000");
		props.put("session.timeout.ms","30000");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("test_topic", "test"));
		
		
		try{
			
			while(true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			
			for(ConsumerRecord<String, String> record : records){	
				System.out.printf(" offset = %d , key = %s , value = %s" , record.offset(), 
						record.key(), record.value()  );
				System.out.printf(" \n");
				
				
				}
			
			}
		}
		catch(KafkaException e){
			System.out.println(" There was a KafkaException " + e);
		}
		
		finally{
			consumer.close();
		}
		
		
		
		
		
	}

}
