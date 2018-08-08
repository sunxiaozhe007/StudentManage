package service;

import com.github.pagehelper.PageInfo;
import domain.Grade;

import java.util.List;

public interface GradeService {
    /**
     * ����רҵҲid��ѯ���а༶
     */
    PageInfo selectByMajor(String id,int pageNum);

    /**
     * ����idɾ���༶
     */
    void deleteById(String id);

    /**
     * ģ����ѯ
     */
    PageInfo selectGrade(Grade grade,int pageNum,String major_id);

    /**
     * ��Ӱ༶
     */
    void insertGrade(Grade grade);

    /**
     * �޸İ༶��Ϣ
     */

    void updateGrade(Grade grade);

    /**
     * ����id��ѯ�༶��Ϣ
     */
    Grade selectByGradeId(String id);

    /**
     * ��ѯ���б��༶��Ϣ
     */
    List<Grade> selectAllGrade();

}
