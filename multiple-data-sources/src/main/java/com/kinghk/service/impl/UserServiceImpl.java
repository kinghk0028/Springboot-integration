package com.kinghk.service.impl;//package com.yunzhi.multdatademo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghk.dao.UserMapper;
import com.kinghk.entity.User;
import com.kinghk.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("clickhouse")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService {
}
