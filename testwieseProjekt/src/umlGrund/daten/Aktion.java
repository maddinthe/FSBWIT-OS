package umlGrund.daten;

import java.util.Date;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Aktion {
    private Date durchfuehrung;
    private Person durchfuehrender;
    private String beschreibung;

    public Aktion(Date durchfuehrung, Person durchfuehrender, String beschreibung) {
        this.durchfuehrung = durchfuehrung;
        this.durchfuehrender = durchfuehrender;
        this.beschreibung = beschreibung;
    }

    public Date getDurchfuehrung() {
        return durchfuehrung;
    }

    public void setDurchfuehrung(Date durchfuehrung) {
        this.durchfuehrung = durchfuehrung;
    }

    public Person getDurchfuehrender() {
        return durchfuehrender;
    }

    public void setDurchfuehrender(Person durchfuehrender) {
        this.durchfuehrender = durchfuehrender;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
