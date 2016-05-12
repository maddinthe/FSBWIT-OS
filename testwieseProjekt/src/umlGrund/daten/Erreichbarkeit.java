package umlGrund.daten;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Erreichbarkeit {
    private String telefonNummer;
    private String handyNummer;
    private String email;
    private String details;

    public Erreichbarkeit(String telefonNummer, String handyNummer, String email, String details) {
        this.telefonNummer = telefonNummer;
        this.handyNummer = handyNummer;
        this.email = email;
        this.details = details;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getHandyNummer() {
        return handyNummer;
    }

    public void setHandyNummer(String handyNummer) {
        this.handyNummer = handyNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
