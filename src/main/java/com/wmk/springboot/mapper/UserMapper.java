package com.wmk.springboot.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmk.springboot.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@TableName("users")
public interface UserMapper extends BaseMapper<User> {
}
