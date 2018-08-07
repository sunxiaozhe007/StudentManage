package service;

import com.github.pagehelper.PageInfo;
import domain.Major;

import java.util.List;

public interface MajorService {
    /**
     * ����ѧԺid��ѯ����רҵ
     */
    PageInfo selectByCollege(String id,int pageNum);

    /**
     * ��ʾ������Ϣ
     */
    PageInfo selectAllMajor(Major major,int pageNum);

    List<Major> selectAllMajor(Major major);


    /**
     * ����רҵ��ģ����ѯ
     */
   PageInfo selectMajorByName(Major major,int pageNum);

   List<Major> selectMajorByName(Major major);

    /**
     * ���רҵ
     */
    void insertMajor(Major major);

    /**
     * ɾ��רҵ
     */
    void deleteMajor(String id);

    /**
     * ����id��ѯרҵ
     */
    Major selectById(String id);

    /**
     * �޸�רҵ��Ϣ
     */
    void updateMajor(Major major);

}
