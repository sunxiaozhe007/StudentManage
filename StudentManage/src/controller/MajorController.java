package controller;

import domain.College;
import domain.Major;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CollegeService;
import service.MajorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MajorController {

    /**
     * �Զ�ע��service��
     */
    @Autowired
    @Qualifier("majorService")
    private MajorService majorService;

    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;

    @RequestMapping("/major/selectByCollege")
    public ModelAndView selectByCollege(String id,HttpServletRequest request,ModelAndView mv){
        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        //��ѯѧԺ��Ϣ
        List<College> colleges = collegeService.selectAllCollege();
        //����ѧԺ��Ϣ
        mv.addObject("college",colleges);
        mv.addObject("major",majorService.selectByCollege(id,pageNum));
        mv.setViewName("/major/major");

        return mv;
    }

    @RequestMapping("/major/list")
    public ModelAndView selectAll(@ModelAttribute Major major,ModelAndView mv,HttpServletRequest request,String college_id){
        //��ѯѧԺ��Ϣ
        List<College> colleges = collegeService.selectAllCollege();
        //����ѧԺ��Ϣ
        mv.addObject("college",colleges);

        int pageNum = 1;
        if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
            pageNum = 1;
        }else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        System.out.println(college_id);
        if (college_id != null ){
            College college = new College();
            college.setId(Integer.parseInt(college_id));
            major.setCollege(college);
        }
        mv.addObject("major",majorService.selectAllMajor(major,pageNum));
        mv.setViewName("/major/major");
        return mv;
    }

    @RequestMapping("/major/add")
    public ModelAndView addMajor(String college_id,String flag,@ModelAttribute Major major, ModelAndView mv){
        if (flag.equals("1")){
            //��ѯѧԺ��Ϣ
            List<College> colleges = collegeService.selectAllCollege();
            //����ѧԺ��Ϣ
            mv.addObject("college",colleges);
            //��ת�����ҳ��
            mv.setViewName("/major/majorAdd");
        }else {
            if (college_id != null){
                College college = new College();
                college.setId(Integer.parseInt(college_id));
                major.setCollege(college);
            }
            //ִ����Ӳ���
            majorService.insertMajor(major);
            //������Ϣ��ʾҳ��
            mv.setViewName("redirect:/major/list");
        }
        return mv;
    }


    @RequestMapping("/major/delete")
    public String delete(String ids){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��ְλ
            majorService.deleteMajor(id);
        }
        return "redirect:/major/list";
    }

    @RequestMapping("/major/update")
    public ModelAndView update(String college_id,String id,@ModelAttribute Major major,String flag,ModelAndView mv){
        if (flag.equals("1")){
            //��ѯѧԺ��Ϣ
            List<College> colleges = collegeService.selectAllCollege();
            //����ѧԺ��Ϣ
            mv.addObject("college",colleges);
            //���ݻ���
            Major major1 = majorService.selectById(id);
            System.out.println(major1);
            mv.addObject("major",major1);
            //��ת���޸�ҳ��
            mv.setViewName("/major/majorUpdate");
        }else {
            if (college_id != null){
                College college = new College();
                college.setId(Integer.parseInt(college_id));
                major.setCollege(college);
            }
            //ִ���޸Ĳ���
            majorService.updateMajor(major);
            mv.setViewName("redirect:/major/list");
        }
        return mv;
    }

}
