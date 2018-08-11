package service;

import dao.FileDao;
import domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("fileService")
public class FileServiceImpl implements FileService {

    /**
     * �Զ�ע��FileDao
     */
    @Autowired
    private FileDao fileDao;

    /**
     * ��ѯ�����ļ�
     * @return
     */
    @Override
    public List<File> selectAllFile() {
        return fileDao.selectAllFile();
    }

    /**
     * �����ļ���ģ����ѯ
     * @param name
     * @return
     */
    @Override
    public List<File> selectByName(String name) {
        return fileDao.selectByName(name);
    }

    /**
     * ��̬������Ϣ
     * @param file
     */
    @Override
    public void insertFile(File file) {
        fileDao.insertFile(file);
    }

    /**
     * ����id��ѯ��Ϣ
     * @param id
     * @return
     */
    @Override
    public File selectById(String id) {
        return fileDao.selectByyId(id);
    }

    /**
     * ����idɾ����Ϣ
     * @param id
     */
    @Override
    public void deleteFile(String id) {
        fileDao.deleteFile(id);
    }
}
