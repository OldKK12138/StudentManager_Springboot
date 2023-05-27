package com.wmk.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wmk.springboot.entity.User;
import com.wmk.springboot.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    // update或insert数据
    public boolean saveUser(User user) {
        if(user.getId()==null){  // 插入操作
           return this.save(user);
        } else{  // 根据id来更新user
            return this.updateById(user);
        }
    }
    //分页查询
    public IPage<User> selectUserPage(Integer pageNum, Integer pageSize, String name) {
        IPage<User> page = new Page<>(pageNum, pageSize);  // 分页查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();  // 添加模糊查询的条件
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");  // 根据id进行降序排列
        return this.page(page, queryWrapper);
    }
    // 导出
    public void exportFile(HttpServletResponse response) {
        // 1.获取所有的User数据

    }
    // 导入
    public boolean importFile(MultipartFile file) {
        return false;
    }

    //登录
    public boolean login(User user) {
        return false;
    }
}
