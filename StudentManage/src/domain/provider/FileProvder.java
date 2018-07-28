package dao.provider;

import domain.File;
import org.apache.ibatis.jdbc.SQL;

public class FileProvder {

    //根据名字模糊查询
    public String selectByName(String name){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("file");
                if (name != null && !name.equals("")){
                    WHERE("name LIKE CONCAT ('%',#{name},'%')");
                }
            }
        }.toString();
        return sql;
    }

    //动态插入文件信息
    //添加学院
    public String insertFile(File file){
        return new SQL(){
            {
                INSERT_INTO("file");
                if (file.getName() != null && !file.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if (file.getIntroduce() != null && !file.getIntroduce().equals("")){
                    VALUES("introduce","#{introduce}");
                }
                if (file.getFileName() != null && !file.getFileName().equals("")){
                    VALUES("filename","#{fileName}");
                }
            }
        }.toString();
    }
}
