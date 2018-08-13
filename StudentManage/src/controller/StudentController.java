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
     * 自动注入service层
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
        //查询学院信息
        List<College> colleges = collegeService.selectAllCollege();
        //查询专业信息
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //查询班级信息
        List<Grade> grades = gradeService.selectAllGrade();
        //设置数据
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
        //查询学院信息
        List<College> colleges = collegeService.selectAllCollege();
        //查询专业信息
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //查询班级信息
        List<Grade> grades = gradeService.selectAllGrade();
        //设置数据
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
        //查询学院信息
        List<College> colleges = collegeService.selectAllCollege();
        //查询专业信息
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        //查询班级信息
        List<Grade> grades = gradeService.selectAllGrade();
        //设置数据
        model.addAttribute("college",colleges);
        model.addAttribute("major",majors);
        model.addAttribute("grade",grades);
        model.addAttribute("student",studentService.selectStudent(pageNum,student,college_id,major_id,grade_id));
        return "student/student";
    }

    @RequestMapping("/student/add")
    public ModelAndView add(@ModelAttribute Student student, String flag, ModelAndView mv, HttpSession session,String grade_id,String major_id,String college_id) throws IOException {
        if (flag.equals("1")){
            //查询学院，专业，班级信息
            List<College> colleges = collegeService.selectAllCollege();
            Major major = new Major();
            List<Major> majors = majorService.selectAllMajor(major);
            List<Grade> grades = gradeService.selectAllGrade();
            //设置信息
            mv.addObject("major",majors);
            mv.addObject("college",colleges);
            mv.addObject("grade",grades);
            //跳转添加页面
            mv.setViewName("/student/studentAdd");
        }else {
            System.out.println(grade_id);
            //上传照片路径
            // 设置班级信息
            Grade grade = gradeService.selectByGradeId(grade_id);
            student.setGrade(grade);
            //设置专业信息
            Major major = majorService.selectById(major_id);
            student.setMajor(major);
            ///设置学院信息
            College college = collegeService.findCollegeById(college_id);
            student.setCollege(college);

            String path = session.getServletContext().getRealPath("/photo");
            //上传照片名
            String fileName = student.getFile().getOriginalFilename();
            System.out.println(path);
            System.out.println(fileName);
            //将上传照片保存到一个目标文件中
            student.getFile().transferTo(new File(path+File.separator+fileName));

            // 设置名字
            student.setPhoto(fileName);

            System.out.println(student);
            studentService.insertStudent(student);
            mv.setViewName("redirect:/student/list");
        }
        return mv;
    }

    @RequestMapping("/student/delete")
    public String delete(String ids) {
        //分解id字符串
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //根据id删除职位
            studentService.deleteStudent(id);
        }
        return "redirect:/student/list";
    }

    @RequestMapping("/student/update")
    public ModelAndView update(@ModelAttribute Student student,ModelAndView mv,String flag,String id,String college_id,String major_id,String grade_id,HttpSession session) throws IOException {
        if (flag.equals("1")){
            //数据回显
            //查询学院，专业，班级信息
            List<College> colleges = collegeService.selectAllCollege();
            Major major = new Major();
            List<Major> majors = majorService.selectAllMajor(major);
            List<Grade> grades = gradeService.selectAllGrade();
            //设置信息
            mv.addObject("major",majors);
            mv.addObject("college",colleges);
            mv.addObject("grade",grades);
            Student student1 = studentService.selectById(id);
            mv.addObject("student",student1);
            //跳转到修改页面
            mv.setViewName("/student/studentUpdate");
        }else {
            // 设置班级信息
            Grade grade = gradeService.selectByGradeId(grade_id);
            student.setGrade(grade);
            //设置专业信息
            Major major = majorService.selectById(major_id);
            student.setMajor(major);
            ///设置学院信息
            College college = collegeService.findCollegeById(college_id);
            student.setCollege(college);

            String path = session.getServletContext().getRealPath("/photo");
            //上传照片名
            String fileName = student.getFile().getOriginalFilename();
            if (student.getFile() !=null) {
                System.out.println(path);
                System.out.println(fileName);
                //将上传照片保存到一个目标文件中
                student.getFile().transferTo(new File(path + File.separator + fileName));
            }else {
                Student student1 = studentService.selectById(id);
                fileName = student1.getPhoto();
            }
            // 设置名字
            student.setPhoto(fileName);

            //执行修改操作
            studentService.updateStudent(student);

            //返回列表
            mv.setViewName("redirect:/student/list");

        }
        return mv;
    }

}
