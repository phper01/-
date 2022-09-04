package tank;

import sun.net.www.content.text.Generic;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;
    public static int width=ResourceMgr.bulletD.getWidth();
    public static int height=ResourceMgr.bulletD.getHeight();

    Rectangle rectangle=new Rectangle();
    public Group group=Group.BAD;
    private int x, y;

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

    private Dir dir;
    private boolean live =true;
    private TankFrame tf=null;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.group=group;
        this.dir = dir;
        this.tf=tf;

        rectangle.x=this.x;
        rectangle.y=this.y;
        rectangle.width=width;
        rectangle.height=height;

        tf.bulletList.add(this);
    }

    public void paint(Graphics g){
        if(!live){
            tf.bulletList.remove(this);
        }
        switch(dir) {
            case left:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case up:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case right:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case down:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        switch (dir){
            case left:
                x-=speed;break;
            case right:
                x+=speed;break;
            case up:
                y-=speed;break;
            case down:
                y+=speed;break;
        }

        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live=false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void collidewith(Tank tank) {
        if(this.group == tank.getGroup()) return;
        Rectangle rectangle1=new Rectangle(this.x,this.y,width,height);
        Rectangle rectangle2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rectangle1.intersects(rectangle2)){
            tank.die();
            this.die();
            int ex=tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int ey=tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
            tf.explodes.add(new Explode(ex,ey,tf));
        }
    }

    private void die() {
        this.live=false;
    }
}
