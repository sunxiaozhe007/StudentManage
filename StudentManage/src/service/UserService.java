package service;

import domain.User;

public interface UserService {

    /**
     * 登陆
     */
    User login(String username,String password);

    /**
     * 注册
     */
    void regist(User user);

    /**
     * 手机号查重
     */
    User selectByPhone(String phone);

    /**
     * 短信发送
     */
    void sendCode(String phone,String code);

}
