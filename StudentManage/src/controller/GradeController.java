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
     * �Զ�ע��Service��
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
        //��ѯרҵ��Ϣ
        List<Major> majors = majorService.selectAllMajor(major);
        //����רҵ��Ϣ
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
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��ְλ
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
        //��ѯרҵ��Ϣ
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
            //��ѯרҵ��Ϣ
            List<Major> majors = majorService.selectAllMajor(major);
            //����רҵ��Ϣ
            mv.addObject("major",majors);
            //��ת�����ҳ��
            mv.setViewName("grade/gradeAdd");
        }else {
            Major major1 = new Major();
            //��ѯרҵ��Ϣ
            List<Major> majors = majorService.selectAllMajor(major1);
            //����רҵ��Ϣ
            mv.addObject("major",majors);
            if (major_id != null){
                Major major = new Major();
                major.setId(Integer.parseInt(major_id));
                grade.setMajor(major);
            }
            //ִ����Ӳ���
            gradeService.insertGrade(grade);
            //��ת����ʾҳ��
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
            //��ѯרҵ��Ϣ
            Major major = new Major();
            List<Major> majors = majorService.selectMajorByName(major);
            //����רҵ��Ϣ
            mv.addObject("major",majors);
            //��ѯ�༶��Ϣ
            Grade grade1 = gradeService.selectByGradeId(id);
            //�������ݻ���
            mv.addObject("grade",grade1);
            //��ת���޸�ҳ��
            mv.setViewName("/grade/gradeUpdate");
        }else{
            Major major1 = new Major();
            //��ѯרҵ��Ϣ
            List<Major> majors = majorService.selectAllMajor(major1);
            //����רҵ��Ϣ
            mv.addObject("major",majors);
            if (major_id != null){
                Major major = new Major();
                major.setId(Integer.parseInt(major_id));
                grade.setMajor(major);
            }
            //ִ���޸Ĳ���
            gradeService.updateGrade(grade);
            //��ת����ʾҳ��
            mv.addObject("grade",gradeService.selectByMajor(major_id,pageNum));
            mv.setViewName("/grade/grade");
        }
        return mv;
    }
}
