package Unterricht.Jan.maedn.model;

import Unterricht.Jan.maedn.darstellung.*;
import Unterricht.Jan.maedn.gui.SpielBeginn;

import java.util.*;

/**
 * Diese Klasse stellt das Herzstück des Spiels dar.
 * Sie erzeugt nach Abfrage der Startaufstellung mittels
 * {@link SpielBeginn} das Spielfeld mit der Klasse
 * {@link Umlauf} und organisiert dann den
 * vollständigen Spielablauf.
 * @author Dr. Nico Krebs
 * @see SpielBeginn
 * @see Umlauf
 */
public class Spiel {
	
	/**
	 * Die Startaufstellung, an Position 0 ist der
	 * Spieler, der links oben startet oder null.
	 * Die anderen Spieler folgen im Uhrzeigersinn.
	 */
	private Spieler[] spieler;
	/**
	 * Das SpielFeld kennt den Umlauf und damit alle 40 Felder.
	 */
	private Umlauf umlauf;
	/**
	 * Die Nummer des aktuellen Spielers, der gerade 
	 * am Zug ist. -1 ist nur der initiale Wert, danach
	 * sind nur Werte zwischen 0 und 3 zulässig.
	 */
	private int spielerNr = -1;
	/**
	 * Der W�rfel.
	 */
	private Wuerfel wuerfel = new Wuerfel();
	/**
	 * Diese Map speichert die aktuell möglichen Züge
	 * ( Map< Ausgangsfeld, Zielfeld > )
	 * eines Spieler in Abhängigkeit seines Würfelergebnisses.
	 * Die Map wird von {@link #schalteFigurenFrei(Spieler)}
	 * erstellt und dann im Menü
	 * genutzt, um eine der ausgewählten Figuren zu setzen.
	 * Die Felder mit den zu ziehenden Figuren bilden
	 * die Schlüsselmenge, die jeweiligen Zielfelder
	 * die Wertemenge. Die Map wird nie null, ist aber
	 * leer, wenn keine Züge möglich sind.
	 */
	private Map<Feld,Feld> aktionen = new HashMap<>();
	
	/**
	 * Nicht die Figuren, sondern die Felder, auf denen die
	 * Figuren standen, werden hier pustbar.
	 */
	private Set<Feld> pustbar = new HashSet<>();
	
	/**
	 * Das Spielbrett für die Darstellung des gesamten Spielfeldes.
	 */
	private SpielBrett spielBrett;
	
	/**
	 * Das Menü wird für die Interaktion mit dem Spieler
	 * benötigt.
	 */
	private Menue menue;
	
	/**
	 * Der Konstruktor erzeugt ein Spiel mit dem
	 * {@link Unterricht.Jan.maedn.model.Umlauf}. Anschließend beginnt
	 * das Spiel. Die dazu
	 * benötigte Startaufstellung wird anfangs über
	 * die Klasse {@link SpielBeginn} vom Nutzer
	 * erfragt. Dieser Vorgang kann vom Nutzer hierbei
	 * abgebrochen werden. In diesem Fall wird das
	 * Programm vollständig beendet.
	 */
	public Spiel() {

		SpielBeginn sb = new SpielBeginn();
		spieler = sb.getStartaufstellung();
		
		menue =  new Menue(sb.getScanner());
		
		umlauf = new Umlauf(spieler);
		
		spielBrett = new SpielBrett(umlauf, spieler);
		
		spielen();
	}
	
	/**
	 * Nach dem Spielbeginn, beginnt hier das eigentliche
	 * Spiel.
	 */
	private void spielen() {
		// unser Spiel startet mit dem ersten Spielerwechsel
		do {
			spielerWechsel();
			
			// lässt nun den nächsten spielen

			pusten(spieler[spielerNr]);
			
			wuerfel.reset();
			do {
				if (wuerfel.getZahl() < 1)
					spielBrett.print();
				
				wuerfeln(spieler[spielerNr]);
				
				ziehen(spieler[spielerNr]);
				
			} while (wuerfel.getZahl() == 6);
			
		} while (!spieler[spielerNr].getZiel().istVoll());
		// hier hat ein Spieler gewonnen und das Spiel ist aus
		
		menue.holeOk(
				"Herzlichen Glückwunsch!\n\n"+
				"Spieler ("+spieler[spielerNr].getFarbe().getZeichen()+")\n"+
				"        /"+spieler[spielerNr].getFarbe().getZeichen()+"\\"+
				" ("+spieler[spielerNr].getFarbe()+"),\n"+
				"Sie haben gewonnen.");
	}
	
