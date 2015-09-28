package Hausaufgaben.HaHexEditor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtheilen on 25.09.2015.
 */
public class HexEditorInOut {
    public static void main(String[] args) {
        HexEditorInOut hex = new HexEditorInOut();
        File hexdump = new File("./Testdaten/HexEdit/hexdump.txt");
        File eingabe = new File("./Testdaten/HexEdit/java-insel.jpg");
        File ausgabe = new File("./Testdaten/HexEdit/HaOut.jpg");
        hex.fileToHex(eingabe, hexdump);
        hex.hexToFile(hexdump, ausgabe);


    }


    public void hexToFile(File in, File out) {
        try (BufferedReader br = new BufferedReader(new FileReader(in)); BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(out))) {

            String read;
            List<byte[]> bout = new ArrayList<>();
            while ((read = br.readLine()) != null) {
                int start = read.indexOf(':');
                read = read.substring(start + 2, start + 49).trim();
                String[] split = read.split(" ");
                byte[] toSave = new byte[split.length];
                for (int i = 0; i < split.length; i++) {
                    toSave[i] = (byte) Integer.parseInt(split[i], 16);
                }
                bos.write(toSave);
            }

        } catch (IOException e) {
            System.out.println("Dateifehler");

        }


    }

    public void fileToHex(File in, File out) {
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(in)); BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {

            byte[] b = new byte[16];
            int c = 0;
            int addr = 0;
            int maxAddrLength = Long.toHexString(in.length()).length();
            while ((c = fis.read(b)) != -1) {
                StringBuilder front = new StringBuilder(Integer.toHexString(addr).toUpperCase());
                while (front.length() < maxAddrLength)
                    front.insert(0, 0);

                front.append(": ");
                for (int i = 0; i < c; i++) {
                    int hexb = b[i] & 255;
                    String hex = Integer.toHexString(hexb).toUpperCase();
                    if (hex.length() == 1) hex = 0 + hex;
                    front.append(hex + " ");

                }
                while (c < 16) {
                    front.append("   ");
                    b[c++] = 0;

                }
                front.append(" ");
                front.append(new String(b).replaceAll("[^\\p{Print}]", "."));
                addr += 16;
                bw.append(front);
                bw.newLine();
            }


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }

    }


}
