package Unterricht.Dez;

import java.util.Random;

/**
 * Created by mtheilen on 11.12.2015.
 */
public class Auto {
    private int tank;
    private boolean reifenOK = true;
    private boolean motorOK = true;

    public Auto() {
        tank = 10;
    }

    public void tanken() {
        tank = 10;

    }

    public void fahren() throws TankLeerException {
        if (!motorOK) throw new MotorGeplatztError();
        if (!reifenOK) throw new ReifenKaputtexception();
        if (tank <= 0) throw new TankLeerException();
        tank--;
        reifenOK = (Math.random() > 0.001);
        motorOK = (Math.random() > 0.0001);


    }

    public void raederWechseln() {
        reifenOK = true;
    }

}

