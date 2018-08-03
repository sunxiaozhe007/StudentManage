package dao;

import dao.provider.StudentProovder;
import domain.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface StudentDao {

    //��ѯ����ѧ����Ϣ
    @Select("select * from student")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "college_id" , property = "college" , one = @One(select = "dao.CollegeDao.selectCollegeById" , fetchType = FetchType.EAGER)),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
            @Result(column = "grade_id" , property = "grade" , one = @One(select = "dao.GradeDao.selectByGradeId" , fetchType = FetchType.EAGER)),

    })
    List<Student> selectAllStudent();

    //ͨ��id��ѯѧ����ϸ��Ϣ
    @Select("select * from student where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "college_id" , property = "college" , one = @One(select = "dao.CollegeDao.selectCollegeById" , fetchType = FetchType.EAGER)),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
            @Result(column = "grade_id" , property = "grade" , one = @One(select = "dao.GradeDao.selectByGradeId" , fetchType = FetchType.EAGER)),

    })
    Student selectById(String id);

    //ģ����ѯѧ����Ϣ
    @SelectProvider(type = StudentProovder.class,method = "selectStudent")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "num",property = "num"),
            @Result(column = "college_id" , property = "college" , one = @One(select = "dao.CollegeDao.selectCollegeById" , fetchType = FetchType.EAGER)),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
            @Result(column = "grade_id" , property = "grade" , one = @One(select = "dao.GradeDao.selectByGradeId" , fetchType = FetchType.EAGER)),

    })
    List<Student> selectStudent(Student student);

    //���ݰ༶id��ѯѧ����Ϣ
    @Select("select * from student where grade_id = #{id}")
    List<Student> selectStudentByGrade(String id);


    //��̬����ѧ����Ϣ
    @SelectProvider(type = StudentProovder.class,method = "insertStudent")
    void insertStudent(Student student);

    //����ѧ��idɾ��ѧ��
    @Select("delete from student where id = #{id}")
    void deleteStudent(String id);

    //�޸�ѧ����Ϣ
    @SelectProvider(type = StudentProovder.class,method = "updateStudent")
    void updateStudent(Student student);

}
