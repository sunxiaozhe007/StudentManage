package controller;

import domain.College;
import domain.Grade;
import domain.Major;
import domain.Student;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CollegeService;
import service.GradeService;
import service.MajorService;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {
    /**
     * �Զ�ע��service��
     */
    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;
    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;
    @Autowired
    @Qualifier("majorService")
    private MajorService majorService;
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;

    @RequestMapping("/student/list")
    public String list(Model model, HttpServletRequest request){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //��ѯѧԺ��Ϣ
        List<College> colleges = collegeService.selectAllCollege();
        //��ѯרҵ��Ϣ
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //��ѯ�༶��Ϣ
        List<Grade> grades = gradeService.selectAllGrade();
        //��������
        model.addAttribute("college",colleges);
        model.addAttribute("major",majors);
        model.addAttribute("grade",grades);
        model.addAttribute("student",studentService.selectAllStudent(pageNum));
        return "/student/student";
    }
    @RequestMapping("/student/selectById")
    public String selectById(String id,Model model){
        Student student = studentService.selectById(id);
        System.out.println(student);
        model.addAttribute("student",student);
        return "/student/inf";

    }

    @RequestMapping("/student/selectByGrade")
    public String selectByGrade(String id,Model model,HttpServletRequest request){
        System.out.println(id);
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //��ѯѧԺ��Ϣ
        List<College> colleges = collegeService.selectAllCollege();
        //��ѯרҵ��Ϣ
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //��ѯ�༶��Ϣ
        List<Grade> grades = gradeService.selectAllGrade();
        //��������
        model.addAttribute("college",colleges);
        model.addAttribute("major",majors);
        model.addAttribute("grade",grades);
        model.addAttribute("student",studentService.selectStudentByGrade(id,pageNum));
        return "student/student";
    }

    @RequestMapping("/student/selectStudent")
    public String selectStudent(Student student,Model model,HttpServletRequest request,String college_id,String major_id,String grade_id){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //��ѯѧԺ��Ϣ
        List<College> colleges = collegeService.selectAllCollege();
        //��ѯרҵ��Ϣ
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //��ѯ�༶��Ϣ
        List<Grade> grades = gradeService.selectAllGrade();
        //��������
        model.addAttribute("college",colleges);
        model.addAttribute("major",majors);
        model.addAttribute("grade",grades);
        model.addAttribute("student",studentService.selectStudent(pageNum,student,college_id,major_id,grade_id));
        return "student/student";
    }

    @RequestMapping("/student/add")
    public ModelAndView add(@ModelAttribute Student student, String flag, ModelAndView mv, HttpSession session,String grade_id,String major_id,String college_id) throws IOException {
        if (flag.equals("1")){
            //��ѯѧԺ��רҵ���༶��Ϣ
            List<College> colleges = collegeService.selectAllCollege();
            Major major = new Major();
            List<Major> majors = majorService.selectAllMajor(major);
            List<Grade> grades = gradeService.selectAllGrade();
            //������Ϣ
            mv.addObject("major",majors);
            mv.addObject("college",colleges);
            mv.addObject("grade",grades);
            //��ת���ҳ��
            mv.setViewName("/student/studentAdd");
        }else {
            System.out.println(grade_id);
            //�ϴ���Ƭ·��
            // ���ð༶��Ϣ
            Grade grade = gradeService.selectByGradeId(grade_id);
            student.setGrade(grade);
            //����רҵ��Ϣ
            Major major = majorService.selectById(major_id);
            student.setMajor(major);
            ///����ѧԺ��Ϣ
            College college = collegeService.findCollegeById(college_id);
            student.setCollege(college);

            String path = session.getServletContext().getRealPath("/photo");
            //�ϴ���Ƭ��
            String fileName = student.getFile().getOriginalFilename();
            System.out.println(path);
            System.out.println(fileName);
            //���ϴ���Ƭ���浽һ��Ŀ���ļ���
            student.getFile().transferTo(new File(path+File.separator+fileName));

            // ��������
            student.setPhoto(fileName);

            System.out.println(student);
            studentService.insertStudent(student);
            mv.setViewName("redirect:/student/list");
        }
        return mv;
    }

    @RequestMapping("/student/delete")
    public String delete(String ids) {
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��ְλ
            studentService.deleteStudent(id);
        }
        return "redirect:/student/list";
    }

    @RequestMapping("/student/update")
    public ModelAndView update(@ModelAttribute Student student,ModelAndView mv,String flag,String id,String college_id,String major_id,String grade_id,HttpSession session) throws IOException {
        if (flag.equals("1")){
            //���ݻ���
            //��ѯѧԺ��רҵ���༶��Ϣ
            List<College> colleges = collegeService.selectAllCollege();
            Major major = new Major();
            List<Major> majors = majorService.selectAllMajor(major);
            List<Grade> grades = gradeService.selectAllGrade();
            //������Ϣ
            mv.addObject("major",majors);
            mv.addObject("college",colleges);
            mv.addObject("grade",grades);
            Student student1 = studentService.selectById(id);
            mv.addObject("student",student1);
            //��ת���޸�ҳ��
            mv.setViewName("/student/studentUpdate");
        }else {
            // ���ð༶��Ϣ
            Grade grade = gradeService.selectByGradeId(grade_id);
            student.setGrade(grade);
            //����רҵ��Ϣ
            Major major = majorService.selectById(major_id);
            student.setMajor(major);
            ///����ѧԺ��Ϣ
            College college = collegeService.findCollegeById(college_id);
            student.setCollege(college);

            String path = session.getServletContext().getRealPath("/photo");
            //�ϴ���Ƭ��
            String fileName = student.getFile().getOriginalFilename();
            if (student.getFile() !=null) {
                System.out.println(path);
                System.out.println(fileName);
                //���ϴ���Ƭ���浽һ��Ŀ���ļ���
                student.getFile().transferTo(new File(path + File.separator + fileName));
            }else {
                Student student1 = studentService.selectById(id);
                fileName = student1.getPhoto();
            }
            // ��������
            student.setPhoto(fileName);

            //ִ���޸Ĳ���
            studentService.updateStudent(student);

            //�����б�
            mv.setViewName("redirect:/student/list");

        }
        return mv;
    }

}
