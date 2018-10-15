package org.xgun.kissolive.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.xgun.kissolive.common.Const;

public class FTPSSMLoad {
    public static Map upload(MultipartFile file,HttpServletRequest request,String remotePath) {
        //获取本地下载路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        Integer i = fileName.lastIndexOf(".") + 1;
        String fileExtensionName = fileName.substring(i);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            //下载到本地
            file.transferTo(targetFile);
            boolean result = FTPUtil.uploadFile(remotePath,Lists.newArrayList(targetFile));
            targetFile.delete();
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFile.getName());
            fileMap.put("http_url",Const.HTTP_PREFIX+remotePath+targetFile.getName());
            fileMap.put("result",result);
            return fileMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map uploadQr(String remotePath, File targetFile){
        try {
            boolean result = FTPUtil.uploadFile(remotePath,Lists.newArrayList(targetFile));
            targetFile.delete();
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFile.getName());
            fileMap.put("http_url",Const.HTTP_PREFIX+remotePath+targetFile.getName());
            fileMap.put("result",result);
            return fileMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void download(HttpServletResponse response, String filePath, String fileName) {
        try {
            FTPUtil.downloadFile(filePath, fileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
