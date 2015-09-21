import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by mtheilen on 18.09.2015.
 */
public class HexEditor {
    public static void main(String[] args) {
        File f = new File("java-insel.jpg");

        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f))) {

            byte[] b = new byte[16];
            int c = 0;
            int addr = 0;
            long restLength = f.length();
            int maxAddrLength = Long.toHexString(restLength).length();
            while ((c = fis.read(b)) != -1) {
                String front = Integer.toHexString(addr).toUpperCase();
                while (front.length() < maxAddrLength) {
                    front = 0 + front;
                }
                System.out.print(front + ": ");
                int count = 0;
                for (int i = 0; i < c; i++) {
                    int hexb = b[i] & 255;
                    String hex = Integer.toHexString(hexb).toUpperCase();
                    if (hex.length() == 1) hex = 0 + hex;
                    System.out.print(hex + " ");
                    count++;
                    restLength--;
                }
                while (count < 16) {
                    System.out.print("   ");
                    count++;
                }

                System.out.print(new String(b).replaceAll("[^\\p{Print}]", "."));
                addr += 16;
                System.out.println();
                if (restLength < 16)
                    Arrays.fill(b, (byte) 0);
            }


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }


    }
}
