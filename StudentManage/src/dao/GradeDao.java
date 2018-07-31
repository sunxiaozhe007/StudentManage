package dao;


import dao.provider.GradeProvder;
import domain.Grade;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GradeDao {
    //根据专业id查询所有班级
    @Select("select * from grade where major_id = #{id}")
    List<Grade> selectByMajor(String id);

    //根据id删除班级
    @Select("delete from grade where id = #{id}")
    void deleteById(String id);

    //动态查询
    @SelectProvider(type = GradeProvder.class,method = "selectGrade")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "teacher", property = "teacher"),
            @Result(column = "monitor", property = "monitor"),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
    })
    List<Grade> selectGrade(Grade grade);

    //动态添加
    @SelectProvider(type = GradeProvder.class,method ="insertGrade")
    void insertGrade(Grade grade);

    //动态修改
    @SelectProvider(type = GradeProvder.class,method = "updateGrade")
    void update(Grade grade);

    //根据id查询班级信息
    @Select("select * from grade where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "teacher", property = "teacher"),
            @Result(column = "monitor", property = "monitor"),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
    })
    Grade selectByGradeId(String id);

    //查询所有班级信息
    @Select("select * from grade")
    List<Grade> selectAllGrade();
}
