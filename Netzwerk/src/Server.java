import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

/**
 * Created by mtheilen on 18.03.2016.
 */
public class Server {
    private List<User> users = new Vector<>();

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(5060);

        ) {
            System.out.println("Server eingerichtet");
            while (true) {
                try {
                    users.add(new User(
                            serverSocket.accept()
                    ));

                } catch (IOException e) {
                    //kommt hier her wenn etwas beim verbindungsaufbau schief ging
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            // kommt hier hin, wenn der port schon belegt ist
            // oder man darf gar keinen port öffnen
            e.printStackTrace();

        }
    }

    private void neueNachricht(User user, String nachricht) {
        System.out.println(nachricht);
        for(User u:users){
            if(u!=user){
                u.sendeNachricht(nachricht);
            }
        }

    }
    private void loeschMich(User user) {
        users.remove(user);
    }

    private class User {
        private Socket socket;

        public User(Socket socket) {
            this.socket = socket;
            new Thread() {
                public void run() {
                    String ip = socket.getInetAddress().toString();
                    System.out.println("Client mit " + ip + " angemeldet");
                    try (BufferedReader reader =
                                 new BufferedReader(
                                         new InputStreamReader(
                                                 socket.getInputStream()))
                    ) {
                        String zeile;
                        while ((zeile = reader.readLine()) != null) {
                            neueNachricht(User.this, zeile);
                        }

                    } catch (IOException e) {
                        // wenn er hier landet ist der Client nicht mehr Erreichbar;
                        System.out.println("Client "+ip+" getrennt");
                        loeschMich(User.this);
                    }
                }
            }.start();
        }

        public void sendeNachricht(String nachricht) {
            try{
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write(nachricht);
                bw.newLine();
                bw.flush();

            }catch (IOException e){

            }
        }
    }



}
