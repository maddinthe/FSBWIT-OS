package Unterricht.Jan.maedn.model;


/**
 * Diese Klasse repräsentiert das Haus eines Spielers.
 * Sie fasst die 4 {@link Feld}er logisch zusammen,
 * ist aber selbst nicht Bestandteil der Darstellung.
 * Je ein {@link Spieler} hat ein Haus.
 * @author Dr. Nico Krebs
 * @see Spieler
 * @see Feld
 */
public class Haus {
	/**
	 * Das Haus besteht aus vier Feldern. An Position
	 * 0 und 1 sind die oberen, an 2 und 3 die unteren 
	 * beiden Felder jeweils von links nach rechts.
	 */
	private Feld[] haus = new Feld[4];
	
	/**
	 * Dieser Konstruktor erzeugt zu einem Spieler
	 * das Haus mit den 4 Feldern. Auf den Felder stehen
	 * initial alle 4 {@link Figur}en des 
	 * Spielers, die hier mit erzeugt werden.
	 * @param spieler Der Spieler, zu dem das Haus
	 * gehören wird. Dessen Farbe wird an die Felder
	 * und Figuren weitergegeben. 
	 */
	public Haus(Spieler spieler) {
		for (int i=0; i<4; i++) { 
			haus[i] = new Feld(spieler.getFarbe());
			haus[i].setFigur(new Figur(spieler));
		}
	}
	
	/**
	 * Fügt dem Haus eine Figur hinzu, falls noch Platz ist.
	 * Das ist eine geworfene Figur, die zurück ins Haus kommt.
	 * @param figur Die hinzuzufügende Figur.
	 */
	public void add(Figur figur) {
		for (int i=0; i<4; i++)
			if (haus[i].isFrei()) {
				haus[i].setFigur(figur);
				return;
			}
	}
	/**
	 * Liefert die Anzahl der Figuren im Haus.
	 * @return Anzahl der Figuren im Haus.
	 */
	public int anzahl() {
		for (int i=0; i<4; i++)
			if (haus[i].isFrei())
				return i;
		return 4;
	}
	/**
	 * Liefert ein Feld des Hauses zurück.
	 * @param position Die Position des Feldes
	 * innerhalb des Hauses.
	 * @return Das Feld an der gewünschten Position.
	 * An Position 0 und 1 sind die oberen, an 2 und 3
	 * die unteren beiden Felder jeweils von links nach
	 * rechts.
	 * @throws ArrayIndexOutOfBoundsException falls
	 * die <code>position</code> außerhalb des Bereiches
	 * von 0 bis 3 war.
	 */
	public Feld getFeld(int position) {
		return haus[position];
	}

	/**
	 * Markiert alle Felder als nicht auswählbar.
	 */
	public void resetAuswahl() {
		for (Feld f : haus)
			f.setWaehlbar(0);
	}
}