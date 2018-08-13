package controller;

import domain.Grade;
import domain.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.GradeService;
import service.MajorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GradeController {

    /**
     * 自动注入Service层
     */
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    @Autowired
    @Qualifier("majorService")
    private MajorService majorService;

    @RequestMapping("/grade/selectByMajor")
    public String sellectByMajor(String id, Model model,HttpServletRequest request){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        Major major = new Major();
        //查询专业信息
        List<Major> majors = majorService.selectAllMajor(major);
        //设置专业信息
        model.addAttribute("major",majors);
        model.addAttribute("grade",gradeService.selectByMajor(id,pageNum));
        return "/grade/grade";
    }

    @RequestMapping("/grade/delete")
    public String list(String ids,Model model,String ide,HttpServletRequest request){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //分解id字符串
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //根据id删除职位
            gradeService.deleteById(id);
        }
        model.addAttribute("grade",gradeService.selectByMajor(ide,pageNum));
        return "/grade/grade";
    }

    @RequestMapping("/grade/select")
    public String select(HttpServletRequest request,Grade grade,Model model,String major_id){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //查询专业信息
        Major major = new Major();
        List<Major> majors = majorService.selectAllMajor(major);
        model.addAttribute("major",majors);
        System.out.println(major_id);
        model.addAttribute("grade",gradeService.selectGrade(grade,pageNum,major_id));
        return "/grade/grade";
    }

    @RequestMapping("/grade/insert")
    public ModelAndView insertGrade(HttpServletRequest request,String major_id,String flag,Grade grade,ModelAndView mv){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        if (flag.equals("1")){
            Major major = new Major();
            //查询专业信息
            List<Major> majors = majorService.selectAllMajor(major);
            //设置专业信息
            mv.addObject("major",majors);
            //跳转到添加页面
            mv.setViewName("grade/gradeAdd");
        }else {
            Major major1 = new Major();
            //查询专业信息
            List<Major> majors = majorService.selectAllMajor(major1);
            //设置专业信息
            mv.addObject("major",majors);
            if (major_id != null){
                Major major = new Major();
                major.setId(Integer.parseInt(major_id));
                grade.setMajor(major);
            }
            //执行添加操作
            gradeService.insertGrade(grade);
            //跳转到显示页面
            mv.addObject("grade",gradeService.selectByMajor(major_id,pageNum));
            mv.setViewName("/grade/grade");
        }
        return mv;
    }

    @RequestMapping("/grade/update")
    public ModelAndView update(String id, String flag, String major_id, Grade grade,ModelAndView mv,HttpServletRequest request){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        if (flag.equals("1")){
            //查询专业信息
            Major major = new Major();
            List<Major> majors = majorService.selectMajorByName(major);
            //设置专业信息
            mv.addObject("major",majors);
            //查询班级信息
            Grade grade1 = gradeService.selectByGradeId(id);
            //设置数据回显
            mv.addObject("grade",grade1);
            //跳转到修改页面
            mv.setViewName("/grade/gradeUpdate");
        }else{
            Major major1 = new Major();
            //查询专业信息
            List<Major> majors = majorService.selectAllMajor(major1);
            //设置专业信息
            mv.addObject("major",majors);
            if (major_id != null){
                Major major = new Major();
                major.setId(Integer.parseInt(major_id));
                grade.setMajor(major);
            }
            //执行修改操作
            gradeService.updateGrade(grade);
            //跳转到显示页面
            mv.addObject("grade",gradeService.selectByMajor(major_id,pageNum));
            mv.setViewName("/grade/grade");
        }
        return mv;
    }
}
