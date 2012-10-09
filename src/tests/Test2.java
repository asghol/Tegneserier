package tests;
import java.util.Calendar;

import model.URLCreator;

public class Test2 {
	public static String preUrl = "http://www.dagbladet.no/tegneserie/pondusarkiv/serveconfig.php?";
	
	public static void main(String[] args) {
		new Test2();
	}
	
	public Test2() {
		Calendar cal = Calendar.getInstance();
		URLCreator urlc = new URLCreator();
		
		String cartoon1 = "pondus";
		int year1 = 2012;
		int month1 = 8;
		int date1 = 2;
		cal.set(year1, month1, date1, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		String s1 = new String(urlc.getCartoon(cartoon1, preUrl, cal.getTime()));
		String filename1 = urlc.createFileName(cartoon1, cal.getTime());
		urlc.getImage(s1, filename1);
		System.out.println(s1);
		
		String cartoon2 = "nemi";
		int year2 = 2012;
		int month2 = 8;
		int date2 = 2;
		cal.set(year2, month2, date2, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		String s2 = new String(urlc.getCartoon(cartoon2, preUrl, cal.getTime()));
		String filename2 = urlc.createFileName(cartoon2, cal.getTime());
		urlc.getImage(s2, filename2);
		System.out.println(s2);
	}
}
