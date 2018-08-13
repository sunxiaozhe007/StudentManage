package controller;

import domain.File;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.FileService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    /**
     * �Զ�ע��FileService
     * @param model
     * @return
     */
    @Autowired
    @Qualifier("fileService")
    private FileService fileService;


    @RequestMapping("/file/list")
    public String list(Model model){

        List<File> files = fileService.selectAllFile();
        model.addAttribute("list",files);
        return "/file/file";
    }

    @RequestMapping("/file/select")
    public String select(String name,Model model){
        List<File> files = fileService.selectByName(name);
        model.addAttribute("list",files);
        return "/file/file";
    }

    @RequestMapping("/file/insert")
    public ModelAndView insert(String flag, @ModelAttribute File file, ModelAndView mv, HttpSession session) throws IOException {
        if (flag.equals("1")){
            //��ת�����ҳ��
            mv.setViewName("/file/fileAdd");
        }else {

            //�ϴ��ļ�·��
            String path = session.getServletContext().getRealPath("/upload");
            //�ϴ��ļ���
            String fileName = file.getFile().getOriginalFilename();

            //���ϴ��ļ����浽һ��Ŀ���ļ���
            file.getFile().transferTo(new java.io.File(path+ java.io.File.separator+fileName));

            //�����ļ�����
            file.setFileName(fileName);

            //�������ݿ�
            fileService.insertFile(file);

            mv.setViewName("redirect:/file/list");
        }
        return mv;
    }

    @RequestMapping("/file/downLoad")
    public ResponseEntity<byte[]> downLoad(String id,HttpSession session) throws IOException {
        //����id���ĵ�
        File file = fileService.selectById(id);
        String fileName = file.getFileName();

        //�ϴ��ļ�·��
        String path = session.getServletContext().getRealPath("/upload/");

        //���Ҫ���ص�File����
        java.io.File file1 = new java.io.File(path+ java.io.File.separator+fileName);
        //����springframework��HttpHeaders����
        HttpHeaders headers = new HttpHeaders();

        //������ʾ���ļ����֣�������������
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");

        //���ط�ʽ
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file1),headers,HttpStatus.CREATED);
    }

    @RequestMapping("/file/delete")
    public String delete(String ids){
        System.out.println(ids);
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��ְλ
            fileService.deleteFile(id);
        }
        return "redirect:/file/list";
    }
}
