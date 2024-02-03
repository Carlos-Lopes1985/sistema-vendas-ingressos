package com.kafka.estudokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagem")
public class KafkaController {


    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping
    public String producerMensagem(@RequestBody String mensagem){
        return kafkaProducer.producerMensagem(mensagem);
    }

}
