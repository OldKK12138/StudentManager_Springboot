package com.wmk.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmk.springboot.entity.Student;
import com.wmk.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 插入或更新数据
    @PostMapping
    public boolean saveStudent(@RequestBody Student student){
        // 新增或者更新操作
        return studentService.saveStudent(student);
    }

    // 查询所有数据
    @GetMapping("/")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    // 删除数据
    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable Integer id){
        return studentService.removeById(id);
    }

    // 批量删除，通过ids
    @PostMapping("/del/batch")
    public boolean deleteByIds(@RequestBody List<Integer> ids){
        return studentService.removeByIds(ids);
    }

    // 分页查询
    @GetMapping("/page")
    public IPage<Student> selectStudentPage(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String name){
        IPage<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);  // 模糊查询
////        queryWrapper.or().like("age",19);
////        queryWrapper.eq("age", 19);  // age<19
//        queryWrapper.lt("age", 30);  //  age< 30
        queryWrapper.orderByDesc("id");  // 根据id进行逆序，为了展示新增的数据
        return studentService.page(page, queryWrapper);
//        return studentService.page(page);
    }

    // 导出功能
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        studentService.export(response);
    }

    // 导入功能
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws IOException {
        return studentService.imp(file);
    }

    // 登录功能
    @PostMapping("/login")
    public boolean login(@RequestBody Student student){
        return studentService.login(student);
    }
}
