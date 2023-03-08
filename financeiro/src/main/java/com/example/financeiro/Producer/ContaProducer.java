package com.example.financeiro.Producer;

import com.ada.mercado.Compra;
import com.ada.mercado.Conta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContaProducer {

    private final String topicName;

    private final KafkaTemplate<String, Conta> kafkaTemplate;

    public ContaProducer(String topicName, KafkaTemplate<String, Conta> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Conta conta){
        kafkaTemplate.send(topicName, conta).addCallback(
                sucess -> log.info("Mensagem enviada com sucesso!"),
                failure -> log.error("Falha ao Enviar Menssagem!")
        );
    }
}
