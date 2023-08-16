package com.kinghk.web.controller;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMq")
    public String sendMQ(@RequestParam(value = "num",defaultValue = "10")Integer num){
        for(int i=0;i<num;i++){
            if(i%2==0){
                Map map = new HashMap<>();
                map.put("name","张三"+i);
                map.put("age","26");
                map.put("sex","男");

                rabbitTemplate.convertAndSend("hello-java-exchange","hello.java",map,new CorrelationData());
            }else{
                Map map = new HashMap<>();
                map.put("str", UUID.randomUUID().toString());

                rabbitTemplate.convertAndSend("hello-java-exchange","hello2.java",map,new CorrelationData());
            }
        }
        return "ok";
    }

}
