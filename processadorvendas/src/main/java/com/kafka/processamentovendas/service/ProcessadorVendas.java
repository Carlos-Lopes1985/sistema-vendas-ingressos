package com.kafka.processamentovendas.service;

import com.kafka.processamentovendas.deserializer.VendaDeserializer;
import com.kafka.processamentovendas.model.Venda;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class ProcessadorVendas {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VendaDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try(KafkaConsumer<String,Venda> consumer = new KafkaConsumer<String, Venda>(properties)){

            consumer.subscribe(Arrays.asList("venda-ingressos"));

            while(true){
                ConsumerRecords<String,Venda> vendas = consumer.poll(Duration.ofMillis(200));
                for (ConsumerRecord<String,Venda> record : vendas ) {
                    Venda venda = record.value();

                    if( new Random().nextBoolean()){
                        venda.setStatus("APROVADO");
                    }else{
                        venda.setStatus("REPROVADO");
                    }
                    Thread.sleep(500);
                    System.out.println(venda);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
