package service;


import com.github.pagehelper.PageInfo;
import domain.College;

import java.util.List;


public interface CollegeService {

    /**
     * 查询所有信息
     */
    PageInfo findAllCollege(int pageNum);

    /**
     * 模糊查询
     */
    PageInfo findCollegeByName(String name,int pageNum);

    /**
     * 根据id查询
     */
    College findCollegeById(String id);

    /**
     * 添加信息
     */
    void insertCollege(College college);

    /**
     * 修改信息
     */
    void updateCollege(College college);

    /**
     * 删除信息
     */
    void deleteCollege(String id);

    /**
     * 查询所有学院
     */
    List<College> selectAllCollege();

}
