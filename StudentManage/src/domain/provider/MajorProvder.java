package dao.provider;

import domain.Major;
import org.apache.ibatis.jdbc.SQL;

public class MajorProvder {

    //ģ����ѯרҵ
    public String selectMajor(Major major){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("major");
                if (major.getName() != null && !major.getName().equals("")){
                    WHERE("name LIKE CONCAT ('%',#{name},'%')");
                }
                if (major.getCollege() != null && major.getCollege().getId() != 0){
                    WHERE("college_id LIKE CONCAT ('%',#{college.id},'%')");
                }
            }
        }.toString();
        return sql;
    }

    //��̬���רҵ
    public String insertMajor(Major major){
        return new SQL(){
            {
                INSERT_INTO("major");
                if (major.getName() != null){
                    VALUES("name","#{name}");
                }
                if (major.getIntroduce() != null){
                    VALUES("introduce","#{introduce}");
                }
                if (major.getCollege() != null){
                    VALUES("college_id", "#{college.id}");
                }
            }
        }.toString();
    }


    //��̬�޸�רҵ
    public String updateMajor(Major major){
        return new SQL() {
            {
                UPDATE("major");
                if (major.getName() != null) {
                    SET("name = #{name}");
                }
                if (major.getIntroduce() != null) {
                    SET("introduce = #{introduce}");
                }
                if (major.getCollege() != null){
                    SET("college_id = #{college.id}");
                }

                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
