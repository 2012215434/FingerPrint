package nth.FingerPrint.Files.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class util2Get {
	public static String getCurrentDate(){
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//��ȡ���
	    String year_f = String.valueOf(year);
	    int month=ca.get(Calendar.MONTH)+1;//��ȡ�·�
	    String month_f = String.valueOf(month);
	    int day=ca.get(Calendar.DATE);//��ȡ��
	    String day_f = String.valueOf(day);
	    //�����ո�ʽ��
	    String yearFormat = "0000";
	    String otherFormat = "00";
	    year_f=yearFormat.substring(0, 4-year_f.length())+year_f;
	    month_f=otherFormat.substring(0, 2-month_f.length())+month_f;
	    day_f=otherFormat.substring(0, 2-day_f.length())+day_f;
	    String currentDate = year_f + month_f + day_f;
	    return currentDate;
	}
		
		public static Timestamp getSysTime(){
			Date date = new Date();       
			Timestamp currentTime = new Timestamp(date.getTime());
			return  currentTime;
		}
		
			
		public static String getUUID(){
		    UUID uuid = UUID.randomUUID(); 
			String uuidTS = uuid.toString();
			return uuidTS;
		}
}