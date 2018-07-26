package dao.provider;

import domain.Grade;
import domain.Major;
import org.apache.ibatis.jdbc.SQL;

public class GradeProvder {

    //��̬��ѯ�༶��Ϣ
    public String selectGrade(Grade grade){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("grade");
                if (grade.getName() != null && !grade.getName().equals("")){
                    WHERE("name LIKE CONCAT ('%',#{name},'%')");
                }
                if (grade.getTeacher() != null && !grade.getTeacher().equals("")){
                    WHERE("teacher LIKE CONCAT ('%',#{teacher},'%')");
                }
                if (grade.getMajor() != null && grade.getMajor().getId() != 0){
                    WHERE("major_id LIKE CONCAT ('%',#{major.id},'%')");
                }
            }
        }.toString();
        return sql;
    }

    //��̬��Ӱ༶��Ϣ
    public String insertGrade(Grade grade){
        return new SQL(){
            {
                INSERT_INTO("grade");
                if (grade.getName() != null){
                    VALUES("name","#{name}");
                }
                if (grade.getTeacher() != null){
                    VALUES("teacher","#{teacher}");
                }
                if (grade.getMonitor() != null){
                    VALUES("monitor", "#{monitor}");
                }
                if (grade.getMajor() != null){
                    VALUES("major_id","#{major.id}");
                }
            }
        }.toString();
    }

    //��̬�޸���Ϣ
    public String updateGrade(Grade grade){
        return new SQL() {
            {
                UPDATE("grade");
                if (grade.getName() != null) {
                    SET("name = #{name}");
                }
                if (grade.getTeacher() != null) {
                    SET("teacher = #{teacher}");
                }
                if (grade.getMonitor() != null){
                    SET("monitor = #{monitor}");
                }
                if (grade.getMajor() != null){
                    SET("major_id = #{major.id}");
                }

                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
