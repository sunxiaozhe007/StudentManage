package dao;

import dao.provider.MajorProvder;
import domain.Major;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface MajorDao {

    //��̬��ѯרҵ
    @SelectProvider(type = MajorProvder.class,method = "selectMajor")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "introduce", property = "introduce"),
            @Result(column = "college_id" , property = "college" , one = @One(select = "dao.CollegeDao.selectCollegeById" , fetchType = FetchType.EAGER)),
    })
    List<Major> selectMajor(Major major);

    //ɾ��רҵ
    @Select("delete from major where id = #{id}")
    void deleteMajorById(String id);

    //���רҵ
    @SelectProvider(type = MajorProvder.class,method = "insertMajor")
    void insertMajor(Major major);

    //�޸�רҵ
    @SelectProvider(type = MajorProvder.class,method = "updateMajor")
    void updateMajor(Major major);

    //����id��ѯרҵ
    @Select("select * from major where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "introduce", property = "introduce"),
            @Result(column = "college_id" , property = "college" , one = @One(select = "dao.CollegeDao.selectCollegeById" , fetchType = FetchType.EAGER)),
    })
    Major selectMajorById(String id);

    //����ѧԺid��ѯ����רҵ
    @Select("select * from major where college_id = #{id}")
    List<Major> selectMajorByCollege(String id);
}
