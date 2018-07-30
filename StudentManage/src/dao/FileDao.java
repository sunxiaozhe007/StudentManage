package dao;

import dao.provider.FileProvder;
import domain.File;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface FileDao {

    //��ѯ�����ļ���Ϣ
    @Select("select * from file")
    List<File> selectAllFile();

    //ģ����ѯ
    @SelectProvider(type = FileProvder.class,method = "selectByName")
    List<File> selectByName(String name);

    //��̬����
    @SelectProvider(type = FileProvder.class,method = "insertFile")
    void insertFile(File file);

    //����id��ѯ�ļ���Ϣ
    @Select("select * from file where id = #{id}")
    File selectByyId(String id);

    //����idɾ����Ϣ
    @Select("delete from file where id = #{id}")
    void deleteFile(String id);
}
