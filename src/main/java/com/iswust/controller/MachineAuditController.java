package com.iswust.controller;

import com.iswust.model.Post;
import com.iswust.util.ReaderWriteUtil;
import com.iswust.util.SensitiveServiceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 机器审查
 */
@RequestMapping("/commit")
@Controller
public class MachineAuditController {

    @Value("${file.sensitiveContent}")
    String contentPath;

    @RequestMapping(value = "check")
    public String ContentCheck(@RequestBody Post post, HttpServletRequest request) throws IOException {
        request.setAttribute("content",post.getContent());//从post中取出数据传给request
        request.setAttribute("uid",post.getUid());
        request.setAttribute("theme_id",post.getTheme_id());
        request.setAttribute("topic",post.getTopic());

        ArrayList<String> sensitiveWord = ReaderWriteUtil.readFile(contentPath);//读取敏感文本内容

        SensitiveServiceUtil sensitiveServiceUtil = new SensitiveServiceUtil();
        for(String word : sensitiveWord){//添加敏感词
            word = word.trim();
            sensitiveServiceUtil.addWord(word);
//            System.out.println(word);
//            System.out.println(post.getContent());
        }

        ReaderWriteUtil.writeFile(sensitiveWord,contentPath);//向磁盘写入敏感文本

        if(sensitiveServiceUtil.filter(post.getContent()) == 0){//判断是否敏感
            return "forward:/post/commit";
        } else{
            return "forward:/post/test";
        }

    }


}
