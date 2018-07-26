package dao.provider;

import domain.College;
import org.apache.ibatis.jdbc.SQL;

public class CollegeProvder {

    //��������ģ����ѯ
    public String selectByName(String name){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("college");
                if (name != null && !name.equals("")){
                    WHERE("name LIKE CONCAT ('%',#{name},'%')");
                }
            }
        }.toString();
        return sql;
    }


    //���ѧԺ
    public String insertCollege(College college){
        return new SQL(){
            {
                INSERT_INTO("college");
                if (college.getName() != null && !college.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if (college.getIntroduce() != null && !college.getIntroduce().equals("")){
                    VALUES("introduce","#{introduce}");
                }
            }
        }.toString();
    }


    //�޸�ѧԺ
    public String updateCollege(College college){
        return new SQL(){
            {
                UPDATE("college");
                if (college.getName() != null){
                    SET("name = #{name}");
                }
                if (college.getIntroduce() != null){
                    SET("introduce = #{introduce}");
                }

                WHERE(" id = #{id}");
            }

        }.toString();
    }
}
