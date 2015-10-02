package Unterricht.Sept;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 29.09.2015.
 */
public class BibelRA {
    public static void main(String[] args) {
        umlautFinden();


    }

    public static void anFinden(){
        try(BufferedReader br=new BufferedReader(new FileReader("./Testdaten/bibel.txt"))) {

            Pattern p= Pattern.compile("\\b[aA]n\\w");
            String zeile;
            int i=0;
            while ((zeile=br.readLine())!=null){
                Matcher m=p.matcher(zeile);
                while(m.find())i++;

            }

            System.out.println(i);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void umlautFinden(){
        try(BufferedReader br=new BufferedReader(new FileReader("./Testdaten/bibel.txt"))) {

            Pattern p= Pattern.compile("\\b([äöüÄÖÜ][a-zäöüß]+)|([A-Za-z]*[öäüß][a-zöäüß]*)");
            String zeile;
            int i=0;
            while ((zeile=br.readLine())!=null){
                Matcher m=p.matcher(zeile);
                while(m.find()){i++;
                System.out.println(m.group());
                }
            }

            System.out.println(i);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void godFinden(){
        try(BufferedReader br =new BufferedReader(new FileReader("./Testdaten/bibel.txt"))){

            Pattern p= Pattern.compile("God");
            String zeile;
            int i=0;
            while ((zeile=br.readLine())!=null){
                Matcher m=p.matcher(zeile);
                while (m.find()){
                    i++;
                }

            }

            System.out.println(i);

        } catch (IOException e) {
            System.out.println("Lesefehler");;
        }

    }


}
