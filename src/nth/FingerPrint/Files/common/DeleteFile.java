package nth.FingerPrint.Files.common;

import java.io.File;

public class DeleteFile {

	/** 
	 * 删除单个文件 
	 * @param   path    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String path) {  
		boolean flag=false;
		File  file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
}
