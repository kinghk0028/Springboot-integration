package com.kinghk.service;

import com.kinghk.event.UserRegisterEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService implements ApplicationContextAware, ApplicationEventPublisherAware {

    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    public boolean userRegister(String username){
        System.out.println("用户"+username+"注册成功");
        //可以用applicationcontext发送事件
        applicationContext.publishEvent(new UserRegisterEvent(this,username));
        //也可以用applicationEventPublisher  二者等价
        //applicationEventPublisher.publishEvent(new UserRegisterEvent(this,username));
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
