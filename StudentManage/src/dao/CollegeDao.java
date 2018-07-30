package dao;


import dao.provider.CollegeProvder;
import domain.College;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CollegeDao {

    //��ѯ����ѧԺ
    @Select("select * from college ")
    List<College> selectAllCollege();

    //����id��ѯѧԺ
    @Select("select * from college where id = #{id}")
    College selectCollegeById(String id);

    //��������ģ����ѯѧԺ��Ϣ
    @SelectProvider(type = CollegeProvder.class,method = "selectByName")
    List<College> selectByName(String name);

    //���ѧԺ
    @SelectProvider(type = CollegeProvder.class,method = "insertCollege")
    void insertCollege(College college);

    //����id�޸�ѧԺ
    @SelectProvider(type = CollegeProvder.class,method = "updateCollege")
    void updateCollege(College college);

    //����idɾ��ѧԺ
    @Select("delete from college where id = #{id}")
    void deleteCollege(String id);


}
