package umlGrund.daten;

import java.util.Date;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Vorgang {
    private Person kaeufer;
    private Verkaeufer verkaeufer;
    private Verkaeufer einkaeufer;
    private KFZ kfz;
    private double vPreis;
    private double ePreis;
    private Date verkaufsDatum;
    private String rabattGrund;
    private String sonstVereinbarungen;
    private Date einkaufsDatum;
    private String schaeden;

    public Vorgang(Verkaeufer einkaeufer, KFZ kfz, double ePreis) {
        this.einkaeufer = einkaeufer;
        this.kfz = kfz;
        this.ePreis = ePreis;
    }

    public Vorgang(Person kaeufer, Verkaeufer verkaeufer, Verkaeufer einkaeufer, KFZ kfz, double vPreis, double ePreis, Date verkaufsDatum, String rabattGrund, String sonstVereinbarungen, Date einkaufsDatum, String schaeden) {
        this.kaeufer = kaeufer;
        this.verkaeufer = verkaeufer;
        this.einkaeufer = einkaeufer;
        this.kfz = kfz;
        this.vPreis = vPreis;
        this.ePreis = ePreis;
        this.verkaufsDatum = verkaufsDatum;
        this.rabattGrund = rabattGrund;
        this.sonstVereinbarungen = sonstVereinbarungen;
        this.einkaufsDatum = einkaufsDatum;
        this.schaeden = schaeden;
    }

    public Person getKaeufer() {
        return kaeufer;
    }

    public void setKaeufer(Person kaeufer) {
        this.kaeufer = kaeufer;
    }

    public Verkaeufer getVerkaeufer() {
        return verkaeufer;
    }

    public void setVerkaeufer(Verkaeufer verkaeufer) {
        this.verkaeufer = verkaeufer;
    }

    public Verkaeufer getEinkaeufer() {
        return einkaeufer;
    }

    public void setEinkaeufer(Verkaeufer einkaeufer) {
        this.einkaeufer = einkaeufer;
    }

    public KFZ getKfz() {
        return kfz;
    }

    public void setKfz(KFZ kfz) {
        this.kfz = kfz;
    }

    public double getvPreis() {
        return vPreis;
    }

    public void setvPreis(double vPreis) {
        this.vPreis = vPreis;
    }

    public double getePreis() {
        return ePreis;
    }

    public void setePreis(double ePreis) {
        this.ePreis = ePreis;
    }

    public Date getVerkaufsDatum() {
        return verkaufsDatum;
    }

    public void setVerkaufsDatum(Date verkaufsDatum) {
        this.verkaufsDatum = verkaufsDatum;
    }

    public String getRabattGrund() {
        return rabattGrund;
    }

    public void setRabattGrund(String rabattGrund) {
        this.rabattGrund = rabattGrund;
    }

    public String getSonstVereinbarungen() {
        return sonstVereinbarungen;
    }

    public void setSonstVereinbarungen(String sonstVereinbarungen) {
        this.sonstVereinbarungen = sonstVereinbarungen;
    }

    public Date getEinkaufsDatum() {
        return einkaufsDatum;
    }

    public void setEinkaufsDatum(Date einkaufsDatum) {
        this.einkaufsDatum = einkaufsDatum;
    }

    public String getSchaeden() {
        return schaeden;
    }

    public void setSchaeden(String schaeden) {
        this.schaeden = schaeden;
    }
}
