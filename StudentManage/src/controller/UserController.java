package controller;

import com.aliyuncs.exceptions.ClientException;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {

    /**
     * 注入Service
     */
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 处理登陆请求
     */

    //登陆
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password")String password,
                              HttpSession session,
                              ModelAndView mv){
        //判断用户是否可以登陆
        User user = userService.login(username,password);
        if (user != null){
            System.out.println(user);
            //客户端跳转到main页面
            mv.setViewName("admin/main");
        }else {
            mv.addObject("message","用户名或密码错误！");
            mv.setViewName("/loginForm");
        }
        //返回
        return mv;
    }


    //注册
    @RequestMapping(value = "/regist")
    public ModelAndView regist(String phone,@ModelAttribute User user,ModelAndView mv,HttpSession session) throws ClientException {
        user.setCreateDate(new Date());

        String code = user.getCode();
            if (session.getAttribute("code").equals(code)){
                userService.regist(user);

                mv.setViewName("redirect:loginForm");
            }else {
                mv.addObject("error", "验证码错误");
            }

        return mv;
    }

    //短信验证
    @RequestMapping(value = "/code")
    public ModelAndView code(String phone,ModelAndView mv,HttpSession session){
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        session.setAttribute("code",code);
        System.out.println(code +"="+"="+phone);
        User user = userService.selectByPhone(phone);
        if (user == null) {
            userService.sendCode(phone,code);
        }else {
            mv.addObject("error","该手机号码已注册过！");
        }
        return mv;
    }

    @RequestMapping("/main")
    public String tomain(){
        return "admin/main";
    }
}
