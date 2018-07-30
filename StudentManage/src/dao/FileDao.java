package dao;

import dao.provider.FileProvder;
import domain.File;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface FileDao {

    //查询所有文件信息
    @Select("select * from file")
    List<File> selectAllFile();

    //模糊查询
    @SelectProvider(type = FileProvder.class,method = "selectByName")
    List<File> selectByName(String name);

    //动态插入
    @SelectProvider(type = FileProvder.class,method = "insertFile")
    void insertFile(File file);

    //根据id查询文件信息
    @Select("select * from file where id = #{id}")
    File selectByyId(String id);

    //根据id删除信息
    @Select("delete from file where id = #{id}")
    void deleteFile(String id);
}
