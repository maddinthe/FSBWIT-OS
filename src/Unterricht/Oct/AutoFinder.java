package Unterricht.Oct;

import java.io.*;
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
        List<Auto> autos = af.autoLister(ausg);
        Set<Auto> einzelautos = new HashSet<>(autos);
        autos = new ArrayList<>(einzelautos);
        Collections.sort(autos, new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                int ret=o1.ezD[1]-o2.ezD[1];
                if(ret==0){
                    ret=o1.ezD[0]-o2.ezD[0];
                if (ret==0) {
                    ret = o1.name.compareTo(o2.name);
                    if (ret == 0) {
                        ret = o1.km - o2.km;
                        if (ret == 0) {
                            ret = o1.ez.compareTo(o2.ez);
                            if (ret == 0)
                                ret = o1.preis - o2.preis;
                        }
                    }
                }}

                return ret;
            }
        });
        autos.forEach(System.out::println);
        System.out.println(autos.size());
        System.out.println(System.currentTimeMillis() - start);
        Auto test = autos.get((int) (Math.random() * autos.size()));
        System.out.println(String.format("%s,Extras: %s", test, test.extras));

        int preis = 0;
        for (Auto a : autos) {
            preis += a.getPreis();
        }
        System.out.println(String.format("Gesamtpreis:%d€", preis));
    }

    public List<Auto> autoLister(List<String> autos) {
        Pattern ezP = Pattern.compile("EZ\\s?([01]\\d/[12]\\d{3})");
        Pattern huP = Pattern.compile("HU( )?(([nN](EU|eu))|((0\\d)|1[0-2])/\\d{4})");
        Pattern anbieterP = Pattern.compile("[Hh]ändler|[Pp]rivat");
        Pattern artP = Pattern.compile("(Kleinwagen|Kombi|Limousine|Van|Minibus)");
        Pattern leistungP = Pattern.compile("\\d{2,3}\\s?[kK][wW]\\s?\\(?\\d{2,3}\\s?[pP][sS]\\)?");
        Pattern kmP = Pattern.compile("(((\\d{1,3}.)?((\\d{3}.?)*)\\d{3})|\\d{1,2})( )?[kK][mM]");
        Pattern preisP = Pattern.compile("(((\\d{1,3}.)?((\\d{3}.?)*)\\d{3})|\\d{1,2})( )?€");
        Pattern kraftstoffP = Pattern.compile("([dD]iesel|[Bb]enzin|([Aa]uto|[Ee]rd)gas|[Hh]ybrid)");
        Pattern schaltungP = Pattern.compile("\\w*getriebe|Automatik");
        Pattern unfallP = Pattern.compile("\\b[Uu]nfall[a-z]+\\b");
        Matcher ezM, huM, anbieterM, artM, leistungM, kmM, preisM, kraftstoffM, schaltungM, unfallM;
        String nameS, ortS, ezS = null, huS = null, anbieterS = null, artS = null, leistungS = null, kmS = null, preisS = null, kraftstoffS = null, schaltungS = null, unfallS = null;
        List<Auto> ret = new LinkedList<>();
        for (String autoS : autos) {
            String[] zerlegt = autoS.split("\n");
            nameS = zerlegt[0];
            ortS = zerlegt[1];
            List<String> extras = new LinkedList<>();

            for (int i = 2; i < zerlegt.length; i++) {
                if ((anbieterM = anbieterP.matcher(zerlegt[i])).find()) {
                    anbieterS = anbieterM.group();
                } else if ((artM = artP.matcher(zerlegt[i])).find()) {
                    artS = artM.group();
                } else if ((leistungM = leistungP.matcher(zerlegt[i])).find()) {
                    leistungS = leistungM.group();
                    if ((kraftstoffM = kraftstoffP.matcher(zerlegt[i])).find()) {
                        kraftstoffS = kraftstoffM.group();
                    }
                } else if ((schaltungM = schaltungP.matcher(zerlegt[i])).find()) {
                    schaltungS = schaltungM.group();
                } else if ((unfallM = unfallP.matcher(zerlegt[i])).find()) {
                    unfallS = unfallM.group();
                } else if ((huM = huP.matcher(zerlegt[i])).find()) {
                    huS = huM.group(2);
                } else if ((preisM = preisP.matcher(zerlegt[i])).find()) {
                    preisS = preisM.group(1);
                } else if ((kmM = kmP.matcher(zerlegt[i])).find()) {
                    kmS = kmM.group(1);
                } else if ((ezM = ezP.matcher(zerlegt[i])).find()) {
                    ezS = ezM.group(1);
                } else extras.add(zerlegt[i]);
            }
            Auto toSave = new Auto(unfallS, ortS, nameS, ezS, huS, anbieterS, artS, kmS, leistungS, preisS, kraftstoffS, schaltungS);
            toSave.setExtras(extras);
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

    public static class Auto implements Comparable<Auto> {
        private String ort;
        private String name;
        private String ez;
        private int[] ezD=new int[2];
        private String hu;
        private String anbieter;
        private String art;
        private int km;
        private String leistung;
        private int preis;
        private String kraftstoff;
        private String schaltung;
        private String unfall;
        private List<String> extras;

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
            if (ez!=null){
                String[] zerlegt=ez.trim().split("/");
                ezD[0]=Integer.parseInt(zerlegt[0]);
                ezD[1]=Integer.parseInt(zerlegt[1]);
            }
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
            return (String.format("%s, %s, %,dkm, %,d€, EZ %02d/%04d, HU %s, %s, %s", name, ort, km, preis, ezD[0],ezD[1], hu, unfall, kraftstoff, leistung));
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


        public int compareTo(Auto o) {
            int ret = this.name.compareTo(o.name);
            if (ret == 0) {
                ret = this.km - o.km;
                if (ret == 0) {
                    ret = ez.compareTo(o.ez);
                    if (ret == 0)
                        ret = this.preis - o.preis;
                }
            }


            return ret;
        }


        public int hashCode() {
            return name.hashCode() ^ km ^ ez.hashCode();
        }


        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Auto)) return false;
            Auto o = (Auto) obj;
            return (name.equals(o.name) && km == o.km && ez.equals(o.ez));
        }
    }

}