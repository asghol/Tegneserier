package tests;
import java.util.Calendar;

import model.Handler;


public class TestHandler {
	public static void main(String[] args) {
		new TestHandler();
	}
	
	public TestHandler() {
		Handler h = new Handler();
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 8, 9, 0, 0, 0);
		String cartoon = "pondus";
		h.getStripFromDate(cartoon, cal.getTime());
	}
}
