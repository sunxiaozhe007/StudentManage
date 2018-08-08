package service;

import com.github.pagehelper.PageInfo;
import domain.Grade;

import java.util.List;

public interface GradeService {
    /**
     * 根据专业也id查询所有班级
     */
    PageInfo selectByMajor(String id,int pageNum);

    /**
     * 根据id删除班级
     */
    void deleteById(String id);

    /**
     * 模糊查询
     */
    PageInfo selectGrade(Grade grade,int pageNum,String major_id);

    /**
     * 添加班级
     */
    void insertGrade(Grade grade);

    /**
     * 修改班级信息
     */

    void updateGrade(Grade grade);

    /**
     * 根据id查询班级信息
     */
    Grade selectByGradeId(String id);

    /**
     * 查询所有本班级信息
     */
    List<Grade> selectAllGrade();

}
