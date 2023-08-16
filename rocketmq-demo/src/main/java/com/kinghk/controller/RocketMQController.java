package com.kinghk.controller;

import com.kinghk.model.Message;
import com.kinghk.model.User;
import com.kinghk.service.MQProducerService;
import com.kinghk.util.RedissonUtil;
import com.kinghk.util.SnowFlake;
import org.apache.rocketmq.client.producer.SendResult;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/send")
    public void send() {
        User user = new User();
        SnowFlake snowFlake = new SnowFlake(1L,1L);
        user.setId(String.valueOf(snowFlake.nextId()));
        user.setName("张三");
        user.setAge(15);
        mqProducerService.send(user);
    }
    
    @GetMapping("/sendMsg")
    public void sendMsg() {
        RedissonClient redissonClient = RedissonUtil.config();
        SnowFlake snowFlake = new SnowFlake(1L,1L);
        String snowId = String.valueOf(snowFlake.nextId());

        final String lockKey = snowId;
        RLock rLock = redissonClient.getLock(lockKey);

        try {
            //尝试5秒内获取锁，如果获取到了，最长60秒自动释放
            boolean res = rLock.tryLock(5L, 60L, TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
                System.out.println("获取锁成功");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String datetime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
                Message message = new Message();
                message.setNumber(snowId);
                message.setPhone("15111111111");
                message.setLocation("XXXXXXXXXXXX");
                message.setCarNo("京A88888");
                message.setSystemtime(datetime);
                mqProducerService.sendMessage(message);
            }
        } catch (Exception e) {
            System.out.println("获取锁失败，失败原因：" + e.getMessage());
        } finally {
            //无论如何, 最后都要解锁
            rLock.unlock();
        }

        //关闭客户端
        redissonClient.shutdown();
    }

    @GetMapping("/sendTag")
    public SendResult sendTag() {
        SendResult sendResult = mqProducerService.sendTagMsg("带有tag的字符消息");
        return sendResult;
    }

}