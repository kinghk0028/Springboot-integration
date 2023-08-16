package com.kinghk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Area {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String citycode;

    private String areaname;

    private String parentcode;

    private String areacode;

    private String phone;

}
