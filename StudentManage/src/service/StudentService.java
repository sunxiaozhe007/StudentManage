package service;

import com.github.pagehelper.PageInfo;
import domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生信息
     */
    PageInfo selectAllStudent(int pageNum);

    /**
     * 通过id查询信息
     */
    Student selectById(String id);

    /**
     * 模糊查询学生信息
     */
    PageInfo selectStudent(int pageNum,Student student,String college_id,String major_id,String grade_id);

    /**
     * 根据班级id查询所有学生信息
     */
    PageInfo selectStudentByGrade(String id,int pageNum);

    /**
     * 插入学生信息
     */
    void insertStudent(Student student);

    /**
     * 根据id删除学生
     */
    void deleteStudent(String id);

    /**
     * 动态修改信息
     */
    void updateStudent(Student student);
}
