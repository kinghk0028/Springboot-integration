package com.kinghk.controller;//package com.yunzhi.multdatademo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinghk.entity.User;
import com.kinghk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getInfo")
    public User getInfo(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name","admin");
        User one = userService.getOne(queryWrapper);
        return one;
    }
}
