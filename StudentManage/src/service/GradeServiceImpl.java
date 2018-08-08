package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.GradeDao;
import dao.MajorDao;
import domain.Grade;
import domain.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("gradeService")
public class GradeServiceImpl implements GradeService {
    /**
     *自动注入dao层
     */
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private MajorDao majorDao;
    int size = 12;

    /**
     * 根据专业id查询所有班级
     * @param id
     * @return
     */
    @Override
    public PageInfo selectByMajor(String id,int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<Grade> grades = gradeDao.selectByMajor(id);
        Major major = majorDao.selectMajorById(id);
        for (Grade grade : grades){
            grade.setMajor(major);
        }
        PageInfo pageInfo = new PageInfo(grades);
        return pageInfo;
    }

    /**
     * 根据id删除班级
     * @param id
     */
    @Override
    public void deleteById(String id) {
        gradeDao.deleteById(id);
    }

    /**
     * 模糊查询班级信息
     * @param grade
     * @return
     */
    @Override
    public PageInfo selectGrade(Grade grade,int pageNum,String major_id) {
        PageHelper.startPage(pageNum,size);
        Major major = majorDao.selectMajorById(major_id);
        grade.setMajor(major);
        List<Grade> grades = gradeDao.selectGrade(grade);
        PageInfo pageInfo = new PageInfo(grades);
        return pageInfo;
    }

    /**
     * 添加班级信息
     * @param grade
     */
    @Override
    public void insertGrade(Grade grade) {
        gradeDao.insertGrade(grade);
    }

    /**
     * 动态修改该班级信息
     * @param grade
     */
    @Override
    public void updateGrade(Grade grade) {
        gradeDao.update(grade);
    }

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    @Override
    public Grade selectByGradeId(String id) {
        return gradeDao.selectByGradeId(id);
    }


    /**
     * 查询所有班级信息
     * @return
     */
    @Override
    public List<Grade> selectAllGrade() {
        return gradeDao.selectAllGrade();
    }
}
