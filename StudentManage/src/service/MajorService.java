package service;

import com.github.pagehelper.PageInfo;
import domain.Major;

import java.util.List;

public interface MajorService {
    /**
     * 根据学院id查询所有专业
     */
    PageInfo selectByCollege(String id,int pageNum);

    /**
     * 显示所有信息
     */
    PageInfo selectAllMajor(Major major,int pageNum);

    List<Major> selectAllMajor(Major major);


    /**
     * 根据专业名模糊查询
     */
   PageInfo selectMajorByName(Major major,int pageNum);

   List<Major> selectMajorByName(Major major);

    /**
     * 添加专业
     */
    void insertMajor(Major major);

    /**
     * 删除专业
     */
    void deleteMajor(String id);

    /**
     * 根据id查询专业
     */
    Major selectById(String id);

    /**
     * 修改专业信息
     */
    void updateMajor(Major major);

}
