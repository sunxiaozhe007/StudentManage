package dao.provider;

import domain.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvder {

    //≤Â»Î”√ªß
    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO("user");
                if (user.getUsername() != null && !user.getUsername().equals("")){
                    VALUES("username","#{username}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")){
                    VALUES("password","#{password}");
                }
                if (user.getCreateDate() != null && !user.getCreateDate().equals("")){
                    VALUES("createtime","#{createDate}");
                }
                if (user.getCode() != null && !user.getCode().equals("")){
                    VALUES("code","#{code}");
                }
                if (user.getPhone() != null && !user.getPhone().equals("")){
                    VALUES("phone","#{phone}");
                }
            }
        }.toString();
    }
}
