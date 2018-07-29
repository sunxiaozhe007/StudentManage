package dao.provider;

import domain.Student;
import org.apache.ibatis.jdbc.SQL;


public class StudentProovder {

    //模糊查询学生
    public String selectStudent(Student student){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("student");
                if (student.getName() != null && !student.getName().equals("")){
                    WHERE("name LIKE CONCAT ('%',#{name},'%')");
                }

                if (student.getNum() != null && !student.getNum().equals("")){
                    WHERE("num LIKE CONCAT ('%',#{num},'%')");
                }

                if (student.getGrade() != null && student.getGrade().getId()!= 0){
                    WHERE("grade_id LIKE CONCAT ('%',#{grade.id},'%')");
                }
                if (student.getMajor() != null && student.getMajor().getId()!= 0){
                    WHERE("major_id LIKE CONCAT ('%',#{major.id},'%')");
                }
                if (student.getCollege() != null && student.getCollege().getId()!= 0){
                    WHERE("college_id LIKE CONCAT ('%',#{college.id},'%')");
                }
            }
        }.toString();
        return sql;
    }

    //动态插入学生信息
    public String insertStudent(Student student){
        return new SQL(){
            {
                INSERT_INTO("student");
                if (student.getName() != null){
                    VALUES("name","#{name}");
                }
                if (student.getNum() != null){
                    VALUES("num","#{num}");
                }
                if (student.getSex() != null){
                    VALUES("sex","#{sex}");
                }
                if (student.getAge() != null){
                    VALUES("age","#{age}");
                }
                if (student.getBirthday() != null){
                    VALUES("birthday","#{birthday}");
                }
                if (student.getNation() != null){
                    VALUES("nation","#{nation}");
                }
                if (student.getPhone() != null){
                    VALUES("phone","#{phone}");
                }
                if (student.getPol() != null){
                    VALUES("pol","#{pol}");
                }
                if (student.getNatives() != null){
                    VALUES("natives","#{natives}");
                }
                if (student.getSchool() != null){
                    VALUES("school","#{school}");
                }
                if (student.getPhoto() != null){
                    VALUES("photo","#{photo}");
                }
                if (student.getSpec() != null){
                    VALUES("spec","#{spec}");
                }
                if (student.getHobby() != null){
                    VALUES("hobby","#{hobby}");
                }
                if (student.getRemark() != null){
                    VALUES("remark","#{remark}");
                }
                if (student.getCollege() != null){
                    VALUES("college_id","#{college.id}");
                }
                if (student.getMajor() != null){
                    VALUES("major_id","#{major.id}");
                }
                if (student.getGrade() != null){
                    VALUES("grade_id","#{grade.id}");
                }

            }
        }.toString();
    }

    //动态修改学生信息
    public String updateStudent(Student student){
        return new SQL(){
            {
                UPDATE("student");
                if (student.getName() != null){
                    SET("name = #{name}");
                }
                if (student.getNum() != null){
                    SET("num = #{num}");
                }
                if (student.getSex() != null){
                    SET("sex = #{sex}");
                }
                if (student.getAge() != null){
                    SET("age = #{age}");
                }
                if (student.getBirthday() != null){
                    SET("birthday = #{birthday}");
                }
                if (student.getNation() != null){
                    SET("nation = #{nation}");
                }
                if (student.getPhone() != null){
                    SET("phone = #{phone}");
                }
                if (student.getPol() != null){
                SET("pol = #{pol}");
                }
                if (student.getNatives() != null){
                    SET("natives = #{natives}");
                }
                if (student.getPhoto() != null){
                    SET("photo = #{photo}");
                }
                if (student.getSpec() != null){
                    SET("spec = #{spec}");
                }
                if (student.getHobby() != null){
                    SET("hobby = #{hobby}");
                }
                if (student.getRemark() != null){
                    SET("remark = #{remark}");
                }
                if (student.getGrade() != null){
                    SET("grade_id = #{grade.id}");
                }
                if (student.getMajor() != null){
                    SET("major_id = #{major.id}");
                }
                if (student.getCollege() != null){
                    SET("college_id = #{college.id}");
                }
            }
        }.toString();
    }

}
