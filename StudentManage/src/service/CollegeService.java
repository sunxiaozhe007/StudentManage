package service;


import com.github.pagehelper.PageInfo;
import domain.College;

import java.util.List;


public interface CollegeService {

    /**
     * ��ѯ������Ϣ
     */
    PageInfo findAllCollege(int pageNum);

    /**
     * ģ����ѯ
     */
    PageInfo findCollegeByName(String name,int pageNum);

    /**
     * ����id��ѯ
     */
    College findCollegeById(String id);

    /**
     * �����Ϣ
     */
    void insertCollege(College college);

    /**
     * �޸���Ϣ
     */
    void updateCollege(College college);

    /**
     * ɾ����Ϣ
     */
    void deleteCollege(String id);

    /**
     * ��ѯ����ѧԺ
     */
    List<College> selectAllCollege();

}
