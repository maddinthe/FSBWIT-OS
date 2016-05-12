package umlGrund.daten;

import java.util.Date;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Person {
    private String anrede;
    private String name;
    private String vorName;
    private Date geburtstag;
    private String anschrift;
    private int postleitzahl;
    private String ort;
    private String ustID;
    private Notiz[] notizen;
    private Erreichbarkeit[] erreichbarkeiten;

    public Person (String anrede, String name){

    }
    public Person (String anrede, String name, String vorName, Date geburtstag, String anschrift, int postleitzahl, String ort, String ustID){

    }

    public String getAnrede() {
        return anrede;
    }

    public String getName() {
        return name;
    }

    public String getVorName() {
        return vorName;
    }

    public Date getGeburtstag() {
        return geburtstag;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getUstID() {
        return ustID;
    }

    public Notiz[] getNotizen() {
        return notizen;
    }

    public Erreichbarkeit[] getErreichbarkeiten() {
        return erreichbarkeiten;
    }

    public void addErreichbarkeit(Erreichbarkeit erreichbarkeit){

    }
    public void addNotiz(Notiz notiz){

    }

}
