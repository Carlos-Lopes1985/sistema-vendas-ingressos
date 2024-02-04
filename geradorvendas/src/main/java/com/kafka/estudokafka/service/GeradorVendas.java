package com.kafka.estudokafka.service;


import com.kafka.estudokafka.model.Venda;
import com.kafka.estudokafka.serializer.VendaSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class GeradorVendas {

    private static Random rand = new Random();
    private static long operacao = 0;

    private static BigDecimal valorIngresso = BigDecimal.valueOf(500);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendaSerializer.class.getName());

        try (org.apache.kafka.clients.producer.KafkaProducer<String, Venda> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties)) {

            while (true){
                Venda venda = gerarVenda();
                ProducerRecord<String, Venda> record = new ProducerRecord<String, Venda>("venda-ingressos", venda);
                producer.send(record);
                Thread.sleep(200);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static Venda gerarVenda() {
        long cliente = rand.nextLong();
        int qtdeIngressos = rand.nextInt();

        return new Venda(operacao++, cliente,qtdeIngressos, valorIngresso.multiply(BigDecimal.valueOf(qtdeIngressos)));
    }
}
