package com.godric.cms.controller;

import com.godric.cms.common.dto.FileUploadRespInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Godric
 */
@Slf4j
@Validated
@RestController
@RequestMapping("file")
public class FileUploadController {

    private static final String UPLOAD_PATH_PREFIX = "upload/";

    @PostMapping("upload")
    public ResultMessage<FileUploadRespInfoDTO> fileUpload(@NotNull @RequestParam MultipartFile multipartFile,
                                                           HttpServletRequest request) {
        log.info("you have in the fileUpload method....");
        if(multipartFile.isEmpty()){
            return ResultMessage.fail("上传文件为空");
        }
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = "src/main/resources/static/" + UPLOAD_PATH_PREFIX;
        log.info("-----------上传文件保存的路径【"+ realPath +"】-----------");
        //存放上传文件的文件夹
        File file = new File(realPath);
        log.info("-----------存放上传文件的文件夹【"+ file +"】-----------");
        log.info("-----------输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径【"+ file.getAbsolutePath() +"】-----------");
        if(!file.isDirectory()){
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = multipartFile.getOriginalFilename();
        log.info("-----------文件原始的名字【"+ oldName +"】-----------");
        String newName = UUID.randomUUID().toString() + (oldName == null ? "" : oldName.substring(oldName.lastIndexOf(".")));
        log.info("-----------文件要保存后的新名字【"+ newName +"】-----------");
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            multipartFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + UPLOAD_PATH_PREFIX + newName;
            log.info("-----------【"+ filePath +"】-----------");
            FileUploadRespInfoDTO result = new FileUploadRespInfoDTO(filePath);
            return ResultMessage.success(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultMessage.fail("上传失败！");
    }

}
