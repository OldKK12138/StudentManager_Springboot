package com.wmk.springboot.service;

import cn.hutool.core.lang.intern.InternUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.net.URLEncoder;
import com.wmk.springboot.entity.Student;
import com.wmk.springboot.mapper.StudentMapper;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//@Service
//public class StudentService {
//    @Autowired
//    private StudentMapper studentMapper;
//
//    public List<Student> findAllStudent(){
//       return studentMapper.findAllStudent();
//    }
//
//    public Map<String, Object> findPage(Integer pageNum, Integer pageSize, String name) {
//        // 将分页查询出来的数据和数据的总条数封装到一个map集合中去
//        Map<String, Object> res = new HashMap<>();
//        List<Student> students = studentMapper.findPage(pageNum, pageSize, name);
//        int total = studentMapper.findTotal(name);
//        res.put("total", total);
//        res.put("data", students);
//        return res;
//    }
//
//    // 判断是update还是insert，并执行相应的业务逻辑
//    public int save(Student student){
//        if(student.getId()==null){
//            // 没有id，则为insert操作
//            return studentMapper.insert(student);
//        }else{
//            return studentMapper.update(student);
//        }
//    }
//
//    public Integer deleteById(Integer id) {
//        return studentMapper.deleteById(id);
//    }
//}

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    // 导入功能
    public boolean imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Student> students = reader.readAll(Student.class);
        System.out.println(students);
        this.saveBatch(students);
        return true;
    }

    // 导出功能
    public void export(HttpServletResponse response) throws IOException {
        List<Student> students = this.list();
        // 通过工具类创建 writer 写出到 磁盘路径
        //  ExcelWriter writer = ExcelUtil.getWriter(fileUploadPath + “/用户信息.xlsx”);
        // 在内存操作，写出到 浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名（不如的话，导出的excel文件的表头会和数据库中一样是英文的）
        writer.addHeaderAlias("name","名字");
        writer.addHeaderAlias("age","年龄");
        // 一次性写出list内的对象到Excel，使用默认样式，强制输出标题
        writer.write(students, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedoucement.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

    public boolean saveStudent(Student student) {
        if(student.getId() == null){
            return save(student); // 直接插入数据
        }else{
            return updateById(student);  // 更新数据
        }
    }
    public List<Student> getAllStudent() {
        return list();
    }

    // 登录
    public boolean login(Student student) {
        String name = student.getName();
//        int password = student.getAge();  // 我们这里为了测试，暂时将age字段当成password
        if(StrUtil.isBlank(name) || StrUtil.isBlank(name)){ // 如果name和password为空，则返回false
            return false;
        }
        QueryWrapper<Student>  wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Student res = getOne(wrapper);
        return res != null;
    }
}
