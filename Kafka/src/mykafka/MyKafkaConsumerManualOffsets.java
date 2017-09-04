package mykafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;

public class MyKafkaConsumerManualOffsets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//inherits from Hashtable
		Properties props = new Properties();
		props.put("bootstrap.servers","localhost:9092");
		props.put("group.id","mygroup");
		props.put("enable.auto.commit","false");
		
		props.put("auto.commit.interval.ms","1000");
		props.put("session.timeout.ms","30000");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("test_topic", "test"));
		
		final int minBatchSize = 2;
		List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
		
		
		try{
			
			while(true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			
			for(ConsumerRecord<String, String> record : records){	
				
				buffer.add(record);
				
				if(buffer.size() >= minBatchSize) {
				
					System.out.println(" Doing something useful with " + buffer.size());
				
					//Manually commit if buffer.size() >= minBatchSize
					consumer.commitSync();
				
					//clearing buffer after sync
					buffer.clear();
					System.out.println("Commited offsets and cleared buffer.");
					
					System.out.printf(" offset = %d , key = %s , value = %s" , record.offset(), 
							record.key(), record.value()  );
					System.out.printf(" \n");
				
				}
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
