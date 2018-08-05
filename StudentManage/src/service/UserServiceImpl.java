package service;

import com.aliyuncs.exceptions.ClientException;
import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.MoblieMessageUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 自动注入持久层Dao对象
     */
    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userDao.selectByUsernameAndPassword(username,password);
    }

    /**
     * 添加用户信息
     * @param user
     */
    @Override
    public void regist(User user) {
        userDao.insertUser(user);
    }

    /**
     * 根据手机号查询有用过户信息
     * @param phone
     * @return
     */
    @Override
    public User selectByPhone(String phone) {
        return userDao.selectByPhone(phone);
    }

    /**
     * 发送短信验证码
     * @param phone
     * @param code
     */
    @Override
    public void sendCode(String phone, String code) {
        try {
            MoblieMessageUtil.sendCode(phone,code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
