import java.io.*;

/**
 * Created by mtheilen on 25.09.2015.
 */
public class HexEditorOut {

    public static void main(String[] args) {
        File in=new File("in.txt");
        File out=new File("HA-Out.txt");

        try(BufferedReader br=new BufferedReader(new FileReader(in));BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(out))){

            String read;
            while ((read=br.readLine())!=null){


            }

        }
        catch (IOException e){
            System.out.println("Dateifehler");

        }



    }


}