	/**
	 * Nachdem der aktuelle Spieler seinen Zug vollendet
	 * hat, wird diese Methode aufgerufen. Der nächste nicht
	 * leere Spieler in der Startaufstellung kommt jetzt
	 * dran.
	 * Seine bisher pustbaren Figuren werden zurückgesetzt.
	 */
	private void spielerWechsel() {
		// nimmt den nächsten vorhandenen Spieler
		spielerNr = (spielerNr+1) % 4;
		// �berspringt dabei nicht mitspielende Spielpositionen
		while (spieler[spielerNr] == null) spielerNr = (spielerNr+1) % 4;
		
		Spieler aktuellerSpieler = spieler[spielerNr];
		
		// sobald er dran ist, verschwinden seine bisher pustbaren Figuren
		loescheAusPustbar(aktuellerSpieler);

		spielBrett.print();

		Farbe farbe = aktuellerSpieler.getFarbe();
		System.out.println(
				"Spieler ("+farbe.getZeichen()+")\n"+
				"        /"+farbe.getZeichen()+"\\ ("+farbe+"), Sie sind am Zug.");
	}
	
	/**
	 * Der aktuelle Spieler darf zunächst pusten, oder es bleiben
	 * lassen.
	 * @param spieler Der aktuelle Spieler.
	 */
	private void pusten(Spieler spieler) {
		resetAuswahl();
		int anzahlFremdeFigurenAufUmlauf = 0;
		for (int i=0; i<40; i++) {
			Feld feld = umlauf.getFeld(i);
			Figur f = feld.getFigur();
			if (f != null && f.spieler() != spieler) {
				feld.setWaehlbar(++anzahlFremdeFigurenAufUmlauf);
			}
		}
		if (anzahlFremdeFigurenAufUmlauf == 0 ||
			!menue.holeBool("Möchten Sie pusten?")) {
			resetAuswahl();
			return;
		}

		spielBrett.print();

		Feld feld = null;
		if (anzahlFremdeFigurenAufUmlauf == 1) {
			System.out.println("Es gibt nur eine fremde Figur auf dem Umlauf.");
			feld = umlauf.getWaehlbaresFeld(1);
		}
		else {
			int nummer = menue.holeInt(
					"Wählen Sie die zu pustenden Figur mit einer "+
					"Nummer zwischen 1 und "+ anzahlFremdeFigurenAufUmlauf +":", 
					1,anzahlFremdeFigurenAufUmlauf);
			feld = umlauf.getWaehlbaresFeld(nummer);
		}
		if (pustbar.contains(feld)) {
			Figur figur = feld.getFigur(); 
			figur.spieler().getHaus().add(figur);
			feld.clear();
			loescheAusPustbar(figur.spieler());
			menue.holeOk("Sie wurde erfolgreich gepustet.");
		}
		else {
			menue.holeOk("Diese Figur jedoch ist nicht pustbar. ");
			bestrafePusten();
		}
		resetAuswahl();
		spielBrett.print();
	}

	/**
	 * Ein neuer Spieler darf würfeln.
	 * Vorab wird mit {@link #blockiert(Spieler)} 
	 * geprüft, ob er 3 Mal würfeln darf.
	 * @param spieler Der nun aktuelle Spieler. Dieser
	 * Spieler darf nicht <code>null</code> sein.
	 * @throws NullPointerException Wenn der übergebenen
	 * Spieler <code>null</code> ist.
	 */
	private void wuerfeln(Spieler spieler) {
		if (blockiert(spieler)) {
			menue.holeOk("Sie dürfen dreimal versuchen, eine 6 zu würfeln...");
			wuerfel.wuerfeln();
			if (wuerfel.getZahl() != 6) {
				menue.holeOk("Sie dürfen noch zweimal versuchen, eine 6 zu würfeln...");
				wuerfel.wuerfeln();
				if (wuerfel.getZahl() != 6) {
					menue.holeOk("Sie dürfen noch einmal versuchen, eine 6 zu würfeln...");
					wuerfel.wuerfeln();
				}
			}
		}
		else {
			menue.holeOk("Sie dürfen jetzt würfeln...");
			wuerfel.wuerfeln();
		}
	}
	
	/**
	 * Prüft, ob ein Spieler 3 Mal würfeln darf, also
	 * alle seine Figuren nicht bewegen kann, d.h. diese
	 * entweder bündig im {@link Ziel}} oder im {@link Haus}} sind.
	 * @param spieler Der aktuell zu prüfende Spieler.
	 * @return <code>true</code>, wenn alle Figuren vom
	 * <code>spieler</code> b�ndig im Ziel stehen oder
	 * noch im {@link Haus}} sind.
	 */
	private boolean blockiert(Spieler spieler) {
		int anzUnbeweglich = spieler.getHaus().anzahl();
		if (spieler.getZiel().istBuendig())
			anzUnbeweglich = spieler.getZiel().getAnzahl();
		return anzUnbeweglich == 4;
	}
	
