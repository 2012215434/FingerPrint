package nth.FingerPrint.Files.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class FtpTest {  
    /** 
     * ��ftpд�ļ�(����) 
     */  
  
    public static void main(String[] args) {  
   
        // Ҫд����ļ�����  
        String fileContent = "hello world���������";  
        // ftp��¼�û���  
        String userName = "FP";  
        // ftp��¼����  
        String userPassword = "123";  
        // ftp��ַ  
        String server = "59.68.29.68";//ֱ��ip��ַ  
        // �������ļ�  
        String fileName = "ftp.txt";  
        // ָ��д���Ŀ¼  
        String path = "test";  
   
        FTPClient ftpClient = new FTPClient();  
        try {  
            InputStream is = null;  
            // 1.������  
            is = new ByteArrayInputStream(fileContent.getBytes());  
            // 2.���ӷ�����  
            ftpClient.connect(server);  
            // 3.��¼ftp  
            ftpClient.login(userName, userPassword);  
            // 4.ָ��д���Ŀ¼  
            ftpClient.changeWorkingDirectory(path);  
            // 5.д����  
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