package net.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;



@Controller
@RequestMapping("/file")
public class ImgUpload {
    
    @RequestMapping("/toFile")
    //跳转到文件上传的JSP页面
    public String toFileUpload() {
        return "fileUpload";
    }
    
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file, 
            HttpServletRequest request, ModelMap model) {
        
        //获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("fileName:"+ fileName);
        
        //新文件名
        String newFileName = UUID.randomUUID()+fileName;
        
        //上传到什么地方
        String path = "e:/upload/";
        File f = new File(path);
        if(!f.exists())f.mkdirs();
        if(!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path+newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while((b=in.read())!=-1){
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("imgurl:"+ path+newFileName);
        //保存文件地址，用于JSP页面回显
        model.addAttribute("fileUrl", path+newFileName);  
        return "fileUpload";
    } 
    
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request,HttpServletResponse response) throws Exception{
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(cmr.isMultipart(request)){
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)(request);
            Iterator<String> files = mRequest.getFileNames();
            while(files.hasNext()){
                MultipartFile mFile = mRequest.getFile(files.next());
                if(mFile != null){
                    String fileName = UUID.randomUUID()+mFile.getOriginalFilename();
                    String path = "e:/upload/"+fileName;
                    
                    File localFile = new File(path);
                    mFile.transferTo(localFile);
                    request.setAttribute("fileUrl", path);
                }
            }
        }
        return "fileUpload";
    }  
} 