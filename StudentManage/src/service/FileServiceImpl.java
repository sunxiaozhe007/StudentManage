package service;

import dao.FileDao;
import domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("fileService")
public class FileServiceImpl implements FileService {

    /**
     * 自动注入FileDao
     */
    @Autowired
    private FileDao fileDao;

    /**
     * 查询所有文件
     * @return
     */
    @Override
    public List<File> selectAllFile() {
        return fileDao.selectAllFile();
    }

    /**
     * 根据文件名模糊查询
     * @param name
     * @return
     */
    @Override
    public List<File> selectByName(String name) {
        return fileDao.selectByName(name);
    }

    /**
     * 动态插入信息
     * @param file
     */
    @Override
    public void insertFile(File file) {
        fileDao.insertFile(file);
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Override
    public File selectById(String id) {
        return fileDao.selectByyId(id);
    }

    /**
     * 根据id删除信息
     * @param id
     */
    @Override
    public void deleteFile(String id) {
        fileDao.deleteFile(id);
    }
}
