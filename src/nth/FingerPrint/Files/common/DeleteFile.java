package nth.FingerPrint.Files.common;

import java.io.File;

public class DeleteFile {

	/** 
	 * ɾ�������ļ� 
	 * @param   path    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteFile(String path) {  
		boolean flag=false;
		File  file = new File(path);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
}
