package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Class to handle cartoon strip fetcing
 * @author Asgeir H�lleland
 */
public class Handler {
	// URL for script to use
	public final String preUrl = "http://www.dagbladet.no/tegneserie/pondusarkiv/serveconfig.php?";
	private Calendar calendar;
	
	private URLCreator urlc;
	
	/**
	 * Constructor for a Handler
	 */
	public Handler() {
		calendar = Calendar.getInstance();
		urlc = new URLCreator();
	}
	
	/**
	 * Method for fetching todays Strip of a cartoon
	 * @param cartoon
	 */
	public void getTodaysStrip(String[] cartoons) {
		Calendar cal = Calendar.getInstance();
		resetTimeOfDay(cal);
		for(int i = 0; i < cartoons.length; i++) {
			getStrip(cartoons[i], cal.getTime());
		}
	}
	
	/**
	 * Method for fetching a cartoon from a given date
	 * @param cartoon	cartoon to fetch
	 * @param date		date published
	 */
	public void getStripFromDate(String cartoon, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		resetTimeOfDay(cal);
		getStrip(cartoon, cal.getTime());
	}
	
	/**
	 * Method for fetching cartoon strips of different kinds between two dates
	 * @param cartoons	cartoons to fetch
	 * @param start		start date
	 * @param end		end date
	 */
	public void getStripsFromTo(String cartoons[], Date start, Date end) {
		long diff = ((end.getTime() - start.getTime()) / 86400000) + 1;
		Long l = new Long(diff);
		int days = l.intValue();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		resetTimeOfDay(cal);
		for(int i = 0; i < days; i++) {
			for(int j = 0; j < cartoons.length; j++) {
				getStrip(cartoons[j], cal.getTime());
			}
			cal.add(Calendar.DATE, 1);
		}
	}

	/**
	 * Method that calls methods for getting a cartoon strip
	 * @param cartoon	cartoon to fetch
	 * @param date		date published
	 */
	private void getStrip(String cartoon, Date date) {
		String s = urlc.getCartoon(cartoon, preUrl, date);
		String filename = urlc.createFileName(cartoon, date);
		urlc.getImage(s, filename);
	}
	
	/**
	 * Method for resetting the calendar objects hour, minute, second and millisecond to zero
	 * @param cal calendar to reset
	 */
	private void resetTimeOfDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * Method for fetching current year
	 * @return current year
	 */
	public String getYear() {
		int year = calendar.get(Calendar.YEAR);
		return Integer.toString(year);
	}
	
	/**
	 * Method for fetching current month
	 * @return current month
	 */
	public String getMonth() {
		int month = calendar.get(Calendar.MONTH)+1;
		return Integer.toString(month);
	}
	
	/**
	 * Method for fetching current date
	 * @return current date
	 */
	public String getDate() {
		int date = calendar.get(Calendar.DATE);
		return Integer.toString(date);
	}
}