	/**
	 * Diese Methode bewirkt das Ziehen nach dem Würfeln
	 * durch den aktiven Spieler.
	 * @param spieler Der aktuelle Spieler.
	 */
	public void ziehen(Spieler spieler) {
		resetAuswahl();
		schalteFigurenFrei(spieler);
		
		if (aktionen.size() == 1) {
			// wenn er nur eine Option hat...
			spielBrett.print();
			menue.holeOk("Bestätigen Sie das Ziehen der mit 1 gekennzeichneten Figur.");
			bewegeFigurAufFeld(aktionen.keySet().iterator().next());
		}
		else if (aktionen.size() > 1) {
			spielBrett.print();
			int wahl = menue.holeInt(
				"Wählen Sie aus den mit Numern markierten Figuren", 1, aktionen.size());
			for (Feld feld : aktionen.keySet())
				if (feld.getNummer() == wahl)
					bewegeFigurAufFeld(feld);
		}
		else
			menue.holeOk("Leider ist kein Zug möglich.");
	}

	/**
	 * In den Aktionen {@link #aktionen} ist für jedes
	 * ziehbare Feld das Zielfeld hinterlegt. Mit dem
	 * Ausgangsfeld kann nun dieser Zug ausgeführt werden.
	 * @param feld Das Ausgangsfeld des Zuges, auf dem
	 * noch die zu ziehende Figur steht.
	 */
	private void bewegeFigurAufFeld(Feld feld) {
		Feld zf = aktionen.get(feld);    // Zielfeld...
		// wird eine geworfen?
		Spieler sp = null;
		if (zf.getFigur() != null) {
			sp = zf.getFigur().spieler(); 
			sp.getHaus().add(zf.getFigur());
			// falls da eine zu pustende Figur stand, ist das jetzt hinf�llig
			pustbar.remove(zf);
		} else {
			// k�nnte eine geworfen werden?
			for (Feld f : aktionen.keySet())
				if (aktionen.get(f).getFigur() != null) {
					pustbar.add(f);
				}
		}
		zf.setFigur(feld.getFigur());
		feld.clear();
		
		resetAuswahl();
	}
	
	/**
	 * Der aktuelle Spieler hat versucht, eine Figur zu
	 * pusten, die jedoch nicht vergessen hatte zu
	 * schlagen... 
	 */
	private void bestrafePusten() {
		if (spieler[spielerNr].getHaus().anzahl() +
				spieler[spielerNr].getZiel().getAnzahl() == 4)
			System.out.println("Leider konntest du nicht bestraft werden...");
		else {
			nimmFordersteFigur(spieler[spielerNr]);
			System.out.println("Deine forderste Figur wurde stattdessen zurück ins Haus gesetzt.");
		}
	}
	
	/**
	 * Nimmt zur Strafe die forderste Figur des Spielers vom Umlauf,
	 * sofern er eine hat.
	 * @param spieler Der zu bestrafende Spieler.
	 */
	private void nimmFordersteFigur(Spieler spieler) {
		int start = umlauf.getFeldPos(spieler.getStartFeld());
		for (int i=39; i>=0; i--) {
			Feld feld = umlauf.getFeld((i+start)%40); 
			if (feld.getFigur() != null &&
				feld.getFigur().spieler() == spieler) {
				spieler.getHaus().add(feld.getFigur());
				feld.clear();
				return;
			}
		}
	}

