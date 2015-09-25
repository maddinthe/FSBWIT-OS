import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mtheilen on 25.09.2015.
 */
public class HexEditorOut {

    public static void main(String[] args) {
        File in=new File("out.txt");
        File out=new File("HA-Out.jpg");

        try(BufferedReader br=new BufferedReader(new FileReader(in));BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(out))){

            String read;
            List<byte[]> bout=new ArrayList<>();
            while ((read=br.readLine())!=null){
                int start=read.indexOf(':');
                read=read.substring(start+2,start+49);
                String[] split=read.split(" ");
                byte[] toSave=new byte[split.length];
                for (int i = 0; i <split.length; i++) {
                    toSave[i]=(byte)Integer.parseInt(split[i],16);
                }
                bout.add(toSave);



            }
            for (byte[] b:bout)
                bos.write(b);
        }
        catch (IOException e){
            System.out.println("Dateifehler");

        }



    }


}
