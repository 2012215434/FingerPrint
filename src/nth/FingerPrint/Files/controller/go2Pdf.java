package nth.FingerPrint.Files.controller;


import java.io.File;  
import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  
  
public class go2Pdf {  
  
    static final int wdDoNotSaveChanges = 0;// ����������ĸ��ġ�  
    static final int wdFormatPDF = 17;// wordתPDF ��ʽ  
    static final int ppSaveAsPDF = 32;// ppt תPDF ��ʽ  
  
  /*  public static void main(String[] args) {  
        String source1 = "D:\\ftp\\file\\20141118\\20141118-29d26040-2e80-4d9f-9a8f-a064b005ece2.doc";  
        String source2 = "D:\\ftp\\0002.xls";  
        String source3 = "D:\\ftp\\00002.ppt";  
        String target1 = "D:\\ftp\\20141118-29d26040-2e80-4d9f-9a8f-a064b005ece2.pdf";  
        String target2 = "D:\\ftp\\xls2.pdf";  
        String target3 = "D:\\ftp\\ppt2.pdf";  
          
        go2Pdf pdf = new go2Pdf();  
          
          
        pdf.word2pdf(source1, target1);  
        pdf.excel2pdf(source2, target2);  
        pdf.ppt2pdf(source3, target3);  
          
          
    } */ 
    
    
    public static void main(String[] args){
    	String source="C:\\Users\\Administrator\\Desktop\\�μ�\\08_JQuery.pptx";
    	String target="C:\\Users\\Administrator\\Desktop\\�μ�\\08_JQuery.pdf";
    	ppt2pdf(source, target);
    }
    public static  void word2pdf(String source,String target){  
        System.out.println("����Word");  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        try {  
            app = new ActiveXComponent("Word.Application");  
            app.setProperty("Visible", false);  
            System.out.println(app);
  
            Dispatch docs = app.getProperty("Documents").toDispatch();  
            System.out.println("���ĵ�" + source);  
            Dispatch doc = Dispatch.call(docs,//  
                    "Open", //  
                    source,// FileName  
                    false,// ConfirmConversions  
                    true // ReadOnly  
                    ).toDispatch();  
            System.out.println("ת���ĵ���PDF " + target);  
            File tofile = new File(target);  
            if (tofile.exists()) {  
                tofile.delete();  
            }  
            Dispatch.call(doc,//  
                    "SaveAs", //  
                    target, // FileName  
                    wdFormatPDF);  
  
            Dispatch.call(doc, "Close", false);  
            long end = System.currentTimeMillis();  
            System.out.println("ת�����..��ʱ��" + (end - start) + "ms.");  
        } catch (Exception e) {  
            System.out.println("========Error:�ĵ�ת��ʧ�ܣ�" + e.getMessage());  
        } finally {  
            if (app != null)  
                app.invoke("Quit", wdDoNotSaveChanges);  
        }  
    }  
  
    public static void ppt2pdf(String source,String target){  
        System.out.println("����PPT");  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        try {  
            app = new ActiveXComponent("Powerpoint.Application");  
            Dispatch presentations = app.getProperty("Presentations").toDispatch();  
            System.out.println("���ĵ�" + source);  
            Dispatch presentation = Dispatch.call(presentations,//  
                    "Open",   
                    source,// FileName  
                    true,// ReadOnly  
                    true,// Untitled ָ���ļ��Ƿ��б��⡣  
                    false // WithWindow ָ���ļ��Ƿ�ɼ���  
                    ).toDispatch();  
  
            System.out.println("ת���ĵ���PDF " + target);  
            File tofile = new File(target);  
            if (tofile.exists()) {  
                tofile.delete();  
            }  
            Dispatch.call(presentation,//  
                    "SaveAs", //  
                    target, // FileName  
                    ppSaveAsPDF);  
  
            Dispatch.call(presentation, "Close");  
            long end = System.currentTimeMillis();  
            System.out.println("ת�����..��ʱ��" + (end - start) + "ms.");  
        } catch (Exception e) {  
            System.out.println("========Error:�ĵ�ת��ʧ�ܣ�" + e.getMessage());  
        } finally {  
            if (app != null) app.invoke("Quit");  
        }  
    }  
  
    public static void excel2pdf(String source, String target) {  
        System.out.println("����Excel");  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // ����excel(Excel.Application)  
        try {  
        app.setProperty("Visible", false);  
        Dispatch workbooks = app.getProperty("Workbooks").toDispatch();  
        System.out.println("���ĵ�" + source);  
        Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method, new Object[]{source, new Variant(false),new Variant(false)}, new int[3]).toDispatch();  
        Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {  
        target, new Variant(57), new Variant(false),  
        new Variant(57), new Variant(57), new Variant(false),  
        new Variant(true), new Variant(57), new Variant(true),  
        new Variant(true), new Variant(true) }, new int[1]);  
        Variant f = new Variant(false);  
        System.out.println("ת���ĵ���PDF " + target);  
        Dispatch.call(workbook, "Close", f);  
        long end = System.currentTimeMillis();  
        System.out.println("ת�����..��ʱ��" + (end - start) + "ms.");  
        } catch (Exception e) {  
            System.out.println("========Error:�ĵ�ת��ʧ�ܣ�" + e.getMessage());  
        }finally {  
            if (app != null){  
                app.invoke("Quit", new Variant[] {});  
            }  
        }  
    }  
}