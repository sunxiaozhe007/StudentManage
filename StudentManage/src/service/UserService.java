package service;

import domain.User;

public interface UserService {

    /**
     * ��½
     */
    User login(String username,String password);

    /**
     * ע��
     */
    void regist(User user);

    /**
     * �ֻ��Ų���
     */
    User selectByPhone(String phone);

    /**
     * ���ŷ���
     */
    void sendCode(String phone,String code);

}
