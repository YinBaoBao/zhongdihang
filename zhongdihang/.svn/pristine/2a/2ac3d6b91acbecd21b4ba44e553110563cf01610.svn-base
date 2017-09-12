package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.modular.service.FileService;
import com.zhongdihang.bankdispatch.modular.service.dto.FileDto;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
@Api(description = "文件控制器")
public class FileController{
    @Value("${upLoad.FilePath}")
    private String filePath;
    @Value("${upLoad.FileFormat}")
    private String fileFormat;
    @Value("${upLoad.FileSize}")
    private Long fileSize;
    @Autowired
    private FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    //=======================================================
    //文件上传
    //======================================================
    @ApiOperation("单文件上传")
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty())
        {
            return ResultModel.error(ResultStatus.FAILURE,"没有找到文件");
        }
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String[] fileFormat_ = fileFormat.split(",");
        for (int i=0;i<fileFormat_.length;i++)
        {
            if (suffixName.equals(fileFormat_[i]))
            {
                break;
            }
            else if (!fileFormat_[fileFormat_.length-1].equals(suffixName))
            {
                return ResultModel.error(ResultStatus.FAILURE, "只接受"+fileFormat+"图片格式");
            }
        }
        if (file.getSize()>fileSize*1024*1024)
        {
            return ResultModel.error(ResultStatus.FAILURE, "目标文件大于"+fileSize+"MB");
        }
        SimpleDateFormat simpleDateFormatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
        String filePath_= simpleDateFormatYear.format(new Date())+"/"+
                simpleDateFormatMonth.format(new Date())+"/"+
                simpleDateFormatDay.format(new Date())+"/";
        String fileName = UUID.randomUUID() + suffixName;
        //=======================================================
        File dest = new File(filePath+filePath_ + fileName);
        com.zhongdihang.bankdispatch.modular.domain.File file1 = new com.zhongdihang.bankdispatch.modular.domain.File();
        file1.setFileName(fileName);
        file1.setOriginalFilename(originalFilename);
        file1.setRelativePath("/"+filePath_+fileName);
        file1.setAbsolutePath(filePath+filePath_+fileName);
        file1.setSuffixName(suffixName);
        fileService.saveFiel(file1);
        //====================================
        // 检测是否存在目录
        //====================================
        if (!dest.getParentFile().exists())
        {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return ResultModel.ok(new FileDto(file1.getId().toString(),file1.getRelativePath()));
    }

    @ApiOperation("文件下载")
    //=======================================
    //文件下载相关代码
    //=======================================
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam Long fileId){
        com.zhongdihang.bankdispatch.modular.domain.File fileM = fileService.findFile(fileId);
        String fileName =fileM.getFileName();
        if (fileName != null) {
            //===================================================================
            // 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)
            // 然后下载到C:\\users\\downloads即本机的默认下载的目录
            //===================================================================
           /* String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");*/
            File file = new File(fileM.getAbsolutePath(), fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    logger.info("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileName;
    }

    @ApiOperation("多文件上传")
    //==========================================
    //多文件上传
    //=========================================
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }


}