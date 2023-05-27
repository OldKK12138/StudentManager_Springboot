package com.wmk.springboot.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wmk.springboot.entity.Student;
import org.springframework.stereotype.Repository;

//@Repository
////@Mapper  // 在MybatisPlusConfig类上配置了包扫描路径 @MapperScan("com.wmk.springboot.mapper") ，因此可以去除
//public interface StudentMapper {
//    @Select("select * from student")
//    List<Student> findAllStudent();
//
//    @Insert("Insert into student(id,name,age)" +
//            "values(#{id}, #{name}, #{age})")
//    Integer insert(Student student);
//
////    @Update("update student set name=#{name}, age=#{age} where id=#{id}")
//    Integer update(Student student);
//    @Delete("delete from student where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
////    @Select("select * from student limit #{pageNum}, #{pageSize}")
//    @Select("select * from student where name like concat('%', #{name}, '%') limit #{pageNum}, #{pageSize}")
//    List<Student> findPage(Integer pageNum, Integer pageSize, String name);
//
//    @Select("select count(*) from student where name like concat('%', #{name}, '%')")
//    Integer findTotal(String name);
//}


//@Mapper  // 在MybatisPlusConfig类上配置了包扫描路径 @MapperScan("com.wmk.springboot.mapper") ，因此可以去除
@Repository
@TableName("student")
public interface StudentMapper extends BaseMapper<Student> {
//    @Select("select * from student")
//    List<Student> findAllStudent();
//
//    @Insert("Insert into student(id,name,age)" +
//            "values(#{id}, #{name}, #{age})")
//    int insert(Student student);
//
//    //    @Update("update student set name=#{name}, age=#{age} where id=#{id}")
//    int update(Student student);
//    @Delete("delete from student where id = #{id}")
//    int deleteById(@Param("id") Integer id);
//
//    //    @Select("select * from student limit #{pageNum}, #{pageSize}")
//    @Select("select * from student where name like concat('%', #{name}, '%') limit #{pageNum}, #{pageSize}")
//    List<Student> findPage(Integer pageNum, Integer pageSize, String name);
//
//    @Select("select count(*) from student where name like concat('%', #{name}, '%')")
//    int findTotal(String name);
}
