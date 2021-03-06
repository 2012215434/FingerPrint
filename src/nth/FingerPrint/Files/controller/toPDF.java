package nth.FingerPrint.Files.controller;

import java.io.File;  

import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.ComThread;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  
/*
 * 
测试用代码
测试用代码
测试用代码
测试用代码
*
*/
public class toPDF {
   static final int wdFormatPDF = 17;// PDF 格式    
    public void wordToPDF(String sfileName,String toFileName){    
            
        System.out.println("启动Word...");      
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
            System.out.println("打开文档..." + sfileName);  
            System.out.println("转换文档到PDF..." + toFileName);      
            File tofile = new File(toFileName);      
           if (tofile.exists()) {      
               tofile.delete();      
            }        
//          Dispatch.call(doc, "SaveAs",  destFile,  17);                    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {                
                toFileName, new Variant(17) }, new int[1]);    
            long end = System.currentTimeMillis();  //时间戳    
            System.out.println("转换完成..用时：" + (end - start) + "ms.");                
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:文档转换失败：" + e.getMessage());      
        } finally {  
            Dispatch.call(doc,"Close",false);  
            System.out.println("关闭文档");  
            if (app != null)      
                app.invoke("Quit", new Variant[] {});      
            }  
          //如果没有这句话,winword.exe进程将不会关闭  
           ComThread.Release();     
    }  
    public static void main(String[] args) {  
    	toPDF d = new toPDF();  
        d.wordToPDF("D:\\ftp\\android_vo.xls", "D:\\ftp\\ppt02.pdf");  
    }  
  
}  
