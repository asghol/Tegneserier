package tests;
import java.util.Calendar;

import model.URLCreator;

public class Test3 {
	public final String preUrl = "http://www.dagbladet.no/tegneserie/pondusarkiv/serveconfig.php?";

	public static void main(String[] args) {
		new Test3();
	}

	public Test3() {
		URLCreator urlc = new URLCreator();
		String[] cartoons = { "pondus", "nemi", "lunch", "rocky" };

		Calendar cal = Calendar.getInstance();
		for(int i = 1; i <= 10; i++) {
			cal.set(2012, 8, i, 0, 0, 0);
			cal.set(Calendar.MILLISECOND, 0);
			for(int j = 0; j < cartoons.length; j++) {
				if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
					String s = new String(urlc.getCartoon(cartoons[j], preUrl, cal.getTime()));
					String filename = urlc.createFileName(cartoons[j], cal.getTime());
					urlc.getImage(s, filename);
				}
			}
		}
	}
}
