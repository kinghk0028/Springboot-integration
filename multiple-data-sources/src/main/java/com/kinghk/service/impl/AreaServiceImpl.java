package com.kinghk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghk.dao.AreaMapper;
import com.kinghk.entity.Area;
import com.kinghk.service.AreaService;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
implements AreaService {
}
