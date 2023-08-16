package com.kinghk;

import com.kinghk.service.UserRegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class EventListenerApplicationTests {

    //    @Autowired
//    private UserRegisterService userRegisterService;

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EventListenerApplication.class);
        UserRegisterService service = ctx.getBean(UserRegisterService.class);
        service.userRegister("tom");
    }
}
