package com.ruoyi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/upload")
    public boolean test(@RequestParam("file") MultipartFile blobFile, @RequestParam("mail")String mail,@RequestParam("title")String title) throws Exception {

        String fileName = blobFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            blobFile.transferTo(file);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("1434165935@qq.com");
            helper.setTo(mail);
            helper.setText("");
            FileSystemResource files = new FileSystemResource(file);
            helper.setSubject(title+"导出");
            helper.addAttachment(title+".xlsx", files);
            mailSender.send(mimeMessage);
            file.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }




    }
}
