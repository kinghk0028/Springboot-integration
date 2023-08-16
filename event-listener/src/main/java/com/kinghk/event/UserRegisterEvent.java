package com.kinghk.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserRegisterEvent extends ApplicationEvent {

    private String username;

    public UserRegisterEvent(Object source,String username) {
        super(source);
        this.username = username;
    }

}
