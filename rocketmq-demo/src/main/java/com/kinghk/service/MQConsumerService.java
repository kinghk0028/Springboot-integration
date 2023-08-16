package com.kinghk.service;

import com.alibaba.fastjson.JSON;
import com.kinghk.model.Message;
import com.kinghk.model.User;
import com.kinghk.util.RedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MQConsumerService {

    // topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
    // selectorExpression的意思指的就是tag，默认为“*”，不设置的话会监听所有消息
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag1", consumerGroup = "Con_Group_One")
    public class ConsumerSend implements RocketMQListener<User> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(User user) {

            log.info("监听到消息：user={}", JSON.toJSONString(user));
            try {
                WebSocketServer.sendInfo(JSON.toJSONString(user), "100");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 注意：这个ConsumerSend2和上面ConsumerSend在没有添加tag做区分时，不能共存，
    // 不然生产者发送一条消息，这两个都会去消费，如果类型不同会有一个报错，所以实际运用中最好加上tag，写这只是让你看知道就行
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", consumerGroup = "Con_Group_Two")
    public class ConsumerSend2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String str) {
            log.info("监听到消息：str={}", str);
        }
    }

    // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag2", consumerGroup = "Con_Group_Three")
    public class Consumer implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            byte[] body = messageExt.getBody();
            String msg = new String(body);
            log.info("监听到消息：msg={}", msg);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "MSG_TPIOC_1", selectorExpression = "tag1", consumerGroup = "Con_Group_four")
    public class ConsumerSendMessage implements RocketMQListener<Message> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(Message message) {
            RedissonClient redissonClient = RedissonUtil.config();
            log.info("监听到消息：user={}", JSON.toJSONString(message));
            RBucket<Message> rBucket =  redissonClient.getBucket("objKey"+message.getNumber());
            // 设置value和key的有效期
            rBucket.set(message, 30, TimeUnit.SECONDS);
            // 通过key获取value
            System.out.println(redissonClient.getBucket("objKey"+message.getNumber()).get());
            //关闭客户端
            redissonClient.shutdown();
        }
    }

}