package dao.provider;

import domain.Major;
import org.apache.ibatis.jdbc.SQL;

public class MajorProvder {

    //模糊查询专业
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

    //动态添加专业
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


    //动态修改专业
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
