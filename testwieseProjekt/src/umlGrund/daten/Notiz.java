package umlGrund.daten;

import java.util.Date;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Notiz {
    private Date datum;
    private String beschreibung;

    public Notiz(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Notiz(Date datum, String beschreibung) {
        this.datum = datum;
        this.beschreibung = beschreibung;
    }


    public Date getDatum() {
        return datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
