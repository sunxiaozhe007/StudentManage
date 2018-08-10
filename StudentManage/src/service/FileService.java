package service;

import domain.File;

import java.util.List;

public interface FileService {

    /**
     * ��ѯ�����ļ���Ϣ
     */
    List<File> selectAllFile();

    /**
     * �����ļ���ģ����ѯ
     */
    List<File> selectByName(String name);

    /**
     * ��̬�����ļ���Ϣ
     */
    void insertFile(File file);

    /**
     * ����id��ѯ
     */
    File selectById(String id);

    /**
     * ����idɾ����Ϣ
     */
    void deleteFile(String id);
}
