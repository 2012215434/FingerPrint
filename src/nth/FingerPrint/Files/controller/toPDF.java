package nth.FingerPrint.Files.controller;

import java.io.File;  

import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.ComThread;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  
/*
 * 
�����ô���
�����ô���
�����ô���
�����ô���
*
*/
public class toPDF {
   static final int wdFormatPDF = 17;// PDF ��ʽ    
    public void wordToPDF(String sfileName,String toFileName){    
            
        System.out.println("����Word...");      
        long start = System.currentTimeMillis();      
        ActiveXComponent app = null;  
        Dispatch doc = null;  
        try {      
            app = new ActiveXComponent("Word.Application");      
           app.setProperty("Visible", new Variant(false));  
            Dispatch docs = app.getProperty("Documents").toDispatch();   
//          doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();   
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] {                    
               sfileName, new Variant(false),new Variant(true) }, new int[1]).toDispatch();               
            System.out.println("���ĵ�..." + sfileName);  
            System.out.println("ת���ĵ���PDF..." + toFileName);      
            File tofile = new File(toFileName);      
           if (tofile.exists()) {      
               tofile.delete();      
            }        
//          Dispatch.call(doc, "SaveAs",  destFile,  17);                    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {                
                toFileName, new Variant(17) }, new int[1]);    
            long end = System.currentTimeMillis();  //ʱ���    
            System.out.println("ת�����..��ʱ��" + (end - start) + "ms.");                
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:�ĵ�ת��ʧ�ܣ�" + e.getMessage());      
        } finally {  
            Dispatch.call(doc,"Close",false);  
            System.out.println("�ر��ĵ�");  
            if (app != null)      
                app.invoke("Quit", new Variant[] {});      
            }  
          //���û����仰,winword.exe���̽�����ر�  
           ComThread.Release();     
    }  
    public static void main(String[] args) {  
    	toPDF d = new toPDF();  
        d.wordToPDF("D:\\ftp\\android_vo.xls", "D:\\ftp\\ppt02.pdf");  
    }  
  
}  
