package com.kinghk.dao;//package com.yunzhi.multdatademo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinghk.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
