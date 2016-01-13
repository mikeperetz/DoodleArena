import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mikep_000 on 6/25/2015.
 */
public class Board extends JPanel {


    static final int B_WIDTH = 1000;
    static final int B_HEIGHT = 1000;

    int doodlecount = 0;

    private static int curr = 0;

    int count = 0;

    Timer timer;

    int max = 15;
    int thread_num = 2;

    private static Vector<Integer> null_ids;
    private static Doodle[] doodles;
    private boolean front_back;

    static int score = 0;

    private static Player player;

    private static boolean inGame = true;

    //Board Constructor
    public Board(){

        System.out.println(Thread.currentThread().getName());
        doodles = new Doodle[max];
        doodles[0] = new Doodle(0, 0, 0, 1.2, 1.2, count);
        null_ids = new Vector();
        for (int i = 1; i < max; i ++)
            null_ids.add(new Integer(i) );


        setBackground(Color.black);
        setFocusable(true);


        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        player = new Player();
        this.addKeyListener(player);


        initGame();



    }

    //Initializes the timer and repeats
    private void initGame(){

      final ExecutorService doodleThreads = Executors.newFixedThreadPool(thread_num);





        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {



            @Override
            public void run() {

                //System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < max; i++){
                    if(doodles[i] != null) {
                        doodleThreads.execute(doodles[i]);
                        //doodles[i].run();

                       doodlecount++;
                       System.out.println(doodlecount);
                    }
                    else {
                       // System.out.println(Thread.currentThread().getName());
                        int rSize,  rXPos, rYPos, rStartSide;
                        double rStartSpeed, rXSpeed, rYSpeed;

                        rSize = calculateSize();
                        rStartSide = calculateStartSide();
                        rStartSpeed = calculateStartSpeed(rStartSide, rSize);



                        rXSpeed = calculateXSpeed(rStartSpeed, rStartSide);
                        rYSpeed = calculateYSpeed(rStartSpeed, rStartSide);

                        rYPos = calculateYPosition(rStartSide);
                        rXPos = calculateXPosition(rStartSide);

                       // System.out.println(null_ids.get(0).intValue());
                        doodles[null_ids.get(0).intValue()] = new Doodle(rXPos, rYPos, rSize, rXSpeed, rYSpeed, null_ids.get(0).intValue());
                        null_ids.remove(0);
                        count++;

                    }
                }

                player.update();

                repaint();


            }
        }, 50, 50);




    }

    @Override
    //necessary for painting
    public void paintComponent(final Graphics g){

        super.paintComponent(g);

        doDrawing(g);

    }

    //the bulk of the program. creates doodles, checks game over, etc.
    private void doDrawing(Graphics g){

        if(inGame) {

            //System.out.println(Thread.currentThread().getName());
            String msg = Integer.toString(score);
            Font sfont = new Font("Helvetica", Font.BOLD, 40);
            FontMetrics metr = getFontMetrics(sfont);

            g.setColor(Color.blue);
            g.setFont(sfont);
            g.drawString(msg, 20, 50);

            g.setColor(Color.yellow);

            g.fillOval(player.getX(), player.getY(), player.getSize() * 2, player.getSize() * 2);

            g.setColor(Color.white);

            for (int i = 0; i < max; i++) {



                Doodle temp = doodles[i];
                if (temp != null && temp.getSize() > 0) {
                    g.fillOval(temp.getX(), temp.getY(), temp.getSize() * 2, temp.getSize() * 2);
                }
              //  else System.out.println("yo");


            }

            Toolkit.getDefaultToolkit().sync();
        }

        else{
            timer.cancel();
            gameOver(g);
        }

    }

    private double calculateXSpeed(double rStartSpeed, int rStartSide) {

        if (rStartSide % 2 != 0)
            return 0;

        double tempSpeed =  13 * Math.random() * rStartSpeed;

        while(tempSpeed < 2 && tempSpeed > -2)
            tempSpeed = 13*Math.random() * rStartSpeed;


        return tempSpeed;

    }

    private double calculateYSpeed(double rStartSpeed, int rStartSide) {
        if (rStartSide % 2 == 0)
            return 0;

        double tempSpeed =  13 * Math.random() * rStartSpeed;

        while(tempSpeed < 2 && tempSpeed > -2)
            tempSpeed = 13*Math.random() * rStartSpeed;


        return tempSpeed;

    }

    private int calculateStartSide() {
        return (int)(Math.floor(4*Math.random()));
    }

    private int calculateXPosition(int start) {
        if (start == 0)
            return -100;

        if (start == 2)
            return B_WIDTH + 100;

        return (int) (Math.random()*1000);
    }

    private int calculateYPosition(int start) {
        if (start == 1)
            return -100;

        if (start == 3)
            return B_HEIGHT + 100;

        return (int) (Math.random()*1000);
    }

    private int calculateSize() {

        int rSize = 0;

        rSize = (int)(player.getSize() * 2.7 *Math.random());


        if (rSize < 2)
            rSize += 2;

        return rSize;
    }

    private double calculateStartSpeed(int start, double size){

        double maxSize = (player.getSize() * 1.1);
        double mod = maxSize / size;

        //System.out.println(mod);
        if (mod > 2) mod *= .6;
        if (mod > 1.66) mod *= .8;
        if (mod < .8) mod *= 1.2;

        if( start < 2)
            return mod;
        return -mod;
    }

    private void gameOver(Graphics g){

        String msg = Integer.toString(score);
        Font sfont = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics metr = getFontMetrics(sfont);

        g.setColor(Color.blue);
        g.setFont(sfont);
        g.drawString(msg, 20, 50);

        msg = "Game Over";
        sfont = new Font("Helvetica", Font.BOLD, 40);
        metr = getFontMetrics(sfont);

        g.setColor(Color.red);
        g.setFont(sfont);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

    }

    public static void destroy (int id){
        curr--;
        //doodles[id].setSize(0);
        doodles[id] = null;
        null_ids.add(new Integer(id));


    }

    public static Player getPlayer(){
       return player;
   }

    public static void setInGame(boolean status){
        inGame = status;
    }






}
