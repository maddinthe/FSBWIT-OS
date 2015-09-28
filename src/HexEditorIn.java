import java.io.*;

/**
 * Created by mtheilen on 18.09.2015.
 */
public class HexEditorIn {
    public static void main(String[] args) {
        File f = new File("java-insel.jpg");
        File out=new File("out.txt");
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f));BufferedWriter bw=new BufferedWriter(new FileWriter(out))) {

            byte[] b = new byte[16];
            int c = 0;
            int addr = 0;
            int maxAddrLength = Long.toHexString(f.length()).length();
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