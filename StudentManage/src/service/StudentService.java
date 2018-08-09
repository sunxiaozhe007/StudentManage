package service;

import com.github.pagehelper.PageInfo;
import domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * ��ѯ����ѧ����Ϣ
     */
    PageInfo selectAllStudent(int pageNum);

    /**
     * ͨ��id��ѯ��Ϣ
     */
    Student selectById(String id);

    /**
     * ģ����ѯѧ����Ϣ
     */
    PageInfo selectStudent(int pageNum,Student student,String college_id,String major_id,String grade_id);

    /**
     * ���ݰ༶id��ѯ����ѧ����Ϣ
     */
    PageInfo selectStudentByGrade(String id,int pageNum);

    /**
     * ����ѧ����Ϣ
     */
    void insertStudent(Student student);

    /**
     * ����idɾ��ѧ��
     */
    void deleteStudent(String id);

    /**
     * ��̬�޸���Ϣ
     */
    void updateStudent(Student student);
}
