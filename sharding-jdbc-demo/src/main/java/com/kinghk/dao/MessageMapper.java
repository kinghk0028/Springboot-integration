package com.kinghk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinghk.model.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
