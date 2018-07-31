package dao;


import dao.provider.GradeProvder;
import domain.Grade;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GradeDao {
    //����רҵid��ѯ���а༶
    @Select("select * from grade where major_id = #{id}")
    List<Grade> selectByMajor(String id);

    //����idɾ���༶
    @Select("delete from grade where id = #{id}")
    void deleteById(String id);

    //��̬��ѯ
    @SelectProvider(type = GradeProvder.class,method = "selectGrade")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "teacher", property = "teacher"),
            @Result(column = "monitor", property = "monitor"),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
    })
    List<Grade> selectGrade(Grade grade);

    //��̬���
    @SelectProvider(type = GradeProvder.class,method ="insertGrade")
    void insertGrade(Grade grade);

    //��̬�޸�
    @SelectProvider(type = GradeProvder.class,method = "updateGrade")
    void update(Grade grade);

    //����id��ѯ�༶��Ϣ
    @Select("select * from grade where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "teacher", property = "teacher"),
            @Result(column = "monitor", property = "monitor"),
            @Result(column = "major_id" , property = "major" , one = @One(select = "dao.MajorDao.selectMajorById" , fetchType = FetchType.EAGER)),
    })
    Grade selectByGradeId(String id);

    //��ѯ���а༶��Ϣ
    @Select("select * from grade")
    List<Grade> selectAllGrade();
}
