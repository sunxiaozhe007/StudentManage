package controller;

import domain.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CollegeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CollegeController {
    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;

   @RequestMapping("/college/list")
    public String list(Model model, HttpServletRequest request){
       int pageNum = 1;
       if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
           pageNum = 1;
       }else {
           pageNum = Integer.parseInt(request.getParameter("pageNum"));
       }
       model.addAttribute("college",collegeService.findAllCollege(pageNum));
       return "/college/college";
   }

   @RequestMapping("/college/find")
   public String find(Model model,HttpServletRequest request,String name){
       int pageNum = 1;
       if (request.getParameter("pageNum")==null || "".equals(request.getParameter("pageNum"))){
           pageNum = 1;
       }else {
           pageNum = Integer.parseInt(request.getParameter("pageNum"));
       }
       model.addAttribute("college",collegeService.findCollegeByName(name,pageNum));
       return "/college/college";
   }

   @RequestMapping("/college/update")
   public ModelAndView update(String id,ModelAndView mv, @ModelAttribute College college,String flag){
       if (flag.equals("1")){
           //����id��ѯѧԺ
           College college1 = collegeService.findCollegeById(id);
           //��������
           mv.addObject("college",college1);
           //��ת���޸�ҳ��
           mv.setViewName("/college/collegeUpdate");
       }else {
            //ִ���޸Ĳ���
           collegeService.updateCollege(college);
           //��ת��ʾ��Ϣ
           mv.setViewName("redirect:/college/list");
       }
       return mv;
   }


   @RequestMapping("/college/add")
   public ModelAndView add(String flag,@ModelAttribute College college,ModelAndView mv){
       if (flag.equals("1")){
           //��ת�����ҳ��
           mv.setViewName("/college/collegeAdd");
       }else {
           //ִ����Ӳ���
           collegeService.insertCollege(college);
           //��ת����Ϣ��ʾҳ��
           mv.setViewName("redirect:/college/list");
       }
       return mv;
   }

   @RequestMapping("/college/delete")
    public String delete(String ids){

       System.out.println(ids);
       //�ֽ�id�ַ���
       String[] idArray = ids.split(",");
       for (String id : idArray){
           //����idɾ��ְλ
           collegeService.deleteCollege(id);
       }

       return "redirect:/college/list";
   }

}
