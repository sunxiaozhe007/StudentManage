package dao;


import dao.provider.CollegeProvder;
import domain.College;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CollegeDao {

    //查询所有学院
    @Select("select * from college ")
    List<College> selectAllCollege();

    //根据id查询学院
    @Select("select * from college where id = #{id}")
    College selectCollegeById(String id);

    //根据名字模糊查询学院信息
    @SelectProvider(type = CollegeProvder.class,method = "selectByName")
    List<College> selectByName(String name);

    //添加学院
    @SelectProvider(type = CollegeProvder.class,method = "insertCollege")
    void insertCollege(College college);

    //根据id修改学院
    @SelectProvider(type = CollegeProvder.class,method = "updateCollege")
    void updateCollege(College college);

    //根据id删除学院
    @Select("delete from college where id = #{id}")
    void deleteCollege(String id);


}
