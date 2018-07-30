package dao;

import dao.provider.UserProvder;
import domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface UserDao {

    /**
     * ��½
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    /**
     * ע��
     */
    @SelectProvider(type = UserProvder.class,method = "insertUser")
    void insertUser(User user);

    /**
     * �ֻ��������
     */
    @Select("select * from user where phone = #{phone}")
    User selectByPhone(@Param("phone")String phone);
}
