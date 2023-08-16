package com.kinghk.service.impl;//package com.yunzhi.multdatademo.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghk.dao.AdminMapper;
import com.kinghk.entity.Admin;
import com.kinghk.service.AdminService;
import org.springframework.stereotype.Service;

@Service
@DS("slave")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
implements AdminService {

}
