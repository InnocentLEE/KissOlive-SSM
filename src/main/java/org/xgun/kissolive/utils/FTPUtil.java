package org.xgun.kissolive.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xgun.kissolive.common.Const;


/**
 * FTP文件服务器工具类
 * 
 * @author Lee
 *
 */
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = Const.FTP_SERVER_IP;
    private static String ftpUser = Const.FTP_USERNAME;
    private static String ftpPass = Const.FTP_PASSWORD;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static void downloadFile(String remotePath, String fileName, HttpServletResponse response)
            throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        byte[] buffer = null;
        try {
            buffer = ftpUtil.downFileByte(remotePath,fileName);// 根据文件名下载FTP服务器上的文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * response.reset(); response.setContentType("text/html;charset=UTF-8");
         * response.addHeader("Content-Disposition", "attachment;filename=" + new
         * String(fileName.getBytes(), "ISO-8859-1")); OutputStream toClient = new
         * BufferedOutputStream(response.getOutputStream());
         * response.setContentType("application/octet-stream"); toClient.write(buffer);
         * toClient.flush(); toClient.close();
         */
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + buffer.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
        response.flushBuffer();
    }

    public byte[] downFileByte(String remotePath,String fileName) throws IOException {
        byte[] return_arraybyte = null;
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                FTPFile[] files = ftpClient.listFiles();
                for (FTPFile file : files) {
                    if (file.getName().equals(fileName)) {
                        InputStream ins = ftpClient.retrieveFileStream(file.getName());
                        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                        byte[] buf = new byte[204800 * 1024];
                        int bufsize = 0;
                        while ((bufsize = ins.read(buf, 0, buf.length)) != -1) {
                            byteOut.write(buf, 0, bufsize);
                        }
                        return_arraybyte = byteOut.toByteArray();
                        byteOut.close();
                        ins.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ftpClient.disconnect();
            }
        }
        return return_arraybyte;
    }

    public static boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.upload(remotePath, fileList);
        logger.info("开始连接ftp服务器,结束上传,上传结果:{}",result);
        return result;
    }

    private boolean upload(String dir, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        String realdir = dir;
        // 连接FTP服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                String d;
                if (!StringExtend.isNullOrEmpty(dir)) {
                    // 目录编码，解决中文路径问题
                    d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
                    // 尝试切入目录
                    if (!ftpClient.changeWorkingDirectory(d)) {
                        dir = StringExtend.trimStart(dir, "/");
                        dir = StringExtend.trimEnd(dir, "/");
                        String[] arr = dir.split("/");
                        StringBuffer sbfDir = new StringBuffer();
                        // 循环生成子目录
                        for (String s : arr) {
                            sbfDir.append("/");
                            sbfDir.append(s);
                            // 目录编码，解决中文路径问题
                            d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                            // 尝试切入目录
                            if (ftpClient.changeWorkingDirectory(d))
                                continue;
                            if (!ftpClient.makeDirectory(d)) {
                                System.out.println("[失败]ftp创建目录：" + sbfDir.toString());
                                return false;
                            }
                            System.out.println("[成功]创建ftp目录：" + sbfDir.toString());
                        }
                    }
                }
                System.out.println("上传目录为："+realdir);
                // 将目录切换至指定路径
                ftpClient.changeWorkingDirectory(realdir);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常", e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    private boolean connectServer(String ip, int port, String user, String pwd) {

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
