package nth.FingerPrint.Files.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class FtpTest {  
    /** 
     * 向ftp写文件(数据) 
     */  
  
    public static void main(String[] args) {  
   
        // 要写入的文件内容  
        String fileContent = "hello world，你好世界";  
        // ftp登录用户名  
        String userName = "FP";  
        // ftp登录密码  
        String userPassword = "123";  
        // ftp地址  
        String server = "59.68.29.68";//直接ip地址  
        // 创建的文件  
        String fileName = "ftp.txt";  
        // 指定写入的目录  
        String path = "test";  
   
        FTPClient ftpClient = new FTPClient();  
        try {  
            InputStream is = null;  
            // 1.输入流  
            is = new ByteArrayInputStream(fileContent.getBytes());  
            // 2.连接服务器  
            ftpClient.connect(server);  
            // 3.登录ftp  
            ftpClient.login(userName, userPassword);  
            // 4.指定写入的目录  
            ftpClient.changeWorkingDirectory(path);  
            // 5.写操作  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftpClient.storeFile(new String(fileName.getBytes("utf-8"),  
                    "iso-8859-1"), is);  
            is.close();  
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
}