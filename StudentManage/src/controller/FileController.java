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
     * 自动注入FileService
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
            //跳转到添加页面
            mv.setViewName("/file/fileAdd");
        }else {

            //上传文件路径
            String path = session.getServletContext().getRealPath("/upload");
            //上传文件名
            String fileName = file.getFile().getOriginalFilename();

            //将上传文件保存到一个目标文件中
            file.getFile().transferTo(new java.io.File(path+ java.io.File.separator+fileName));

            //设置文件名字
            file.setFileName(fileName);

            //插入数据库
            fileService.insertFile(file);

            mv.setViewName("redirect:/file/list");
        }
        return mv;
    }

    @RequestMapping("/file/downLoad")
    public ResponseEntity<byte[]> downLoad(String id,HttpSession session) throws IOException {
        //根据id查文档
        File file = fileService.selectById(id);
        String fileName = file.getFileName();

        //上传文件路径
        String path = session.getServletContext().getRealPath("/upload/");

        //获得要下载的File对象
        java.io.File file1 = new java.io.File(path+ java.io.File.separator+fileName);
        //创建springframework的HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();

        //下载显示的文件名字，中文乱码问题
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");

        //下载方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file1),headers,HttpStatus.CREATED);
    }

    @RequestMapping("/file/delete")
    public String delete(String ids){
        System.out.println(ids);
        //分解id字符串
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //根据id删除职位
            fileService.deleteFile(id);
        }
        return "redirect:/file/list";
    }
}
