package Unterricht.Jan.maedn.darstellung;

import Unterricht.Jan.maedn.model.*;

import java.util.*;

/**
 * Diese Klasse ist ein "Dialog" zur Konfiguration der
 * Startaufstellung der Spieler.
 * Mittels Texteingabe könne die Spieler
 * (zwischen 2 und 4) die Positionen und dessen Farbe
 * nacheinander wählen.
 * Dieser Dialog ist statisch und wird über die Methode
 * 
 * 
 * erzeugt und gleich zur Anzeige gebracht. Nach Wahl
 * der Startaufstellung oder Abbruch durch Spieler
 * läuft das aufrufende Programm weiter. Es sollte dann
 * die Startaufstellung abgerufen werden und eventuell
 * das Programm beendet werden.
 * Die empfohlene Nutzung sieht wie folgt aus:
 * <pre>
 *    SpielBeginn sb = new SpielBeginn();
 *    Spieler[] sp = sb.holeSpieler();
 *    if (sp == null) System.exit(0);
 *    ...
 * </pre>
 * @author Dr. Nico Krebs
 * @see Spiel
 */
public class SpielBeginn {

	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Diese Methode erzeugt den Dialog, der die 
	 * Konfiguration der Startaufstellung ermöglicht.
	 * @return Die Startaufstellung kodiert in einem
	 * Array der Länge vier. Mitspielende Spieler
	 * sind an Positionen der jeweilige Ecke:</br>
	 * <ul>
	 *  <li>0: links oben</li>
	 *  <li>1: rechts oben</li>
	 *  <li>2: rechts unten</li>
	 *  <li>3: links unten</li>
	 * </ul> 
	 */
	public Spieler[] getStartaufstellung() {
		Spieler[] spieler = new Spieler[4];

		System.out.println("Mensch ärgere dich nicht! - Spielstart");
		
		Map<String, Farbe> farben = new HashMap<>();
		for (Farbe f : new Farbe[] {Farbe.ROT, Farbe.BLAU, Farbe.GRUEN, Farbe.GELB})
			farben.put(" ("+f.getZeichen()+")\n    /"+f.getZeichen()+"\\, ("+f+")\n", f); 

		Map<String, Integer> ecken = new HashMap<>();
		ecken.put(" o| \n     -+-\n     |  (links oben)\n", 0);
		ecken.put("  |o\n     -+-\n     |  (rechts oben)\n", 1);
		ecken.put("  | \n     -+-\n     |o (rechts unten)\n", 2);
		ecken.put("  | \n     -+-\n    o|  (links unten)\n", 3);

		for (int i=1; i<=4; i++) {
			String[] auswahl = new String[farben.size() + (i>2?1:0)];
			int j=0;
			for (String s : farben.keySet())
				auswahl[j++] = s;
			if (i>2) {
				auswahl[auswahl.length-1] = 
					"Oder Sie starten das Spiel mit nur "+(i-1)+" Spielern.";
			}
			int wahl = menue(i,"Farbe",auswahl);
			if (i>2 && wahl==auswahl.length-1) return spieler;
			Spieler aktSpieler = new Spieler(farben.remove(auswahl[wahl]));
			
			auswahl = new String[ecken.size()];
			j=0;
			for (String s : ecken.keySet())
				auswahl[j++] = s;
			wahl = menue(i,"Ecke",auswahl);
			spieler[ecken.remove(auswahl[wahl])] = aktSpieler;
		}
		return spieler;
	}
	
	/**
	 * Bringt ein kleines Menü zur Anzeige (Konsole) und lässt
	 * den Nutzer wählen.
	 * Falls nur eine mögliche Option zur Wahl steht, wird nur
	 * eine Bestätigung erwartet.
	 * @param nummer Die Nummer des gefragten Spielers.
	 * @param typ "Farbe" oder "Ecke"
	 * @param auswahl Die zur Auswahl stehenden Farben oder Ecken.
	 * @return Den vom Nutzer gewählte Index innerhalb des Arrays auswahl.
	 * @throws NullPointerException falls auswahl null ist.
	 * @throws ArrayIndexOutOfBoundsException falls das Array leer ist.
	 */
	private int menue(int nummer, String typ, String[] auswahl) {
		if (auswahl.length == 1) {
			System.out.println("Spieler "+nummer+", Sie spielen mit der "+typ+" "+auswahl[0]+".");
			scanner.nextLine();
			return 0;
		}
		else {
			System.out.println("Spieler "+nummer+", bitte wählen Sie Ihre "+typ+":");
			for (int i=0; i<auswahl.length; i++) {
				System.out.println((i+1)+": "+auswahl[i]);
			}
			while (true) {
				try {
					int i = scanner.nextInt();
					if (1<=i && i<=auswahl.length) return i-1;
				}
				catch (Exception e) {}
			}
		}			
	}

	public Scanner getScanner() {
		return scanner;
	}
	
}