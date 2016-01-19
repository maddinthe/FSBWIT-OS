package Unterricht.Jan.maedn.model;


/**
 * Diese Klasse stellt ein einzelnes Feld auf dem
 * gesamten {@link Umlauf} dar. Ein Feld kann auch
 * speziell einem {@link Unterricht.Jan.maedn.model.Haus} oder einer {@link Unterricht.Jan.maedn.model.Ziel}
 * zugeordnet werden. Feldern können {@link Unterricht.Jan.maedn.model.Farbe}n
 * zugewiesen sein, die bei der Darstellung leerer 
 * Felder berücksichtigt wird. Auf Feldern kann genau
 * eine {@link Unterricht.Jan.maedn.model.Figur} beliebiger Farbe stehen.
 * @author Nico Krebs und XIII.04
 * @see Unterricht.Jan.maedn.model.Umlauf
 * @see Unterricht.Jan.maedn.model.Farbe
 * @see Unterricht.Jan.maedn.model.Figur
 * @see Unterricht.Jan.maedn.model.Haus
 * @see Unterricht.Jan.maedn.model.Ziel
 */
public class Feld {
	/**
	 * Die Figur, die auf dem Feld steht. Steht da
	 * keine ist <code>figur = null</code>.
	 */
	private Figur figur;
	
	/**
	 * Die Farbe, die sichtbar sein soll, wenn keine
	 * Figur auf dem Feld steht.
	 */
	private Farbe defaultColor;
	
	/**
	 * Gibt an, ob der Nutzer die Figur auf dem Feld 
	 * auswählen kann - und mit welcher Nummer,
	 * also das Feld ein Startfeld für einen gültigen
	 * Zug ist (siehe 
	 * {@link Unterricht.Jan.maedn.model.Spiel#schalteFigurenFrei(Spieler)}).
	 */
	private int nummer = 0;
	
	/**
	 * Der Konstruktor erzeugt ein neues Feld.
	 */
	public Feld() {
		figur = null;
	}
	/**
	 * Der Konstruktor erzeugt ein neues Feld, das
	 * aufgrund seiner Farbe einem Spieler zugeordnet
	 * ist. Leere Felder haben dann einen Kreis mit
	 * entsprechender Füllung.
	 * @param farbe Die gewünschte Farbe.
	 */
	public Feld(Farbe farbe) {
		this();
		defaultColor = farbe;
	}
	/**
	 * Diese Methode setzt eine Figur auf das Feld.
	 * Ist die übergebene Figur <code>null</code>,
	 * kommt das einem Löschen gleich.
	 * @param figur Die zu setzende Figur oder 
	 * <code>null</code>, falls die bereits gesetzte
	 * Figur gelöscht werden soll.
	 */
	public void setFigur(Figur figur) {
		this.figur = figur;
	}
	/**
	 * Gibt die auf dem Feld stehende Figur zurück,
	 * sonst <code>null</code>.
	 * @return Die auf dem Feld stehende Figur,
	 * sonst <code>null</code>.
	 */
	public Figur getFigur() {
		return figur;
	}
	/**
	 * Gibt zurück, ob das Feld noch frei ist, also
	 * noch keine Figur drauf steht. 
	 * @return <code>true</code> falls noch keine
	 * Figur auf dem Feld steht, sonst <code>false</code>.
	 */
	public boolean isFrei() {
		return figur == null;
	}
	/**
	 * L�scht die Figur aus dem Feld.
	 */
	public void clear() {
		figur = null;
	}

	/**
	 * Macht das Feld auswählbar sofern eine Figur
	 * darauf steht, oder eben nicht. Das Feld zeigt
	 * dann die Figur mit der Nummer.
	 * Die Methode wird von der Klasse {@link Unterricht.Jan.maedn.model.Spiel}
	 * genutzt, um Startfelder gültiger Züge zu
	 * kennzeichnen.
	 * @param nummer größer 0 wenn das Feld
	 * ausw�hlbar werden soll.
	 */
	public void setWaehlbar(int nummer) {
		if (nummer != 0 && figur != null)
			this.nummer = nummer;
		else
		    this.nummer = 0;
	}
	/**
	 * Gibt zurück, ob das Feld auswählbar ist.
	 * @return <code>true</code> falls das Feld
	 * auswählbar ist, <code>false</code> sonst.
	 */
	public boolean isWaehlbar() {
		return nummer > 0;
	}
	
	/**
	 * Gibt die Nummer, mit der die Figur auf diesem Feld
	 * ausgewählt werden kann zurück.
	 * @return Die Nummer zur aktuellen Auswahl.
	 */
	public int getNummer() {
		return nummer;
	}
}