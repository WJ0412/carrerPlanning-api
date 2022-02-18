package com.mars.controller;
/*
    下载控制器：用于下载供上传的.xls模板文件
 */
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@CrossOrigin
@Controller
@RequestMapping("user")
public class FileDownloadController {
    @GetMapping("/fileDownload")
    public void fileDownload(HttpServletResponse response, String fileName) throws Exception {
        /*ClassPathResource resource = new ClassPathResource("static/excel/"+fileName);
        File f = resource.getFile();
       // File f = new File(filePath+"/"+fileName);
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(f.exists());
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        if (!f.exists()) {
            System.out.println(f.getAbsolutePath());
            response.sendError(404, "the file to download not exist!");
            return;
        }*/
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("static/excel/" + fileName);
        BufferedInputStream br = new BufferedInputStream(fis);
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
    }
}
