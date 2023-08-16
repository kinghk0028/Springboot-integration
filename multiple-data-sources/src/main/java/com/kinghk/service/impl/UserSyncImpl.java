package com.kinghk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghk.dao.UserSyncMapper;
import com.kinghk.entity.UserSync;
import com.kinghk.service.UserSyncService;
import org.springframework.stereotype.Service;

@DS("doris")
@Service
public class UserSyncImpl extends ServiceImpl<UserSyncMapper, UserSync>
implements UserSyncService {

}
