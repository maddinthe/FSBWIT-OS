package Unterricht.Jan.maedn.darstellung;

/**
 * Der Würfel ist Bestandteil der textbasierten
 * Ausgabe. Beim Würfeln zeigt er das selbständig an
 * und merkt sich sein Ergebnis.
 * Interessierte Klassen sollten sich über die Methode
 * {@link #getZahl()} das Ergebnis holen.
 * @author Dr. Nico Krebs
 */
public class Wuerfel {
	/**
	 * Die gewürfelte Zahl zwischen 1 und 6, sofern
	 * schon ein Ergebnis vorliegt, andernfalls 0.
	 */
	private int zahl = 0;
	
	/**
	 * Die Methode lässt den Würfel würfeln.
	 * Entsprechend werden Ausgaben erfolgen...
	 */
	public void wuerfeln() {
	    System.out.println("  ____");
	    System.out.println(" /\\'. \\    _____");
	    System.out.println("/ .\\__'\\  / ,  /\\");
	    System.out.println("\\' / . / /____/, \\");
	    System.out.println(" \\/___/  \\' .'\\ '/");
	    System.out.println("          \\'__'\\/");
	    zahl = (int)(Math.random()*6)+1;
	    System.out.println(">> "+zahl+" <<");
	}		
	/**
	 * über diese Methode gelangt man an das
	 * Würfelergebnis.
	 * @return Das Würfelergebnis zwischen 1 und 6, falls
	 * bereits eins vorliegt, andernfalls 0.
	 */
	public int getZahl() {
		return zahl;
	}
	
	/**
	 * Markiert den Würfel als noch nicht gewürfelt.
	 */
	public void reset() {
		zahl = 0;
	}
}