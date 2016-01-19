package Unterricht.Jan.maedn.darstellung;

import java.util.Scanner;

public class Menue {

	private Scanner scanner;
	
	public Menue(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public int holeInt(String text, int min, int max) {
		while (true) {
			try {
				System.out.println(text);
				String s = scanner.nextLine();
				int nr = Integer.parseInt(s);
				if (min <= nr && nr <= max)
					return nr;
			}
			catch (Exception e) {}
		}		
	}
	
	public boolean holeBool(String text) {
		System.out.println(text);
		String s = scanner.nextLine();
		return "ja".compareToIgnoreCase(s) == 0;
	}

	public void holeOk(String text) {
		System.out.println(text);
		scanner.nextLine();
	}
}
