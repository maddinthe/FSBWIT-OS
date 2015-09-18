import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mtheilen on 18.09.2015.
 */
public class HexEditor {
    public static void main(String[] args) {
        File f=new File("Download.png");

        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f))) {

            byte[] b = new byte[16];
            int c = 0;
            int addr=0;
            long maxAddr=f.length();
            while ((c = fis.read(b)) != -1) {
                String front=Integer.toHexString(addr).toUpperCase();
                while (front.length()<6){
                    front=0+front;
                }
                System.out.print(front+": ");
                for (int i = 0; i < c; i++) {
                    int hexb = b[i] & 255;
                    String hex = Integer.toHexString(hexb).toUpperCase();
                    if (hex.length() == 1) hex = 0 + hex;
                    System.out.print(hex + " ");
                }
                System.out.print(new String(b).replaceAll("[^\\p{Print}]","."));
                addr+=16;
                System.out.println();

            }


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }


    }
}
