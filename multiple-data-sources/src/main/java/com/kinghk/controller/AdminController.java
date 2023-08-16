package com.kinghk.controller;//package com.yunzhi.multdatademo.controller;

import com.kinghk.entity.Admin;
import com.kinghk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("getInfo")
    public Admin getInfo(){
        Admin byId = adminService.getById(1);
        return byId;
    }
}
