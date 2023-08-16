package com.kinghk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tbl_user_sync")
public class UserSync {

    @TableId("_id")
    private String id;

    @TableField("user_name")
    private String userName;

}
