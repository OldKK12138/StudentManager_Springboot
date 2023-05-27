package com.wmk.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "users")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String nickname;
    private Integer age;
    private String password;
    private String address;
    private String image;
    @TableField(value = "create_time")
    private Date createTime;
}
