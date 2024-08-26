package csd.uoc.gr.A24;
import org.jfugue.Player;

public class myThread extends Thread implements Runnable {
        public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Player p = new Player();
                p.play("[50");}
}