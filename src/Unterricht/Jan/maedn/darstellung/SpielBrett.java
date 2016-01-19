package Unterricht.Jan.maedn.darstellung;

import Unterricht.Jan.maedn.model.Feld;
import Unterricht.Jan.maedn.model.Spiel;
import Unterricht.Jan.maedn.model.Spieler;
import Unterricht.Jan.maedn.model.Umlauf;

/**
 * Diese Klasse repräsentiert die Hauptansicht des
 * Spiels Mensch ärgere dich nicht!
 * Eine Instanz dieser Klasse wird von 
 * <code>Spiel</code> erzeugt und anschließend
 * ausschließlich zur Darstellung genutzt.
 * @author Nico Krebs
 * @see Spiel
 */
public class SpielBrett {
	
	private Umlauf umlauf;
	
	private Spieler[] spieler;
	
	private final String SPACE =  " ";
	private final String RAHMEN = "|";
	private final String ECKE =   "+";
	private final String NICHTS =        "      ";
	private final String NICHTS_RAHMEN = "     |";
	private final String NICHTS_ECKE   = "     +";
	private final String KANTE =         "-----+";
		
	public SpielBrett(Umlauf umlauf, Spieler[] spieler) {
		this.umlauf = umlauf;
		this.spieler = spieler;
	}
	
