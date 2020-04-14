package com.example.zhaocong.luntan.controller;

import com.example.zhaocong.luntan.model.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;


@Controller
@Slf4j
public class FileController {

    private static final Logger logger= LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("file");

        if (files.size() <= 0 || files == null) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败,文件不能为空");
            return fileDTO;
        }
        for (MultipartFile file : files) {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                //MutipartFile转化为FILE
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(file.getOriginalFilename()));
                String fileName = file.getOriginalFilename();

                bis = new BufferedInputStream(new FileInputStream(new File(file.getOriginalFilename())));
                String outPut = "D://" + fileName;
                bos = new BufferedOutputStream(new FileOutputStream(new File(outPut)));
                int buffer;
                byte[] bytes = new byte[2048];
                while ((buffer = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, buffer);
                }
            } catch (Exception e) {
                logger.error("错误信息:" + e.getMessage());
                FileDTO fileDTO = new FileDTO();
                fileDTO.setSuccess(0);
                fileDTO.setMessage("上传失败");
                return fileDTO;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        return fileDTO;
    }
}
