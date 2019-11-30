import javax.swing.*;
import java.awt.*;
//Trevor- also going to add a keyboard class
public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;

    private Thread thread;
    private JFrame frame;
   // private Keyboard key;
    private boolean running = false;

    public Game()
    {
        //key = new Keyboard();

    }

    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()	{
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e)	{
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;	            //nanosecond converter
        double delta = 0;
        int framesPerSec = 0;
        int updatesPerSec = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while(delta >= 1) {	                            //while loop is true about 60 times every second
                update();	                                //tick update, called roughly every second
                updatesPerSec++;
                delta--;
            }
            render();                                       //called as fast as possible, as many frames per second
            framesPerSec++;
            if(System.currentTimeMillis() - timer > 1000) {	//while loop is true ever second
                timer += 1000;	                            //1000 milisecs = 1 sec we add this time to timer to keep its time up to date with the System time
                System.out.println(updatesPerSec + " updates, " + framesPerSec + " Fps.");
                //frame.setTitle("DeathGame" + " | " + updatesPerSec + " updates, " + framesPerSec + " Fps.");
                updatesPerSec = 0;
                framesPerSec = 0;
            }
        }
    }

    public void update()
    {
        //called roughly 60 times a second
        //key.update();
        //player.update; and so on
    }

    public void render()
    {

    }

    public static void main(String[] args)	{
        Game game = new Game();
        //game.frame.setResizable(false);
        //game.frame.setTitle("Space invaders");
        //game.frame.add(game); //remember game is sub class of canvas
        //game.frame.pack();
        //game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //game.frame.setLocationRelativeTo(null);
        //game.frame.setVisible(true);

        game.start();

    }
}
