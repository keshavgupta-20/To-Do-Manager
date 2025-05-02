package com.lcwd.todo.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.util.Arrays;


@RestController
@RequestMapping("/File")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);



    @PostMapping("/single")
    public  String  uploadSingle(@RequestParam("image")MultipartFile file){
        logger.info("Name : {} ", file.getName());
        logger.info("ContentType {} ",file.getContentType());
        logger.info("Original File Name {}", file.getOriginalFilename());
        logger.info("File size {}", file.getSize());

        //file.getInputStream
//        InputStream inputStream = file.getInputStream();
//        FileOutputStream fileOutputStream = new  FileOutputStream("data.png");
//        byte data[] = new byte[inputStream.available()];
//        fileOutputStream.write(data);

        return "File test";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile [] Files){
        Arrays.stream(Files).forEach(file -> {
            logger.info("Name : {} ", file.getName());
            logger.info("ContentType {} ",file.getContentType());
            logger.info("Original File Name {}", file.getOriginalFilename());
            logger.info("File size {}", file.getSize());
        });

        return "Handling multiple files";
    }

    @GetMapping("/images")
    public  void serveImageHandler(HttpServletResponse response){
        try {
            InputStream fileInputStream = new FileInputStream("images/donkey.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
