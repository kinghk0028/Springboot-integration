package com.kinghk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinghk.entity.UserSync;
import com.kinghk.service.UserSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usersync")
public class UserSyncController {

    @Autowired
    private UserSyncService userSyncService;

    @GetMapping("getInfo/{id}")
    public UserSync getInfo(@PathVariable("id") String id){
        UserSync byId = userSyncService.getOne(new QueryWrapper<>());
        return byId;
    }
}
