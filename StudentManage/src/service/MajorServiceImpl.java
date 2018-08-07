package service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.CollegeDao;
import dao.MajorDao;
import domain.College;
import domain.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("majorService")
public class MajorServiceImpl implements MajorService {

    /**
     * �Զ�ע��dao�־ò�
     */
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private CollegeDao collegeDao;
    int size = 12;


    /**
     * ����ѧԺid��ѯ����רҵ
     * @param id
     * @return
     */
    @Override
    public PageInfo selectByCollege(String id,int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<Major> majors = majorDao.selectMajorByCollege(id);
        College college = collegeDao.selectCollegeById(id);
        for (Major major : majors){
            major.setCollege(college);
        }
        PageInfo pageInfo = new PageInfo(majors);
        return pageInfo;
    }

    /**
     * ��ѯ������Ϣ
     * @return
     */
    @Override
    public PageInfo selectAllMajor(Major major, int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<Major> majors =  majorDao.selectMajor(major);
        PageInfo pageInfo = new PageInfo(majors);
        return pageInfo;
    }

    @Override
    public List<Major> selectAllMajor(Major major) {
        return majorDao.selectMajor(major);
    }

    /**
     * ����רҵ��ģ����ѯ
     * @param major
     * @return
     */
    @Override
    public PageInfo selectMajorByName(Major major,int pageNum) {
        PageHelper.startPage(pageNum,size);
        List<Major> majors =  majorDao.selectMajor(major);
        PageInfo pageInfo = new PageInfo(majors);
        return pageInfo;
    }

    @Override
    public List<Major> selectMajorByName(Major major) {
        return majorDao.selectMajor(major);
    }

    /**
     * ���רҵ
     * @param major
     * @return
     */
    @Override
    public void insertMajor(Major major) {
        majorDao.insertMajor(major);
    }

    /**
     * ����id�޸�רҵ
     * @param id
     */
    @Override
    public void deleteMajor(String id) {
        majorDao.deleteMajorById(id);
    }

    /**
     * ����id��ѯרҵ��Ϣ
     * @param id
     * @return
     */
    @Override
    public Major selectById(String id) {
        return majorDao.selectMajorById(id);
    }

    /**
     * �޸�רҵ��Ϣ
     * @param major
     */
    @Override
    public void updateMajor(Major major) {
        majorDao.updateMajor(major);
    }
}
