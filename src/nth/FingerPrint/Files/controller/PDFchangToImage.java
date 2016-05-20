package nth.FingerPrint.Files.controller;

import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.lang.reflect.Method;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
import java.security.AccessController;  
import java.security.PrivilegedAction;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGEncodeParam;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
import com.sun.pdfview.PDFFile;  
import com.sun.pdfview.PDFPage;  
  
public class PDFchangToImage {  
    public static void main(String[] args){  
        PDFchangToImage.changePdfToImg();  
    }  
  
    private static void changePdfToImg() {  
  
        try {  
        File file = new File("D:\\ftp\\20141125-25f35f3b-d3be-42d0-b4f4-ed4dbe10500a.pdf");  
        RandomAccessFile raf = new RandomAccessFile(file, "r");  
        FileChannel channel = raf.getChannel();  
        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());  
        PDFFile pdffile = new PDFFile(buf);  
        for (int i = 1; i <= pdffile.getNumPages(); i++) {  
        PDFPage page = pdffile.getPage(i);  
        Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));  
        Image img = page.getImage(rect.width, rect.height, rect,  
        null, // null for the ImageObserver  
        true, // fill background with white  
        true // block until drawing is done  
        );  
        BufferedImage tag = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);  
        tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, null);  
//      File imgfile = new File("D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg");  
//      if(imgfile.exists()){  
//          if(imgfile.createNewFile())  
//          {  
//              System.out.println("����ͼƬ��"+"D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg");  
//          } else {  
//              System.out.println("����ͼƬʧ�ܣ�");  
//          }  
//      }  
        FileOutputStream out = new FileOutputStream("D:\\ftp\\img\\" + i + ".jpg"); // ������ļ���  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);  
        param2.setQuality(0.25f, false);// 1f��������ɵ�ͼƬ����  
        encoder.setJPEGEncodeParam(param2);  
        encoder.encode(tag); // JPEG����  
        out.close();  
        }  
        channel.close();  
        raf.close();  
        unmap(buf);//���Ҫ��תͼƬ֮��ɾ��pdf���ͱ���Ҫ����ر�������ջ���ķ���  
        } catch (FileNotFoundException e) {  
        e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
          
    }  
  
    private static void unmap(final Object buffer) {  
        // TODO Auto-generated method stub  
  
        AccessController.doPrivileged(new PrivilegedAction<Object>() {  
        public Object run() {  
        try {  
        Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);  
        getCleanerMethod.setAccessible(true);  
        sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);  
        cleaner.clean();  
        } catch (Exception e) {  
        e.printStackTrace();  
        }  
        return null;  
        }  
        });  
    }  
      
      
}