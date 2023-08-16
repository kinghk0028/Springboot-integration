package com.kinghk.listener;

import com.kinghk.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(2)//指定优先级，越小优先级越高
public class MailUserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        log.info("监听到邮件发送事件："+event.getUsername()+"给用户发送邮件");
    }
}

@Component
@Order(1)
//第二种方式 EventListener注解监听事件
class CouponUserRegisterListener {
    @EventListener
    public void sendCoupon(UserRegisterEvent event) {
        System.out.println(Thread.currentThread().getName()+"-给用户"+event.getUsername()+"发送优惠券!");
    }
}