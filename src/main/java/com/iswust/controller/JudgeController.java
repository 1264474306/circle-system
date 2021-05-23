package com.iswust.controller;

import com.iswust.model.Judge;
import com.iswust.model.dto.PostJudge;
import com.iswust.serves.IJudgeServes;
import com.iswust.serves.IPostServes;
import com.iswust.serves.IUserServes;
import com.iswust.util.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RequestMapping("/judge")
@RestController
public class JudgeController {
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.uploadImage}")
    private String uploadImage;
    @Value("${file.uploadReportImage}")
    private String uploadReportImage;
    @Value("${file.prefix}")
    private String prefix;

    @Autowired
    IPostServes postServes;
    @Autowired
    IUserServes userServes;
    @Autowired
    IJudgeServes judgeServes;

    /**
     * 上传图片
     * @param id
     * @param multipartFiles
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/image")
    public ResponseEntity<ResponseModel> judgeImage (Integer id, MultipartFile[] multipartFiles, HttpServletRequest request) throws IOException {
//        id = (Integer) request.getSession().getAttribute("judge_id");

        if(id == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("judge_id为空");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(null == judgeServes.judgeFindById(id)){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前judge_id不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        StringBuffer pic_path = new StringBuffer();
        for (MultipartFile multipartFile : multipartFiles) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File( uploadFolder + uploadReportImage + originalFilename));//写入图片
            pic_path.append(prefix + staticAccessPath + originalFilename + ";");//保存地址
        }

        judgeServes.judgeSavePath(pic_path, id);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(pic_path);//返回地址
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 删除
     * @param judge
     * @return
     */
    @RequestMapping("/delete")
    public ResponseEntity<ResponseModel> judgeDelete(@RequestBody Judge judge, HttpServletRequest request) {
        System.out.println(judge.getId());

        if (judge.getId() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(judgeServes.judgeFindById(judge.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        String pic_paths = judgeServes.judgeFindPathById(judge.getId());
        if(pic_paths != null){
            String[] paths = pic_paths.split(";");
            for(String path : paths){
                String filename = StringUtils.substringAfterLast(path,"/");
                new File(uploadFolder + uploadReportImage + "/" + filename).delete();
            }
        }

        judgeServes.judgeDelete(judge);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }


    /**
     * 举报
     * @param judge
     * @param request
     * @return
     */
    @RequestMapping("/report")
    public ResponseEntity<ResponseModel> judgeReport(@RequestBody Judge judge, HttpServletRequest request){
        if(judge.getLaunch_uid() == null || judge.getPost_id() == null || judge.getReceive_uid() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("发起人launch_uid或贴子post_id或接受人receive_uid为空");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(userServes.userFindById(judge.getLaunch_uid()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("发起人launch_uid不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(userServes.userFindById(judge.getReceive_uid()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("接收人receive_uid不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(postServes.postFindById(judge.getPost_id()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子post_id不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        judgeServes.judgeReport(judge);
        request.getSession().setAttribute("judge_id",judge.getId());

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setData(judge);
        responseModel.setMessage("操作成功");

        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 管理员完成审核提交
     * @param judge
     * @param request
     * @return
     */
    @RequestMapping("/commit")
    public ResponseEntity<ResponseModel> judgeCommit(@RequestBody Judge judge, HttpServletRequest request){
        if(judge.getId() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("评价id为空");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(judgeServes.judgeFindById(judge.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("此id评价不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        judgeServes.judgeUpdate(judge);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");

        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 机器拦截后的审核  暂时无用
     * @param judge
     * @param request
     * @return
     */
    @RequestMapping("/applyJudge")
    public ResponseEntity<ResponseModel> judgeApplyJudge(@RequestBody Judge judge, HttpServletRequest request){
        if(judge.getId() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("评价id为空");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(judgeServes.judgeFindById(judge.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("此id评价不存在");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        judgeServes.judgeUpdate(judge);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");

        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 获取评价
     * @param judge
     * @param request
     * @return
     */
    @RequestMapping("/query")
    public ResponseEntity<ResponseModel> judgeGet(@RequestBody Judge judge, HttpServletRequest request){
        if(judge.getId() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("评价id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(judgeServes.judgeFindById(judge.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前审核评价id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        PostJudge postJudge = judgeServes.judgeGet(judge);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setData(postJudge);
        responseModel.setMessage("操作成功");

        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}
