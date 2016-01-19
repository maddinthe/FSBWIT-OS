package Unterricht.Jan.maedn.model;

/**
 * Diese Klasse repräsentiert eine Spielfarbe.
 * Es gibt ausschließlich 4 vorgefertigte Instanzen, die
 * jeweils die gültigen Spielfarben darstellen.
 * @author Nico Krebs
 * @see Unterricht.Jan.maedn.model.Spieler
 * @see Feld
 */
public final class Farbe {
	/**
	 * Die Spielfarbe Rot.
	 */
	public static final Farbe ROT = new Farbe("Rot",'|');
	/**
	 * Die Spielfarbe Grün.
	 */
	public static final Farbe GRUEN = new Farbe("Grün",'/');
	/**
	 * Die Spielfarbe Blau.
	 */
	public static final Farbe BLAU = new Farbe("Blau",'\\');
	/**
	 * Die Spielfarbe Gelb.
	 */
	public static final Farbe GELB = new Farbe("Gelb",'-');
	
	/**
	 * Die Farbe der jeweiligen Farbe zur textbasierten
	 * Ausgabe.
	 */
	private String name;
	
	/**
	 * Das Zeichen, mit dem in der Textdarstellung eigene 
	 * Felder und Figuren gekennzeichet werden.
	 */
	private char zeichen;
	
	/**
	 * Der private Konstruktor dient dem Erzeugen der
	 * 4 gültigen Spielfarben mitsamt des Namens für
	 * die textbasierte Ausgabe.
	 * @param name Der anzuzeigende Name der Farbe bei
	 * einer textbasierten Ausgabe.
	 * @param zeichen Das Zeichen, mit dem in der 
	 * Textdarstellung eigene Felder und Figuren
	 * gekennzeichet werden sollen.
	 */
	private Farbe(String name, char zeichen) {
		this.name = name;
		this.zeichen = zeichen;
	}

	/**
	 * Gibt den Namen der Farbe zurück.
	 * @return Der Namen der Farbe.
	 */
	public String toString() {
		return name;
	}
	
	public char getZeichen() {
		return zeichen;
	}
}