package nth.FingerPrint.common;

public class VerifyingCode {
	
	public static String[] s={"0","1","2","3","4","5","6","7","8","9",
		"a","b","c","d","e","f","g","h","i","j",
		"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public String generateVerifyingCode(){
		
		String verifyingCode="";
		
		int a=(int) (Math.random()*36);
		int b=(int)(Math.random()*36);
		int c=(int)(Math.random()*36);
		int d=(int)(Math.random()*36);
		
		verifyingCode=verifyingCode+s[a]+s[b]+s[c]+s[d];
		
		return verifyingCode;
	}
	
	public static void main(String[] args){
		VerifyingCode v=new VerifyingCode();
		for(int i=0;i<5;i++)
		System.out.println(v.generateVerifyingCode());
	}
}