	public void print() {
		boolean[] haus = {
				spieler[0] != null, 
				spieler[1] != null, 
				spieler[2] != null, 
				spieler[3] != null};
		
		for (int zeile = 1; zeile <= 34; zeile++) {
			switch (zeile) {
			case 1:
			case 4:
			case 7:
				if (haus[0])
					System.out.print(ECKE+KANTE+KANTE);
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_ECKE);
				System.out.print(KANTE+(zeile==7&&!haus[1]?NICHTS_ECKE:KANTE)+KANTE);
				System.out.print(NICHTS);
				if (haus[1])
					System.out.print(NICHTS_ECKE+KANTE+KANTE);
				break;

			case 2:
			case 3:
				if (haus[0]) {
					System.out.print(RAHMEN);
					for (int i=0; i<=1; i++)
						System.out.print(zeile(spieler[0].getHaus().getFeld(i),zeile-1,spieler[0]));
				}
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_RAHMEN);
				for (int i=8; i<=10; i++)
					System.out.print(zeile(umlauf.getFeld(i),zeile-1,i==10?spieler[1]:null));
				if (haus[1]) {
					System.out.print(NICHTS+NICHTS_RAHMEN);
					for (int i=0; i<=1; i++)
						System.out.print(zeile(spieler[1].getHaus().getFeld(i),zeile-1,spieler[1]));
				}
				break;
				
			case 5:
			case 6:
				if (haus[0]) {
					System.out.print(RAHMEN);
					for (int i=2; i<=3; i++)
						System.out.print(zeile(spieler[0].getHaus().getFeld(i),zeile-4,spieler[0]));
				}
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(7),zeile-4,null));
				if (haus[1])
					System.out.print(zeile(spieler[1].getZiel().getFeld(0),zeile-4,spieler[1]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(11),zeile-4,null));
				if (haus[1]) {
					System.out.print(NICHTS+NICHTS_RAHMEN);
					for (int i=2; i<=3; i++)
						System.out.print(zeile(spieler[1].getHaus().getFeld(i),zeile-4,spieler[1]));
				}
				break;
				
			case 8:
			case 9:
				System.out.print(SPACE+NICHTS+NICHTS+NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(6),zeile-7,null));
				if (haus[1])
					System.out.print(zeile(spieler[1].getZiel().getFeld(1),zeile-7,spieler[1]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(12),zeile-7,null));
				break;
				
			case 10:
			case 25:
				System.out.print(SPACE+NICHTS+NICHTS+NICHTS+NICHTS_ECKE+KANTE+
						(((zeile==10&&!haus[1]) || (zeile==25&&!haus[3]))?NICHTS_ECKE:KANTE)+
						KANTE);
				break;
				
			case 11:
			case 12:
				System.out.print(SPACE+NICHTS+NICHTS+NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(5),zeile-10,null));
				if (haus[1])
					System.out.print(zeile(spieler[1].getZiel().getFeld(2),zeile-10,spieler[1]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(13),zeile-10,null));
				break;

			case 13:
			case 16:
			case 19:
			case 22:
				System.out.print(
						ECKE+KANTE+KANTE+KANTE+KANTE+KANTE+
						(((zeile<19&&haus[1])||(zeile>=19&&haus[3]))?KANTE:NICHTS_ECKE)+
						KANTE+KANTE+KANTE+KANTE+KANTE);
				break;
				
			case 14:
			case 15:
				System.out.print(RAHMEN);
				for (int i=0; i<=4; i++)
					System.out.print(zeile(umlauf.getFeld(i),zeile-13,i==0?spieler[0]:null));
				if (haus[1])
					System.out.print(zeile(spieler[1].getZiel().getFeld(3),zeile-13,spieler[1]));
				else
					System.out.print(NICHTS_RAHMEN);
				for (int i=14; i<=18; i++)
					System.out.print(zeile(umlauf.getFeld(i),zeile-13,null));
				break;
				
			case 17:
			case 18:
				System.out.print(RAHMEN);
				System.out.print(zeile(umlauf.getFeld(39),zeile-16,null));
				if (haus[0])
					for (int i=0; i<=3; i++)
						System.out.print(zeile(spieler[0].getZiel().getFeld(i),zeile-16,spieler[0]));
				else
					System.out.print(NICHTS+NICHTS+NICHTS+NICHTS);
				if (haus[2]) {
					System.out.print(NICHTS_RAHMEN);
					for (int i=3; i>=0; i--)
						System.out.print(zeile(spieler[2].getZiel().getFeld(i),zeile-16,spieler[2]));
				}
				else
					System.out.print(NICHTS+NICHTS+NICHTS+NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(19),zeile-16,null));
				break;
				
			case 20:
			case 21:
				System.out.print(RAHMEN);
				for (int i=38; i>=34; i--)
					System.out.print(zeile(umlauf.getFeld(i),zeile-19,null));
				if (haus[3])
					System.out.print(zeile(spieler[3].getZiel().getFeld(3),zeile-19,spieler[3]));
				else
					System.out.print(NICHTS_RAHMEN);
				for (int i=24; i>=20; i--)
					System.out.print(zeile(umlauf.getFeld(i),zeile-19,i==20?spieler[2]:null));
				break;
				
			case 23:
			case 24:
				System.out.print(SPACE+NICHTS+NICHTS+NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(33),zeile-22,null));
				if (haus[3])
					System.out.print(zeile(spieler[3].getZiel().getFeld(2),zeile-22,spieler[3]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(25),zeile-22,null));
				break;
				
			case 26:
			case 27:
				System.out.print(SPACE+NICHTS+NICHTS+NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(32),zeile-25,null));
				if (haus[3])
					System.out.print(zeile(spieler[3].getZiel().getFeld(1),zeile-25,spieler[3]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(26),zeile-25,null));
				break;
				
			case 28:
			case 31:
			case 34:
				if (haus[3])
					System.out.print(ECKE+KANTE+KANTE);
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_ECKE);
				System.out.print(KANTE+(zeile==28&&!haus[3]?NICHTS_ECKE:KANTE)+KANTE);
				System.out.print(NICHTS);
				if (haus[2])
					System.out.print(NICHTS_ECKE+KANTE+KANTE);
				break;
				
			case 29:
			case 30:
				if (haus[3]) {
					System.out.print(RAHMEN);
					for (int i=0; i<=1; i++)
						System.out.print(zeile(spieler[3].getHaus().getFeld(i),zeile-28,spieler[3]));
				}
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(31),zeile-28,null));
				if (haus[3])
					System.out.print(zeile(spieler[3].getZiel().getFeld(0),zeile-28,spieler[3]));
				else
					System.out.print(NICHTS_RAHMEN);
				System.out.print(zeile(umlauf.getFeld(27),zeile-28,null));
				if (haus[2]) {
					System.out.print(NICHTS+NICHTS_RAHMEN);
					for (int i=0; i<=1; i++)
						System.out.print(zeile(spieler[2].getHaus().getFeld(i),zeile-28,spieler[2]));
				}
				break;
				
			case 32:
			case 33:
				if (haus[3]) {
					System.out.print(RAHMEN);
					for (int i=2; i<=3; i++)
						System.out.print(zeile(spieler[3].getHaus().getFeld(i),zeile-31,spieler[3]));
				}
				else
					System.out.print(SPACE+NICHTS+NICHTS);
				System.out.print(NICHTS+NICHTS_RAHMEN);
				for (int i=30; i>=28; i--)
					System.out.print(zeile(umlauf.getFeld(i),zeile-31,i==30?spieler[3]:null));
				if (haus[2]) {
					System.out.print(NICHTS+NICHTS_RAHMEN);
					for (int i=2; i<=3; i++)
						System.out.print(zeile(spieler[2].getHaus().getFeld(i),zeile-31,spieler[2]));
				}
				break;

			default:
				System.out.println("Da fehlt ne Zeile: "+zeile);

			}
			System.out.println();
		}
			
	}
	
	/**
	 * Liefert den String der ersten/zweiten Zeile eines Feldes
	 * immer ohne Anfang! 
	 * @param f Das darzustellende Feld
	 * @param zeile Die 1. oder die 2. Zeile der zweizeiligen Darstellung?
	 * @return Den String mit oder ohne Figur bzw. Eigenfarbe und Nummer 
	 */
	private String zeile(Feld f, int zeile, Spieler spieler) {
		String s = " ";
		if (zeile == 1) {
			if (f.isFrei()) {
				if (spieler == null)
					return s+"    |";
				else
					return s+spieler.getFarbe().getZeichen()+
							spieler.getFarbe().getZeichen()+
							spieler.getFarbe().getZeichen()+" |";
			}
			else
				return s+"("+f.getFigur().getFarbe().getZeichen()+") |";				
		}
		if (f.isFrei()) {
			if (spieler == null)
				return s+"    |";
			else
				return s+spieler.getFarbe().getZeichen()+
						spieler.getFarbe().getZeichen()+
						spieler.getFarbe().getZeichen()+" |";
		}
		else
			if (f.getNummer() == 0)
				return s+"/"+f.getFigur().getFarbe().getZeichen()+"\\ |";
			else
				return s+"/"+f.getNummer()+"\\ |";
	}
}
