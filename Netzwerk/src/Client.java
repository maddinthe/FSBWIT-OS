import java.io.*;
import java.net.Socket;

/**
 * Created by mtheilen on 18.03.2016.
 */
public class Client {
    public Client(String ip) {
        try (Socket socket = new Socket(ip, 5060);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(
                             socket.getOutputStream()));
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {
            new Thread() {
                public void run() {
                    try {
                        String zeile;
                        while ((zeile = reader.readLine()) != null) {
                            System.out.println(zeile);

                        }
                    } catch (IOException e) {
                        System.out.println("Verbindung zum server verloren");
                        System.exit(0);
                    }
                }
            }.start();

            System.out.println("Verbindung zum Server eingerichtet");
            String zeile;
            while((zeile=br.readLine())!=null){
                writer.write(zeile);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
