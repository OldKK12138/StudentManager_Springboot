package com.wmk.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmk.springboot.entity.User;
import com.wmk.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有 User
     */
    @GetMapping("/")
    public List<User> getAllUser(){
        return userService.list();
    }
    /**
     * update或insert User数据
     */
    @PostMapping
    public boolean saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    /**
     * 根据id进行删除
     */
    @DeleteMapping("{id}")
    public boolean deleteUserById(@PathVariable Integer id){
        return userService.removeById(id);
    }
    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public boolean deleteBatchUser(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }
    /**
     * 分页查询 pageNum、pageSize、以及模糊查询的条件 name
     */
    @GetMapping("/page")
    public IPage<User> selectUserPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name){
        return userService.selectUserPage(pageNum, pageSize, name);
    }
    /**
     * 导出
     */
    @GetMapping("/export")
    public void exportFile(HttpServletResponse response){
        userService.exportFile(response);
    }
    /**
     * 导入
     */
    @PostMapping("/import")
    public boolean importFile(MultipartFile file){
        return userService.importFile(file);
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    public boolean login(User user){
        return userService.login(user);
    }
}
