package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to handle URL String creation and actual downloading
 * @author Asgeir H�lleland
 */
public class URLCreator {
	/**
	 * Method for creating a valid URL for a cartoon
	 * @param cartoon 	what cartoon to fetch like "pondus" or "nemi"
	 * @param preURL	the first part of the url, the source
	 * @param date		date of the month of the cartoon strip to fetch
	 * @return String representation of the correct URL
	 */
	public String getCartoon(String cartoon, String preURL, Date date) {
		long time = date.getTime() / 1000L;
		return preURL + "date=" + Long.toString(time) + "&strip=" + cartoon; 
	}
	
	/**
	 * Simple method that downloads the image
	 * @param sURL url to the resource to download
	 * @param filename name of the file to save
	 */
	public void getImage(String sURL, String filename) {
		try {
			URL url = new URL(sURL);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(filename + ".gif");
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			fos.close();
			rbc.close();
			checkFile(filename + ".gif");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks whether the file is empty, if it is empty we delete it
	 * @param filename file to be checked
	 */
	private void checkFile(String filename) {
		File file = new File(filename);
		if(file.length() == 0)
			file.delete();
	}
	
	/**
	 * Creates a nicely formated filename
	 * @param cartoon name of the cartoon
	 * @param date date to look for the corresponding strip
	 * @return formated filename
	 */
	public String createFileName(String cartoon, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return cartoon + " " + sdf.format(date);
	}
}
