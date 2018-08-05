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
     * ע��Dao����
     * @return
     */
    @Autowired
    private CollegeDao collegeDao;

    int size = 12;


    /**
     * ��ҳ��ѯ����ѧԺ��Ϣ
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
     * ��ҳģ����ѯ
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
     * ����id��ѯѧԺ��Ϣ
     * @param id
     * @return
     */
    @Override
    public College findCollegeById(String id) {
        return collegeDao.selectCollegeById(id);
    }

    /**
     * ���ѧԺ��Ϣ
     * @param college
     */
    @Override
    public void insertCollege(College college) {
        collegeDao.insertCollege(college);
    }

    /**
     * �޸�ѧԺ��Ϣ
     * @param college
     */
    @Override
    public void updateCollege(College college) {
        collegeDao.updateCollege(college);
    }

    /**
     * ����idɾ����Ϣ
     * @param id
     */
    @Override
    public void deleteCollege(String id) {
        collegeDao.deleteCollege(id);
    }


    /**
     * ��ѯ����ѧԺ
     * @return
     */
    @Override
    public List<College> selectAllCollege() {
        return collegeDao.selectAllCollege();
    }


}
