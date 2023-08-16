package com.kinghk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghk.dao.MessageMapper;
import com.kinghk.model.Message;
import com.kinghk.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
implements MessageService {
}
