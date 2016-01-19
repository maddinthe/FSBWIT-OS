package Unterricht.Jan.maedn.model;

/**
 * Die Klasse repräsentiert einen Spieler.
 * Ein Spieler kennt seine Farbe, die Felder
 * des Ziels, die seines Hauses und das Startfeld.
 * @author Dr. Nico Krebs
 * @see Feld
 * @see Ziel
 * @see Haus
 */
public class Spieler {
	/**
	 * Die Farbe des Spielers.
	 */
	private Farbe farbe;
	/**
	 * Das Startfeld des Spielers. Das Feld, auf dem
	 * eine Figur gesetzt wird, wenn er eine Figur
	 * aus dem Haus nehmen darf. über dieses Feld darf
	 * eine Figur des Spielers nicht bewegt werden,
	 * stattdessen muss sie in das Ziel abbiegen.
	 */
	private Feld startFeld;
	/**
	 * Das Haus des Spielers.
	 */
	private Haus haus;
	/**
	 * Das Ziel des Spielers.
	 */
	private Ziel ziel;

	/**
	 * Dieser Konstruktor erzeugt einen neuen Spieler
	 * mit der übergebenen Farbe. Zudem wird ein
	 * Haus, ein Ziel und ein Startfeld erzeugt,
	 * die alle die übergebene Farbe berücksichtigen.
	 * @param farbe Eine der gültigen Spielfarben.
	 */
	public Spieler(Farbe farbe) {
		this.farbe = farbe;
		this.startFeld = new Feld(farbe);
		haus = new Haus(this);
		ziel = new Ziel(this);
	}
	
	/**
	 * Gibt das Haus des Spielers zurück.
	 * @return Das Haus des Spielers.
	 */
	public Haus getHaus() {
		return haus;
	}
	/**
	 * Gibt die Gasse des Spielers zurück.
	 * @return Die Gasse des Spielers
	 */
	public Ziel getZiel() {
		return ziel;
	}
	/**
	 * Gibt die Farbe des Spielers zurück.
	 * @return Die Farbe des Spielers.
	 */
	public Farbe getFarbe() {
		return farbe;
	}
	/**
	 * Gibt das Startfeld des Spielers zurück.
	 * Das ist das Feld, auf dem
	 * eine Figur gesetzt wird, wenn er eine Figur
	 * aus dem Haus nehmen darf. über dieses Haus darf
	 * eine Figur des Spielers nicht bewegt werden,
	 * stattdessen muss sie in das Haus abbiegen.
	 * @return Das Startfeld des Spielers.
	 */
	public Feld getStartFeld() {
		return startFeld;
	}
	
	/**
	 * Gibt zurück, ob der Spieler gewonnen hat, also
	 * alle seine Figuren in seinem Ziel sind.
	 * @return true, wenn der Spieler gewonnen hat.
	 */
	private boolean gewonnen() {
		return ziel.istVoll();
	}

	public void resetAuswahl() {
		haus.resetAuswahl();
		ziel.resetAuswahl();		
	}
	
	public String toString() {
		return farbe+","+farbe.getZeichen();
	}

}
