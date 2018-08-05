package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.CollegeDao;
import domain.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("collegeService")
public class CollegeServiceInpl implements CollegeService {

    /**
     * 注入Dao对象
     * @return
     */
    @Autowired
    private CollegeDao collegeDao;

    int size = 12;


    /**
     * 分页查询所有学院信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo findAllCollege(int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<College> colleges =  collegeDao.selectAllCollege();
        PageInfo pageInfo = new PageInfo(colleges);
        return pageInfo;
    }

    /**
     * 分页模糊查询
     * @param name
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo findCollegeByName(String name,int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<College> colleges = collegeDao.selectByName(name);
        PageInfo pageInfo = new PageInfo(colleges);
        return pageInfo;
    }

    /**
     * 根据id查询学院信息
     * @param id
     * @return
     */
    @Override
    public College findCollegeById(String id) {
        return collegeDao.selectCollegeById(id);
    }

    /**
     * 添加学院信息
     * @param college
     */
    @Override
    public void insertCollege(College college) {
        collegeDao.insertCollege(college);
    }

    /**
     * 修改学院信息
     * @param college
     */
    @Override
    public void updateCollege(College college) {
        collegeDao.updateCollege(college);
    }

    /**
     * 根据id删除信息
     * @param id
     */
    @Override
    public void deleteCollege(String id) {
        collegeDao.deleteCollege(id);
    }


    /**
     * 查询所有学院
     * @return
     */
    @Override
    public List<College> selectAllCollege() {
        return collegeDao.selectAllCollege();
    }


}
