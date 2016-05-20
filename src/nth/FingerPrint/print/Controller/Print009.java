package nth.FingerPrint.print.Controller;

public class Print009 {
	public static void main(String args[]){
		printPDF("D:/ftp/20141219-23c73e6a-67de-421e-b8c4-84d979b747eb.pdf");
	}
	public static boolean printPDF(String path){
		try{
			Runtime run = Runtime.getRuntime();
			run.exec("cmd /k c: && cd C:\\Program Files (x86)\\Adobe\\Reader 11.0\\Reader && acroRd32.exe /t "+path);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
		
		
	}
}