	/**
	 * Diese Methode eroiert die jetzt möglichen Spielzüge
	 * für den aktuellen Spieler anhand des Würfels.
	 * @param spieler Der aktuelle Spieler.
	 * In der Map {@link #aktionen} sind die Startfelder der
	 * möglichen Züge als Schlüssel und die Zielfelder
	 * als Werte hinterlegt. Sind keine Spielzüge
	 * möglich, wird eine leere Map hinterlassen.
	 */
	private void schalteFigurenFrei(Spieler spieler) {
		aktionen.clear();
		int nummer = 1;
		// soll eins raus
		if (wuerfel.getZahl() == 6) {
			int anz = spieler.getHaus().anzahl(); 
			// hat er noch Figuren im Haus...
			if (anz > 0) {
				// und keine eigene auf dem Startfeld...
				if (spieler.getStartFeld().getFigur() == null || 
						spieler.getStartFeld().getFigur().spieler() != spieler) {
					// dann muss er rauskommen
					spieler.getHaus().getFeld(anz-1).setWaehlbar(nummer++);
					aktionen.put(spieler.getHaus().getFeld(anz-1), spieler.getStartFeld());
					return;
				}
				// andernfalls muss er erst vom Startfeld weg, wenn nicht 6 Felder weiter
				// eine eigene Figur steht
				else {
					Feld zf = holeZielFeld(spieler.getStartFeld());
					// ZielFeld kann nicht null sein ;o)
					Figur figur = zf.getFigur(); 
					if (figur == null || figur.getFarbe() != spieler.getFarbe()) {
						spieler.getStartFeld().setWaehlbar(nummer++);
						aktionen.put(spieler.getStartFeld(), zf);
						return;
					}
				}
			}
		}
		// steht er auf dem Startfeld und muss er es freimachen?
		if (spieler.getStartFeld().getFigur() != null && 
				spieler.getStartFeld().getFigur().spieler() == spieler && 
					spieler.getHaus().anzahl() > 0) {
			Feld zf = holeZielFeld(spieler.getStartFeld());
			// ZielFeld kann hier auch nicht null sein ;o)
			Figur figur = zf.getFigur(); 
			if (figur == null || figur.spieler() != spieler) {
				spieler.getStartFeld().setWaehlbar(nummer++);
				aktionen.put(spieler.getStartFeld(), zf);
				return;
			}
		}
		// hier kann er alle bewegen, die gehen...
		// Suche auf dem Umlauf
		// Suche beginnt immer bei seinem StartFeld
		int start = umlauf.getFeldPos(spieler.getStartFeld());
		for (int i=0; i<40; i++) {
			Feld f = umlauf.getFeld((start+i)%40);
			if (f.getFigur() != null && f.getFigur().spieler() == spieler) {
				Feld zf = holeZielFeld(f);
				if (zf != null && (zf.getFigur() == null || zf.getFigur().spieler() != spieler)) {
					f.setWaehlbar(nummer++);
					aktionen.put(f, zf);
				}
			}
		}
		// suche im Ziel
		for (int i=0; i<4; i++) {
			Feld f = spieler.getZiel().getFeld(i);
			if (f.getFigur() != null) {
				Feld zf = holeZielFeld(f);
				if (zf != null) {
					f.setWaehlbar(nummer++);
					aktionen.put(f, zf);
				}
			}
		};
	}
	
	/**
	 * Holt das Zielfeld abhängig der Würfelzahl.
	 * Berücksichtigt die Startfelder und Ziele.
	 * @param feld Ein Feld mit einer Figur des aktuellen Spielers.
	 * @return Das Zielfeld sofern erreichbar, sonst 
	 * <code>null</code>.
	 * @throws NullPointerException falls auf feld keine Figur steht.
	 */
	private Feld holeZielFeld(Feld feld) {
		int start = umlauf.getFeldPos(feld);
		int anz = wuerfel.getZahl();
		Spieler spieler = feld.getFigur().spieler();
		// Bewegung beginnt auf dem Spielfeld ?
		if (start >= 0) {
			while (anz > 0) {
				if (umlauf.getFeld((start+1) % 40) == spieler.getStartFeld()) break;
				start = (start+1) % 40;
				anz--;
			}
			if (anz==0) return umlauf.getFeld(start);
			// geht weiter ins Ziel ?
			start = -1;
			while (anz > 0) {
				// steht da schon einer, darf er ja nicht drüber
				if (start+1>3) break;
				if (spieler.getZiel().getFeld(start+1).getFigur() != null) break;
				start++;
				anz--;
			}
			if (anz==0) return spieler.getZiel().getFeld(start);
		}
		// beginnt im Ziel
		else {
			start = 0;
			while (spieler.getZiel().getFeld(start) != feld) start++;
			while (anz > 0) {
				// steht da schon einer, darf er ja nicht drüber
				if (start+1>3) break;
				if (spieler.getZiel().getFeld(start+1).getFigur() != null) break;
				start++;
				anz--;
			}
			if (anz==0) return spieler.getZiel().getFeld(start);
		}
		return null;
	}
	
	/**
	 * Setzt pustbar für alle Figuren des Spielers zurück.
	 * @param spieler Der Spieler, dessen Figuren anschließend
	 * nicht mehr pustbar sein sollen.
	 */
	private void loescheAusPustbar(Spieler spieler) {
		boolean nochmal;
		do {
			nochmal = false;
			for (Feld f : pustbar)
				if (f.getFigur() == null || 
				      f.getFigur().spieler() == spieler) {
					nochmal = true;
					pustbar.remove(f);
					break;
				}
		} while (nochmal);
	}

	/**
	 * Setzt die Nummern der bislang auswählbaren Felder bzw.
	 * der Figuren darauf zurück.
	 */
	private void resetAuswahl() {
		for (int i=0; i<40; i++)
			umlauf.getFeld(i).setWaehlbar(0);
		for (Spieler sp : spieler)
			if (sp != null)
				sp.resetAuswahl();
	}
}