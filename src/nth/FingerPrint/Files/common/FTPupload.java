package nth.FingerPrint.Files.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import nth.FingerPrint.print.Controller.Print009;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FTPupload {  
    /** 
     * ��ftpд�ļ�(����) 
     */  
  
    public static void uploadFile(InputStream in,String fileName) {  
   
        // ftp��¼�û���  
        String userName = "FP";  
        // ftp��¼����  
        String userPassword = "123";  
        // ftp��ַ  
        String server = "59.68.29.68";//ֱ��ip��ַ  
        // �������ļ�  
       
        // ָ��д���Ŀ¼  
        String path = "test";  
   
        FTPClient ftpClient = new FTPClient();  
        try {   
            // 2.���ӷ�����  
            ftpClient.connect(server);  
            // 3.��¼ftp  
            ftpClient.login(userName, userPassword);  
            // 4.ָ��д���Ŀ¼  
            ftpClient.changeWorkingDirectory(path);  
            // 5.д����  
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
    	
    	// ftp��¼�û���  
        String userName = "FP";  
        // ftp��¼����  
        String userPassword = "123";  
        // ftp��ַ  
        String server = "59.68.29.68";//ֱ��ip��ַ  
        // �������ļ�   
        FileOutputStream fos=null;
        FTPClient ftpClient = new FTPClient();  
        try {   
            // 2.���ӷ�����  
            ftpClient.connect(server);  
            // 3.��¼ftp  
            ftpClient.login(userName, userPassword);  
            ftpClient.changeWorkingDirectory(directory);  

            fos = new FileOutputStream(localPath); 

            ftpClient.setBufferSize(1024); 
            //�����ļ����ͣ������ƣ� 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.retrieveFile(remoteFileName, fos); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP�ͻ��˳���", e); 
        } finally { 
            IOUtils.closeQuietly(fos); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("�ر�FTP���ӷ����쳣��", e); 
            } 
        } 
      Print009.printPDF(printPath);
    } 
    
  /* public static void main(String[] args){
    	testDownload("ftp://59.68.29.68/test/20141219-1ce28757-fb9f-4485-8152-7266368c67c0.pdf");
   }*/
}