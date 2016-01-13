/**
 * Created by mikep_000 on 6/25/2015.
 */

import javax.swing.*;
import java.awt.*;

public class DoodleArena extends JFrame {

    public DoodleArena(){



        add(new Board());
       // System.out.println(Thread.currentThread().getName());
        setResizable(false);
        pack();

        setTitle("Doodle Arena");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String args[]){

        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                //System.out.println(Thread.currentThread().getName());
                JFrame ex = new DoodleArena();

                ex.setVisible(true);
            }
        });

    }



}
