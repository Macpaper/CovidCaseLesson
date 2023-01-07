package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import javax.swing.JPanel;

public class DataPanel extends JPanel {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 500;
	Dimension dimension;
	
	static ArrayList<String> cases;
	static ArrayList<Integer> newCases;
	static HashMap<String, Integer> dayCase;
	ArrayList<Integer> sortedCases;
	int minCase = 0,maxCase = 0;
	
	public DataPanel() {
		readData();

//		setFocusable(true);
		dimension = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(dimension);
//		setDoubleBuffered(true);
		
		printBadDays();
	}

	
	public void readData() {
		cases = new ArrayList<String>();
		newCases = new ArrayList<Integer>();
		dayCase = new HashMap<String, Integer>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("cases.csv"))){
			String s;
			while((s = reader.readLine()) != null) {
				cases.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// check to see if it worked
		for(String s : cases) {
			System.out.println(s);
		}
		organizeData();
	}
	
	public void organizeData() {
		// must start at 1 because the first line is the header
		for(int i = 1; i < cases.size(); i++) {
			String[] singleCase = cases.get(i).split(",");
			
			String date = singleCase[0];
			int singleDayCases = Integer.parseInt(singleCase[1]);
			
			if(singleDayCases < minCase) {
				minCase = singleDayCases;
			}
			if(singleDayCases > maxCase) {
				maxCase = singleDayCases;
			}
			
			newCases.add(singleDayCases);
			dayCase.put(date, singleDayCases);
		}

	}
	
	public void printBadDays() {
		// prints the days of the 3 highest
		sortedCases = new ArrayList<>(newCases);
		
		Collections.sort(sortedCases, Collections.reverseOrder());
		for(int i = 0; i < 20; i++) {
			String highestDate = getKeyByValue(dayCase, sortedCases.get(i));
			System.out.println("On this day, " + highestDate + ", " + sortedCases.get(i) + " cases were recorded.");
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
//		double proportionConstant = (double)HEIGHT/maxCase;
		
		g2.setColor(Color.blue);
		for(int i = 0; i < newCases.size(); i++) {
			g2.drawRect(i, HEIGHT, 5, -newCases.get(i));
		}
		g2.dispose();
	}
	
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
}
