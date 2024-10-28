package com.gjp.controller;

import com.gjp.configtest.GjpConfig;
import com.gjp.service.AService;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/a/test")
public class Acontroller {

    @Value("${gjp.testProperties}")
    String testProperties;

//    @Autowired
//    AService aService;
//
    @Autowired
    GjpConfig gjpConfig;


    @GetMapping("/getOneNum")
    public String getOneNum(){
        gjpConfig.getLabelName();
        System.out.println(gjpConfig.getLabelName());
        return testProperties;
    }

    @PostMapping("/getOneNum")
    public String getOneNumPost(){

        return "post";
    }

    @GetMapping("/forward2")
    public ResponseEntity<Resource> forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        // 构建请求的URI
        String url = "http://gips3.baidu.com/it/u=3886271102,3123389489&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960";
        // 根据需要发送不同的HTTP方法
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, Resource.class);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空，请重新上传";
        }

        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 处理每一行的代码逻辑
                System.out.println(line);
            }
            bufferedReader.close();

            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的字节
            byte[] bytes = file.getBytes();
            // 这里可以写代码将文件内容保存到服务器的指定目录
            return "文件上传成功：" + fileName;
        } catch (Exception e) {
            return "文件上传失败：" + e.getMessage();
        }
    }

}
