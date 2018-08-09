package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.CollegeDao;
import dao.GradeDao;
import dao.MajorDao;
import dao.StudentDao;
import domain.College;
import domain.Grade;
import domain.Major;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    /**
     * �Զ�ע��Dao��
     * @return
     */
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private MajorDao majorDao;

    int size = 12;


    /**
     * ����������Ϣ
     * @return
     */
    @Override
    public PageInfo selectAllStudent(int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<Student> students = studentDao.selectAllStudent();
        PageInfo pageInfo = new PageInfo(students);
        return pageInfo;
    }

    /**
     * ����id��ѯѧ����Ϣ
     * @param id
     * @return
     */
    @Override
    public Student selectById(String id) {

        return studentDao.selectById(id);
    }

    /**
     * ģ����ѯѧ����Ϣ
     * @param pageNum
     * @param student
     * @return
     */
    @Override
    public PageInfo selectStudent(int pageNum, Student student,String college_id,String major_id,String grade_id) {

        //��װ��Ϣ
        College college = collegeDao.selectCollegeById(college_id);
        Major major = majorDao.selectMajorById(major_id);
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(grade_id));
        student.setCollege(college);
        student.setMajor(major);
        student.setGrade(grade);

        PageHelper.startPage(pageNum,size);
        List<Student> students = studentDao.selectStudent(student);

        PageInfo pageInfo = new PageInfo(students);
        return pageInfo;
    }

    /**
     * ���ݰ༶��Ϣ��ѯѧ��
     * @param id
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo selectStudentByGrade(String id, int pageNum) {
        PageHelper.startPage(pageNum,size);

        Grade grade = gradeDao.selectByGradeId(id);
        List<Student> students = studentDao.selectStudentByGrade(id);
        for (Student student : students){
            student.setGrade(grade);
        }
        System.out.println(grade);
        System.out.println(students);
        PageInfo pageInfo = new PageInfo(students);
        return pageInfo;
    }

    /**
     * ��̬����ѧ����Ϣ
     * @param student
     */
    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    /**
     * ����idɾ��ѧ����Ϣ
     * @param id
     */
    @Override
    public void deleteStudent(String id) {
        studentDao.deleteStudent(id);
    }

    /**
     * ��̬�޸���Ϣ
     * @param student
     */
    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }
}
