package Unterricht.Dez;

/**
 * Created by mtheilen on 11.12.2015.
 */
public class FahrSim {
    public static void main(String[] args) {
        Auto a=new Auto();

        for (int i = 0; i < 10000; i++) {
            try {
                a.fahren();
                System.out.print("fahren ");
            } catch (TankLeerException e) {
                System.out.println("tanken");
                a.tanken();
            }catch (ReifenKaputtexception e1){
                a.raederWechseln();
                System.out.println("reifen Wechseln");

            }

        }
    }
}
