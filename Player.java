import javafx.scene.shape.Circle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/**
 * Created by mikep_000 on 6/25/2015.
 */
public class Player implements KeyListener {

    private int size = 5;
    private int x, y;
    private int dx, dy;
    private Circle c;

    public Player() {

        x = Board.B_WIDTH / 2 - (size / 2);
        y = Board.B_HEIGHT / 2 - (size / 2);
        c = new Circle(x, y, size);

    }


    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();


        if (keyCode == KeyEvent.VK_RIGHT) {

            dx = 5;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            dy = 5;
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            dx = -5;
        }

        if (keyCode == KeyEvent.VK_UP) {
            dy = -5;
        }

    }

    public void update() {



        if ((x + dx + (size*3)) > Board.B_WIDTH)
            x = Board.B_WIDTH - (size*3);

        else
            x += dx;

        if (x + dx < 0)
            x = 0;
        else
            x += dx;


        if ((y + dy + (size*3)) > Board.B_HEIGHT)
            y = Board.B_HEIGHT - (size*3);
        else
            y += dy;

        if (y + dy < 0)
            y = 0;
        else
            y += dy;





    }



    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();


        if (keyCode == KeyEvent.VK_RIGHT && dx >= 0) {
            dx = 0;
        }

        if (keyCode == KeyEvent.VK_DOWN && dy >= 0) {
            dy = 0;
        }

        if (keyCode == KeyEvent.VK_LEFT && dx < 0) {
            dx = 0;
        }

        if (keyCode == KeyEvent.VK_UP && dy < 0) {
            dy = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    public int getSize(){
        return size;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setSize(int size){
        this.size = size;
    }
}
