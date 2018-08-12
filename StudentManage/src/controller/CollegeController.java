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
           //根据id查询学院
           College college1 = collegeService.findCollegeById(id);
           //设置数据
           mv.addObject("college",college1);
           //跳转到修改页面
           mv.setViewName("/college/collegeUpdate");
       }else {
            //执行修改操作
           collegeService.updateCollege(college);
           //跳转显示信息
           mv.setViewName("redirect:/college/list");
       }
       return mv;
   }


   @RequestMapping("/college/add")
   public ModelAndView add(String flag,@ModelAttribute College college,ModelAndView mv){
       if (flag.equals("1")){
           //跳转到添加页面
           mv.setViewName("/college/collegeAdd");
       }else {
           //执行添加操作
           collegeService.insertCollege(college);
           //跳转到信息显示页面
           mv.setViewName("redirect:/college/list");
       }
       return mv;
   }

   @RequestMapping("/college/delete")
    public String delete(String ids){

       System.out.println(ids);
       //分解id字符串
       String[] idArray = ids.split(",");
       for (String id : idArray){
           //根据id删除职位
           collegeService.deleteCollege(id);
       }

       return "redirect:/college/list";
   }

}
