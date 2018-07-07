package com.huawei.tdt.testplan.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huawei.tdt.common.authorization.constants.ResponseStatusEnum;
import com.huawei.tdt.common.model.ResponseResult;
import com.huawei.tdt.common.util.CommonUtil;
import com.huawei.tdt.testplan.config.FastDFSConfig;

/**
 * FileUploadController
 * 
 * @author lWX537094
 */
@RestController
public class FileUploadController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FastDFSConfig fastDFSConfig;

    /**
     * 保存上传的文件至文件系统服务期
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param targetFile 文件
     * @return 上传文件保存后的路径
     * @throws Exception 操作异常
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public ResponseResult<String> saveFile(@RequestParam(value = "targetFile", required = false) MultipartFile targetFile)
    {
        ResponseResult<String> result = new ResponseResult<String>();

        String fileName = null;
        String fileUrl = null;
        try
        {
            byte[] bytes = targetFile.getBytes();
            fileName = targetFile.getOriginalFilename();
            long fileLength = targetFile.getSize();

            FileUploadHandler fileUploadHandler = FileUploadHandler.getFileUploadHandler();
            String[] results = fileUploadHandler.uploadFile(bytes, fileName, fileLength);
            if (null != results)
            {
                fileUrl = "http://" + fastDFSConfig.getNginxTrackerHost() + ":" + fastDFSConfig.getNginxTrackerPort()
                        + "/" + results[0] + "/" + results[1];
                result.setData(fileUrl);
            }
            else
            {
                result.setStatus(ResponseStatusEnum.FAIL);
                result.setCode("保存文件失败。");
            }
        }
        catch (Exception e)
        {
            result.setStatus(ResponseStatusEnum.FAIL);
            result.setCode("保存文件失败。");

            LOGGER.error("上传文件失败。" + fileName, e);
        }

        return result;
    }
    
    /**
     * 保存上传的文件至文件系统服务器
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return 上传文件保存后的路径
     * @throws Exception 操作异常
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public ResponseResult<String> saveFile(HttpServletRequest request, HttpServletResponse response)
    {
        ResponseResult<String> result = new ResponseResult<String>();

        String fileName = null;
        String fileUrl = null;
        long fileLength = 0;
        try
        {
            Collection<Part> parts = request.getParts();
            if (CommonUtil.isEmpty(parts))
            {
                throw new Exception("文件为空。");
            }
            
            Part part = parts.iterator().next();
            fileName = part.getSubmittedFileName();
            fileLength = part.getSize();

            byte[] bytes = toByteArray(part.getInputStream(), (int) fileLength);
            
            FileUploadHandler fileUploadHandler = FileUploadHandler.getFileUploadHandler();
            String[] results = fileUploadHandler.uploadFile(bytes, fileName, fileLength);
            if (null != results)
            {
                fileUrl = "http://" + fastDFSConfig.getNginxTrackerHost() + ":" + fastDFSConfig.getNginxTrackerPort()
                        + "/" + results[0] + "/" + results[1];
                result.setData(fileUrl);
            }
            else
            {
                result.setStatus(ResponseStatusEnum.FAIL);
                result.setCode("上传文件失败。");
            }
        }
        catch (Exception e)
        {
            result.setStatus(ResponseStatusEnum.FAIL);
            result.setCode("上传文件失败。");

            LOGGER.error("上传文件失败。" + fileName, e);
        }

        return result;
    }
    
    private byte[] toByteArray(InputStream inputStream, int length) throws Exception
    {

        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
        BufferedInputStream in = null;
        try
        {
            in = new BufferedInputStream(inputStream);
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize)))
            {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
        finally
        {
            CommonUtil.closeInputStream(in);
            CommonUtil.closeOutputStream(bos);
        }
    }
}
