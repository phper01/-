package tank;

import java.awt.*;
import java.util.Random;

public class Explode {
    Integer x;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    Integer y;


    private  int step=0;

    private TankFrame tf=null;

    public static int WIDTH = ResourceMgr.explodes[1].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[1].getHeight();




    public Explode(Integer x, Integer y, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.tf=tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length)
            tf.explodes.remove(this);
    }


}
