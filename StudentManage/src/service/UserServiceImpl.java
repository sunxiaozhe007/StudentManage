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
     * �Զ�ע��־ò�Dao����
     */
    @Autowired
    private UserDao userDao;

    /**
     * �����û����������ѯ�û�
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userDao.selectByUsernameAndPassword(username,password);
    }

    /**
     * ����û���Ϣ
     * @param user
     */
    @Override
    public void regist(User user) {
        userDao.insertUser(user);
    }

    /**
     * �����ֻ��Ų�ѯ���ù�����Ϣ
     * @param phone
     * @return
     */
    @Override
    public User selectByPhone(String phone) {
        return userDao.selectByPhone(phone);
    }

    /**
     * ���Ͷ�����֤��
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
