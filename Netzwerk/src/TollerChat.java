/**
 * Created by mtheilen on 18.03.2016.
 */
public class TollerChat {
    public static void main(String[] args) {
        if(args.length ==1){
            new Client(args[0]);

        }else new Server();
    }
}
