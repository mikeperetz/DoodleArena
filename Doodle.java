import javafx.scene.shape.Circle;

/**
 * Created by mikep_000 on 6/25/2015.
 */
public class Doodle implements Runnable  {

    private int x, y;
    private int size;
    private Circle c;
    private double dx, dy;
    private int id;
    private static int curr = 0;

    public Doodle(int x, int y, int size, double dx, double dy, int id){
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;

        c = new Circle(x, y, size);

    }

    private void checkBounds(){

        if ((x > Board.B_WIDTH + 100 && dx > 0) || (x < -100 && dx < 0) || y > Board.B_HEIGHT + 100 || y < -100){
            size = 0;

            Board.destroy(id);
        }

        checkCollision();

    }

    private void checkCollision(){

        //player's x and y are top left corner! center - (size/2)

        double playerR = Board.getPlayer().getSize();
        double doodleR = size;

        double playerX = Board.getPlayer().getX() + playerR;
        double playerY = Board.getPlayer().getY() + playerR;

        double doodleX = x + doodleR;
        double doodleY = y + doodleR;


        double a = (playerR + doodleR) * (playerR + doodleR);

        double distanceX = playerX - doodleX;
        double distanceY = playerY - doodleY;

        if ( a >= (distanceX * distanceX) + (distanceY * distanceY) ){

            if (size < playerR){
                Board.score += size;

                if (size / 5  == 0)
                    Board.getPlayer().setSize((Board.getPlayer().getSize() + 1));
                else
                    Board.getPlayer().setSize((Board.getPlayer().getSize() + size/5));

                Board.destroy(id);

            }

            else
                Board.setInGame(false);

        }

       // System.out.println(Thread.currentThread().getName()+" (End) ");

    }



    public void run(){

       // System.out.println(Thread.currentThread().getName()+" (Start) ");

        //System.out.println(Thread.currentThread().getName());
               for (int k = 0; k < 10000; k ++){
                   Circle hii = new Circle();
               }



        x += dx;
        y += dy;

        checkBounds();

    }






    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Circle getC() {
        return c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
