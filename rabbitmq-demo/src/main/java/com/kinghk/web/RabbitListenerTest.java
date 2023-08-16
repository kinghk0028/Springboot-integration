package com.kinghk.web;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class RabbitListenerTest {

    /**
     * queues：声明需要监听的所有队列
     * @param message
     *
     * 1、Message message：原生消息详细信息、头+体
     * 2、T<发送的消息的类型> Map map
     * 3、Channel channel：当前传输数据的通道
     *
     * Queue：可以很多人都来监听，只要收到消息，队列会删除消息，而且只能有一个收到消息
     */
//    @RabbitListener(queues = {"hello-java-queue"})
    public void revieveMessage(Object message,
                               Map map, Channel channel){
        System.out.println("接收到消息内容："+message+"===>内容："+map.toString());
    }
}
