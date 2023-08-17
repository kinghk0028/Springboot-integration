package com.kinghk.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Slf4j
public class ConsumerDemo {

    /**
     * 消费者消费消息
     * 监听test-topic、test主题消息
     * @param consumerRecord
     */
    @KafkaListener(topics = {"test-topic","test"})
    public void consumerMsg(ConsumerRecord<?,?> consumerRecord){
        log.info("{}{}{}","接收到来自主题["+consumerRecord.topic(),"],key为"+consumerRecord.key(),"的消息==>>"+consumerRecord.value());
    }
}
