package com.kinghk.listener;

import com.kinghk.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncUserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(Thread.currentThread().getName()+"-收到用户注册事件，异步执行任务");
    }
}
