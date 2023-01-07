package main;

import javax.swing.JFrame;

public class DataWindow {


	public DataWindow() {
		
		JFrame f = new JFrame("COVID Data - Cases by Day Over Time");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new DataPanel());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DataWindow();
	}

}
