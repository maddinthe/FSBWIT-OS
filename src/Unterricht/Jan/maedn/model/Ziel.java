package Unterricht.Jan.maedn.model;


/**
 * Diese Klasse bildet die 4 Felder einer Gasse
 * eines Spielers ab, ohne selbst Bestandteil der 
 * grafischen Oberfläche zu sein.
 * @author Nico Krebs
 * @see Spieler
 * @see Feld
 */
public class Ziel {
	/**
	 * Hier sind die 4 Felder des Ziels hinterlegt.
	 * An Position 0 ist das erste Feld am Rand des Umlaufs,
	 * 3 ist das letzte Feld, also das am weitesten
	 * innen befindliche.
	 */
	private Feld[] ziel = new Feld[4];

	/**
	 * Der Konstruktor erzeugt ein leeres Ziel zum
	 * Spieler. Alle Felder erhalten die Farbe des
	 * �bergebenen Spielers und sind initial leer.
	 * @param spieler Der Spieler, zu dem das Ziel
	 * farblich passen soll.
	 * @throws NullPointerException falls der übergebene
	 * Spieler <code>null</code> ist.
	 */
	public Ziel(Spieler spieler) {
		for (int i=0; i<4; i++)
			ziel[i] = new Feld(spieler.getFarbe());
	}
	/**
	 * Gibt ein Feld des Ziels zurück.
	 * @param position Die Position des gewünschten
	 * Feldes innerhalb des Ziels.
     * An Position 0 ist das erste Feld am Rand des Umlaufs,
	 * 3 ist das letzte Feld, also das am weitesten
	 * innen befindliche.
	 * @return Das Feld des Ziels an <code>position</code>.
	 * @throws ArrayIndexOutOfBoundsException falls
	 * eine <code>position</code> außerhalb des
	 * Bereichs 0 bis 3 �bergeben wurde.
	 */
	public Feld getFeld(int position) {
		return ziel[position];
	}
	
	/**
	 * Prüft, ob alle Figuren bündig am Ende des Ziels stehen,
	 * also nicht mehr bewegt werden können.
	 * Hier geht es um das 3x Würfeln...
	 * @return <code>true</code>, falls dem so ist.
	 */
	public boolean istBuendig() {
		int pos = 3;
		while (pos>0 && ziel[pos] != null) pos--;
		if (pos == -1) return true;
		while (ziel[pos] == null) pos--;
		return pos == -1;
	}
	/**
	 * Gibt zurück, ob alle Figuren im Ziel sind,
	 * also der Besitzer des Ziels gewonnen hat.
	 * Es reicht zu prüfen, ob in jedem Feld des
	 * Ziels eine Figur ist. 
	 * @return <code>true</code>, wenn das Ziel voll ist.
	 */
	public boolean istVoll() {
		for (Feld f : ziel)
			if (f.getFigur() == null) return false;
		return true;
	}
	
	/**
	 * Liefert die Anzahl der Figuren im Ziel.
	 * @return Die Anzahl der Figuren im Ziel.
	 */
	public int getAnzahl() {
		int anz = 0;
		for (Feld f : ziel)
			if (f.getFigur() != null) anz++;
		return anz;
	}
	
	/**
	 * Markiert alle Felder als nicht auswählbar.
	 */
	public void resetAuswahl() {
		for (Feld f : ziel)
			f.setWaehlbar(0);
	}
}