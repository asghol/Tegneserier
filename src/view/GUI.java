package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Handler;

public class GUI extends JFrame implements ActionListener {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 1560508750802569605L;
	
	private JPanel jp;
	private JCheckBox jcbPondus, jcbNemi, jcbLunch, jcbRocky;
	private JLabel jlYear1, jlYear2, jlMonth1, jlMonth2, jlDate1, jlDate2;
	private JTextField jtfYear1, jtfYear2, jtfMonth1, jtfMonth2, jtfDate1, jtfDate2;
	private JButton jbStart, jbDagens, jbToUker;
	
	private Handler handler;
	
	public GUI() {
		super("Tegneserier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new JPanel();
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		handler = new Handler();
		createElements(gbc);
		
		getContentPane().add(jp);
		pack();
		setVisible(true);
	}

	private void createElements(GridBagConstraints gbc) {
		jcbPondus = new JCheckBox("Pondus");
		gbc.gridx = 0;
		gbc.gridy = 0;
		jp.add(jcbPondus, gbc);
		
		jcbNemi = new JCheckBox("Nemi");
		gbc.gridx = 1;
		gbc.gridy = 0;
		jp.add(jcbNemi, gbc);
		
		jcbLunch = new JCheckBox("Lunch");
		gbc.gridx = 2;
		gbc.gridy = 0;
		jp.add(jcbLunch, gbc);
		
		jcbRocky = new JCheckBox("Rocky");
		gbc.gridx = 3;
		gbc.gridy = 0;
		jp.add(jcbRocky, gbc);
		
		jlYear1 = new JLabel("År");
		gbc.gridx = 0;
		gbc.gridy = 1;
		jp.add(jlYear1, gbc);
		
		jtfYear1 = new JTextField(handler.getYear(), 4);
		gbc.gridx = 1;
		gbc.gridy = 1;
		jp.add(jtfYear1, gbc);
		
		jlYear2 = new JLabel("År");
		gbc.gridx = 2;
		gbc.gridy = 1;
		jp.add(jlYear2, gbc);
		
		jtfYear2 = new JTextField(handler.getYear(), 4);
		gbc.gridx = 3;
		gbc.gridy = 1;
		jp.add(jtfYear2, gbc);
		
		jlMonth1 = new JLabel("Måned");
		gbc.gridx = 0;
		gbc.gridy = 2;
		jp.add(jlMonth1, gbc);
		
		jtfMonth1 = new JTextField(handler.getMonth(), 4);
		gbc.gridx = 1;
		gbc.gridy = 2;
		jp.add(jtfMonth1, gbc);
		
		jlMonth2 = new JLabel("Måned");
		gbc.gridx = 2;
		gbc.gridy = 2;
		jp.add(jlMonth2, gbc);
		
		jtfMonth2 = new JTextField(handler.getMonth(), 4);
		gbc.gridx = 3;
		gbc.gridy = 2;
		jp.add(jtfMonth2, gbc);
		
		jlDate1 = new JLabel("Dato");
		gbc.gridx = 0;
		gbc.gridy = 3;
		jp.add(jlDate1, gbc);
		
		jtfDate1 = new JTextField(handler.getDate(), 4);
		gbc.gridx = 1;
		gbc.gridy = 3;
		jp.add(jtfDate1, gbc);
		
		jlDate2 = new JLabel("Dato");
		gbc.gridx = 2;
		gbc.gridy = 3;
		jp.add(jlDate2, gbc);
		
		jtfDate2 = new JTextField(handler.getDate(), 4);
		gbc.gridx = 3;
		gbc.gridy = 3;
		jp.add(jtfDate2, gbc);
		
		jbStart = new JButton("Start");
		jbStart.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		jp.add(jbStart, gbc);
		
		jbDagens = new JButton("Dagens");
		jbDagens.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		jp.add(jbDagens, gbc);
		
		jbToUker = new JButton("2 uker");
		jbToUker.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 4;
		jp.add(jbToUker, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource().equals(jbStart)) {
			String[] cartoons = getChecked();
			
			String year1str = jtfYear1.getText();
			String year2str = jtfYear2.getText();
			String month1str = jtfMonth1.getText();
			String month2str = jtfMonth2.getText();
			String date1str = jtfDate1.getText();
			String date2str = jtfDate2.getText();
			
			int year1, year2, month1, month2, date1, date2;
			try {
				year1 = Integer.parseInt(year1str);
				year2 = Integer.parseInt(year2str);
				month1 = Integer.parseInt(month1str)-1;
				month2 = Integer.parseInt(month2str)-1;
				date1 = Integer.parseInt(date1str);
				date2 = Integer.parseInt(date2str);
			} catch (NumberFormatException e) {
				return; // In the future add a popup message saying something about the input being bad
			}
			
			Calendar cal = Calendar.getInstance();
			cal.set(year1, month1, date1);
			Date start = cal.getTime();
			cal.set(year2, month2, date2);
			Date end = cal.getTime();
			
			handler.getStripsFromTo(cartoons, start, end);
		}
		if(evt.getSource().equals(jbDagens)) {
			String[] cartoons = getChecked();
			handler.getTodaysStrip(cartoons);
		}
		if(evt.getSource().equals(jbToUker)) {
			String[] cartoons = getChecked();
			Calendar cal = Calendar.getInstance();
			Date end = cal.getTime();
			cal.add(Calendar.DATE, -15);
			Date start = cal.getTime();
			handler.getStripsFromTo(cartoons, start, end);
		}
	}

	private String[] getChecked() {
		ArrayList<String> list = new ArrayList<String>(4);
		if(jcbPondus.isSelected())
			list.add("pondus");
		if(jcbNemi.isSelected())
			list.add("nemi");
		if(jcbLunch.isSelected())
			list.add("lunch");
		if(jcbRocky.isSelected())
			list.add("rocky");
		String[] cartoons = list.toArray(new String[list.size()]);
		return cartoons;
	}
}
