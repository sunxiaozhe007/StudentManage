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
     * ע��Service
     */
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * �����½����
     */

    //��½
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password")String password,
                              HttpSession session,
                              ModelAndView mv){
        //�ж��û��Ƿ���Ե�½
        User user = userService.login(username,password);
        if (user != null){
            System.out.println(user);
            //�ͻ�����ת��mainҳ��
            mv.setViewName("admin/main");
        }else {
            mv.addObject("message","�û������������");
            mv.setViewName("/loginForm");
        }
        //����
        return mv;
    }


    //ע��
    @RequestMapping(value = "/regist")
    public ModelAndView regist(String phone,@ModelAttribute User user,ModelAndView mv,HttpSession session) throws ClientException {
        user.setCreateDate(new Date());

        String code = user.getCode();
            if (session.getAttribute("code").equals(code)){
                userService.regist(user);

                mv.setViewName("redirect:loginForm");
            }else {
                mv.addObject("error", "��֤�����");
            }

        return mv;
    }

    //������֤
    @RequestMapping(value = "/code")
    public ModelAndView code(String phone,ModelAndView mv,HttpSession session){
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        session.setAttribute("code",code);
        System.out.println(code +"="+"="+phone);
        User user = userService.selectByPhone(phone);
        if (user == null) {
            userService.sendCode(phone,code);
        }else {
            mv.addObject("error","���ֻ�������ע�����");
        }
        return mv;
    }

    @RequestMapping("/main")
    public String tomain(){
        return "admin/main";
    }
}
