package umlGrund.daten;

import java.util.Date;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class KFZ {
    private String hersteller;
    private String modell;
    private String kfzBriefNr;
    private int leistungInKw;
    private String farbe;
    private Date ez;
    private byte umweltplakette;
    private String kraftstoff;
    private Aktion[] aktionen;
    private Sonderausstattung[] sonderausstattungen;

    public KFZ(String hersteller, String modell, String kfzBriefNr, int leistungInKw, String farbe, Date ez, byte umweltplakette, String kraftstoff, Aktion[] aktionen, Sonderausstattung[] sonderausstattungen) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.kfzBriefNr = kfzBriefNr;
        this.leistungInKw = leistungInKw;
        this.farbe = farbe;
        this.ez = ez;
        this.umweltplakette = umweltplakette;
        this.kraftstoff = kraftstoff;
        this.aktionen = aktionen;
        this.sonderausstattungen = sonderausstattungen;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getKfzBriefNr() {
        return kfzBriefNr;
    }

    public void setKfzBriefNr(String kfzBriefNr) {
        this.kfzBriefNr = kfzBriefNr;
    }

    public int getLeistungInKw() {
        return leistungInKw;
    }

    public void setLeistungInKw(int leistungInKw) {
        this.leistungInKw = leistungInKw;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public Date getEz() {
        return ez;
    }

    public void setEz(Date ez) {
        this.ez = ez;
    }

    public byte getUmweltplakette() {
        return umweltplakette;
    }

    public void setUmweltplakette(byte umweltplakette) {
        this.umweltplakette = umweltplakette;
    }

    public String getKraftstoff() {
        return kraftstoff;
    }

    public void setKraftstoff(String kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public Aktion[] getAktionen() {
        return aktionen;
    }

    public void setAktionen(Aktion[] aktionen) {
        this.aktionen = aktionen;
    }

    public Sonderausstattung[] getSonderausstattungen() {
        return sonderausstattungen;
    }

    public void setSonderausstattungen(Sonderausstattung[] sonderausstattungen) {
        this.sonderausstattungen = sonderausstattungen;
    }

    public void addSonderausstattungen(Sonderausstattung sonderausstattung){

    }
    public void addAktion(Aktion aktion){

    }
}
