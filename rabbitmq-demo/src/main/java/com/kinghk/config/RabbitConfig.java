package com.kinghk.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制RabbitTemplate
     * 1、spring.rabbitmq.publisher-confirms-type=correlated
     *      1、spring.rabbitmq.publisher-confirms=true
     *      2、设置确认回调ConfirmCallback
     * 2、消息正确抵达队列进行回调
     *      1、spring.rabbitmq.publisher-returns=true
     *         spring.rabbitmq.template.mandatory=true
     *      2、设置确认回调ReturnsCallback
     * 3、消费端确认（保证每个消息被正确消费，此时才可以borker删除这个消息）
     *      1、默认是自动确认的，只要消息接收到，客户端会默认确认，服务端就会移除这个消息
     */
    @PostConstruct
    public void initRabbitTemplate(){
        //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             * 1、只要消息抵达Borker就ack=true
             * @param correlationData 当前消息的唯一关联数据，（这个是消息的唯一id）
             * @param ack 消息是否成功收到
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm...correlationData["+correlationData+"]==>"+"["+ack+"]==>"+"["+cause+"]");
            }
        });
        //设置投递消息失败回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            /**
             * Message message, 投递失败的消息详细信息
             * int replyCode, 回复的状态码
             * String replyText, 回复的文本内容
             * String exchange, 当时这个消息发给哪个交换器
             * String routingKey 当时这个消息用哪个路由键
             * @param returnedMessage
             */
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("fail message["+returnedMessage.getMessage()+"]"+"==>["+returnedMessage.getExchange()+"]"+"==>["+returnedMessage.getRoutingKey()+"]");
            }
        });
    }
}
