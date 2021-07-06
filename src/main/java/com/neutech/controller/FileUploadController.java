package com.neutech.controller;

import com.neutech.vo.ResultVO;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Value("${ftp.hostname}")
    private String hostname;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @PostMapping("/fileUpload")
    public ResultVO fileUpload(MultipartFile file){
        //前台不传此参数时有时是null有时不是null
        if(file==null||file.getSize()==0){
            return ResultVO.error(1006,"文件未选择");
        }
        FTPClient ftpClient=new FTPClient();
        try {
            ftpClient.connect(hostname,port);
            ftpClient.login(username,password);
            int replyCode=ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return ResultVO.error(1007,"ftp服务器连接失败");
            }
            ftpClient.setControlEncoding("utf-8");
//            ftpClient.enterLocalPassiveMode();
            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            boolean a=ftpClient.changeWorkingDirectory("/home/aubin");
            String fileName= UUID.randomUUID().toString()+
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            if(!ftpClient.storeFile(fileName,file.getInputStream())){
                return ResultVO.error(1009,"上传失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultVO.success();
    }
}

