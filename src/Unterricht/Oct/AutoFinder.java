package Unterricht.Oct;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 20.10.2015.
 */
public class AutoFinder {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AutoFinder af = new AutoFinder();
        List<String> ausg = af.autosToString(af.getAutoFiles("Y:\\3_XI\\XI_6\\302_SOP_OOP\\Autos\\"));
        ausg = af.autoBundler(ausg);
        //ausg.forEach(System.out::println);
        List<Auto> autos = af.autoLister(ausg);
        autos.forEach(System.out::println);
        System.out.println(autos.size());
        System.out.println(System.currentTimeMillis() - start);


    }


    public List<Auto> autoLister(List<String> autos) {
        Pattern ezP = Pattern.compile("EZ\\s?([01]\\d/[12]\\d{3})");
        Pattern huP = Pattern.compile("HU\\s?((0\\d)|(1[0-2])/(1\\d{3})|(20[01]\\d))");
        Pattern anbieterP = Pattern.compile("[Hh]ändler|[Pp]rivat");
        Pattern artP = Pattern.compile("(Kleinwagen|Kombi|Limousine|Van|Minibus)");
        Pattern leistungP = Pattern.compile("\\d{2,3}\\s?[kK][wW]\\s?\\(?\\d{2,3}\\s?[pP][sS]\\)?");
        Pattern kmP = Pattern.compile("[^/](\\d{0,3}(\\.)?\\d{3})\\s?[kK][mM]");
        Pattern preisP = Pattern.compile("(\\d{0,3}(\\.)?\\d{3})\\s?€");
        Pattern kraftstoffP = Pattern.compile("([dD]iesel|[Bb]enzin|([Aa]uto|[Ee]rd)gas|[Hh]ybrid)");
        Pattern schaltungP = Pattern.compile("\\w*getriebe");
        Matcher ezM, huM, anbieterM, artM, leistungM, kmM, preisM, kraftstoffM, schaltungM;
        String nameS, ortS, ezS = null, huS = null, anbieterS = null, artS = null, leistungS = null, kmS = null, preisS = null, kraftstoffS = null, schaltungS = null;
        List<Auto> ret = new LinkedList<>();
        for (String autoS : autos) {
            String[] zerlegt = autoS.split("\n");
            nameS = zerlegt[0];
            autoS=autoS.replace(zerlegt[0], "");
            ortS = zerlegt[1];
            autoS=autoS.replace(zerlegt[1], "");
            if ((ezM = ezP.matcher(autoS)).find()) {
                ezS = ezM.group(1);
                autoS=autoS.replace(ezM.group(), "");
            }
            if ((huM = huP.matcher(autoS)).find()) {
                huS = huM.group(1);
                autoS=autoS.replace(huM.group(), "");
            }
            if ((anbieterM = anbieterP.matcher(autoS)).find()) {
                anbieterS = anbieterM.group();
                autoS=autoS.replace(anbieterS, "");
            }
            if ((artM = artP.matcher(autoS)).find()) {
                artS = artM.group();
                autoS=autoS.replace(artS, "");
            }
            if ((leistungM = leistungP.matcher(autoS)).find()) {
                leistungS = leistungM.group();
                autoS=autoS.replace(leistungS, "");
            }
            if ((kmM = kmP.matcher(autoS)).find()) {
                kmS = kmM.group(1);
                autoS=autoS.replace(kmM.group(), "");
            }
            if ((preisM = preisP.matcher(autoS)).find()) {
                preisS = preisM.group(1);
                autoS=autoS.replace(preisM.group(), "");
            }
            if ((kraftstoffM = kraftstoffP.matcher(autoS)).find()) {
                kraftstoffS = kraftstoffM.group();
                autoS=autoS.replace(kraftstoffS, "");
            }
            if ((schaltungM = schaltungP.matcher(autoS)).find()) {
                schaltungS = schaltungM.group();
                autoS=autoS.replace(schaltungS, "");
            }
            Auto toSave = new Auto(null, ortS, nameS, ezS, huS, anbieterS, artS, kmS, leistungS, preisS, kraftstoffS, schaltungS);
            System.out.println(Arrays.toString(autoS.replaceAll("[, ]", "").replaceAll("(?m)^\\s+$","").replaceAll("\n", " ").trim().split("[, ]")));
            ret.add(toSave);
        }
        return ret;
    }

    public List<String> autoBundler(List<String> autos) {
        List<String> ret = new LinkedList<>();
        autos = new ArrayList<String>(autos);
        for (int index = 0; index < autos.size(); index++) {
            String ort = autos.get(index);
            if (ort.matches("^DE( )?-( )?\\d{5} .*$")) {
                StringBuilder toSave = new StringBuilder();
                toSave.append(autos.get(index - 1));
                toSave.append("\n");
                toSave.append(ort);
                toSave.append("\n");
                for (int i = index + 1; ; i++) {
                    String zeile = autos.get(i);
                    if (zeile.startsWith("Finanzierung")) {
                        index = i;
                        break;
                    } else {
                        toSave.append(zeile);
                        toSave.append("\n");
                    }
                }
                ret.add(toSave.toString());
            }
        }


        return ret;
    }

    public List<String> autosToString(List<File> autos) {
        List<String> ret = new LinkedList<>();

        for (File f : autos) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String read;
                while ((read = br.readLine()) != null) {
                    ret.add(read);
                }

            } catch (IOException e) {
                System.out.println("Dateifehler bei " + f.getName());

            }
        }
        return ret;
    }

    public List<File> getAutoFiles(String dirPath) {
        return getAutoFiles(new File(dirPath));
    }

    public List<File> getAutoFiles(File dir) {
        File[] dateien = dir.listFiles();
        List<File> autos = new LinkedList<>();
        Pattern p = Pattern.compile("(Autos_)(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\b");
        for (File f : dateien)
            if (f.isFile() && (p.matcher(f.getName()).find()))
                autos.add(f);
        return autos;
    }

    public class Auto {
        String ort;
        String name;
        String ez;
        String hu;
        String anbieter;
        String art;
        int km;
        String leistung;
        int preis;
        String kraftstoff;
        String schaltung;
        String unfall;
        List<String> extras;

        public Auto(String name, String ort, int km, int preis) {
            this.name = name;
            this.ort = ort;
            this.km = km;
            this.preis = preis;
        }

        public Auto(String unfall, String ort, String name, String ez, String hu, String anbieter, String art, String km, String leistung, String preis, String kraftstoff, String schaltung) {
            this.unfall = unfall;
            this.ort = ort;
            this.name = name.trim();
            this.ez = ez;
            this.hu = hu;
            this.anbieter = anbieter;
            this.art = art;
            if (km != null) {
                this.km = Integer.parseInt(km.replace(".", ""));
            }
            this.leistung = leistung;
            if (preis != null) {
                this.preis = Integer.parseInt(preis.replace(".", ""));
            }
            this.kraftstoff = kraftstoff;
            this.schaltung = schaltung;
        }

        public String toString() {
            return (String.format("%s, %s, %dkm, %d€", name, ort, km, preis));
        }

        public String getOrt() {
            return ort;
        }

        public void setOrt(String ort) {
            this.ort = ort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEz() {
            return ez;
        }

        public void setEz(String ez) {
            this.ez = ez;
        }

        public String getHu() {
            return hu;
        }

        public void setHu(String hu) {
            this.hu = hu;
        }

        public String getAnbieter() {
            return anbieter;
        }

        public void setAnbieter(String anbieter) {
            this.anbieter = anbieter;
        }

        public String getArt() {
            return art;
        }

        public void setArt(String art) {
            this.art = art;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public String getLeistung() {
            return leistung;
        }

        public void setLeistung(String leistung) {
            this.leistung = leistung;
        }

        public int getPreis() {
            return preis;
        }

        public void setPreis(int preis) {
            this.preis = preis;
        }

        public String getKraftstoff() {
            return kraftstoff;
        }

        public void setKraftstoff(String kraftstoff) {
            this.kraftstoff = kraftstoff;
        }

        public String getSchaltung() {
            return schaltung;
        }

        public void setSchaltung(String schaltung) {
            this.schaltung = schaltung;
        }

        public String getUnfall() {
            return unfall;
        }

        public void setUnfall(String unfall) {
            this.unfall = unfall;
        }

        public List<String> getExtras() {
            return extras;
        }

        public void setExtras(List<String> extras) {
            this.extras = extras;
        }
    }

}