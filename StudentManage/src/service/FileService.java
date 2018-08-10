package service;

import domain.File;

import java.util.List;

public interface FileService {

    /**
     * 查询所有文件信息
     */
    List<File> selectAllFile();

    /**
     * 根据文件名模糊查询
     */
    List<File> selectByName(String name);

    /**
     * 动态插入文件信息
     */
    void insertFile(File file);

    /**
     * 根据id查询
     */
    File selectById(String id);

    /**
     * 根据id删除信息
     */
    void deleteFile(String id);
}
