package com.kinghk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 生产者发送消息
     * @param msg
     */
    @GetMapping("sendMsg")
    public void sendMsg(@RequestParam(name = "msg",defaultValue = "hello kafka")String msg){
        kafkaTemplate.send("test-topic",msg);
    }

}
