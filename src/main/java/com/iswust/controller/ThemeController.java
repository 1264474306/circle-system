package com.iswust.controller;


import com.iswust.model.Theme;
import com.iswust.model.dto.ThemeList;
import com.iswust.serves.impl.ThemeServes;
import com.iswust.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/theme")
@RestController
public class ThemeController{
    @Autowired
    ThemeServes themeServes;

    @RequestMapping("/queryAll")
    public ResponseEntity<ResponseModel> themeGetAll(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();

        List<Theme> theme = themeServes.themeGetAll();

        ThemeList themes = new ThemeList(theme);

        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        responseModel.setData(themes);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }


    @RequestMapping("/add")
    public ResponseEntity<ResponseModel> themeAdd(@RequestBody Theme theme, HttpServletRequest request){
        if(theme.getIntroduce() == null || theme.getTopic() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("介绍introduce或标题topic为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        ResponseModel responseModel = new ResponseModel();

        themeServes.themeAdd(theme);

        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(theme);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @RequestMapping("/browse")
    public  ResponseEntity<ResponseModel> themeBrowse(@RequestBody Theme theme, HttpServletRequest request){
        if(theme.getId() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("话题id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(themeServes.themeGet(theme.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前话题id不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        Theme theme1 = themeServes.themeBrowse(theme);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(theme1);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}
