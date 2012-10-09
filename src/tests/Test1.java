package tests;
import java.util.Calendar;


public class Test1 {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 7, 1000);
		System.out.println(cal.getTime());
	}
}
