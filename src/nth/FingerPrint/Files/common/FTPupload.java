package nth.FingerPrint.Files.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import nth.FingerPrint.print.Controller.Print009;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FTPupload {  
    /** 
     * 向ftp写文件(数据) 
     */  
  
    public static void uploadFile(InputStream in,String fileName) {  
   
        // ftp登录用户名  
        String userName = "FP";  
        // ftp登录密码  
        String userPassword = "123";  
        // ftp地址  
        String server = "59.68.29.68";//直接ip地址  
        // 创建的文件  
       
        // 指定写入的目录  
        String path = "test";  
   
        FTPClient ftpClient = new FTPClient();  
        try {   
            // 2.连接服务器  
            ftpClient.connect(server);  
            // 3.登录ftp  
            ftpClient.login(userName, userPassword);  
            // 4.指定写入的目录  
            ftpClient.changeWorkingDirectory(path);  
            // 5.写操作  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftpClient.storeFile(new String(fileName.getBytes("utf-8"),  
                    "iso-8859-1"), in); 
            in.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (ftpClient.isConnected()) {  
                try {  
                    ftpClient.disconnect();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
    public static void testDownload(String path) { 
    	
    	String remoteFileName=path.substring(path.lastIndexOf('/')+1,path.length());
    	String directory=path.substring(0,path.lastIndexOf('/'));
    	directory=path.substring(directory.lastIndexOf('/')+1,directory.length());
    	String localPath="C:\\ftpTest\\"+remoteFileName;
    	String printPath="C:/ftpTest/"+remoteFileName;
    	
    	// ftp登录用户名  
        String userName = "FP";  
        // ftp登录密码  
        String userPassword = "123";  
        // ftp地址  
        String server = "59.68.29.68";//直接ip地址  
        // 创建的文件   
        FileOutputStream fos=null;
        FTPClient ftpClient = new FTPClient();  
        try {   
            // 2.连接服务器  
            ftpClient.connect(server);  
            // 3.登录ftp  
            ftpClient.login(userName, userPassword);  
            ftpClient.changeWorkingDirectory(directory);  

            fos = new FileOutputStream(localPath); 

            ftpClient.setBufferSize(1024); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.retrieveFile(remoteFileName, fos); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        } 
      Print009.printPDF(printPath);
    } 
    
  /* public static void main(String[] args){
    	testDownload("ftp://59.68.29.68/test/20141219-1ce28757-fb9f-4485-8152-7266368c67c0.pdf");
   }*/
}