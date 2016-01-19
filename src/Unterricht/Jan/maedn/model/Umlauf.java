package Unterricht.Jan.maedn.model;

import Unterricht.Jan.maedn.darstellung.SpielBeginn;

/**
 * Diese Klasse bildet den Umlauf ab.
 * Es werden die 40 Felder verwaltet.
 * @author Dr. Nico Krebs
 */
public class Umlauf {
	
	/**
	 * Das sind die 40 Felder des Spielfeldes.
	 * An Posotion 0 steht das Startfeld des Spielers, der
	 * links oben startet, oder ein normales Feld, falls
	 * dort kein Spieler startet.
	 */
	private Feld[] felder;
	
	/**
	 * Dieser Konstruktor intialisiert den Umlauf
	 * entsprechend der Startaufstellung (siehe 
	 * {@link SpielBeginn#spieler}).
	 * @param spieler Die Startaufstellung der Spieler.
	 */
	public Umlauf(Spieler[] spieler) {
		felder = new Feld[40];
		for (int i=0; i<felder.length; i++)
			if (i % 10 == 0 && spieler[i/10] != null)
				felder[i] = spieler[i/10].getStartFeld();
			else
				felder[i] = new Feld();
	}
	/**
	 * Gibt ein Feld innerhalb der 40er-Anordnung zurück.
	 * @param position 0 für Startfeld des Spielers oben links.
	 * @return Das gewünschte Feld.
	 */
	public Feld getFeld(int position) {
		return felder[position];
	}
	
	/**
	 * Gibt das Feld zurück, das die mit der angegebenen Nummer
	 * markierte Figur enthält.
	 * @param nummer Die Nummer, mit der das Feld angesprochen
	 * werden kann, das die damit gewählte Nummer enthält.
	 * @return Das somit gewählte Feld.
	 */
	public Feld getWaehlbaresFeld(int nummer) {
		for (Feld f : felder)
			if (f.getNummer() == nummer) return f;
		return null;
	}
	/**
	 * Gibt die Position passend zum Feld zurück.
	 * @param feld
	 * @return Die Position, falls das Feld drin ist, -1 falls
	 * nicht (z.B. bei Feldern der Ziele oder der Häuser).
	 */
	public int getFeldPos(Feld feld) {
		for (int i=0; i<40; i++) if (felder[i] == feld) return i;
		return -1;
	}
}