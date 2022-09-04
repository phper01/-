package tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    Integer x;

    public Random random=new Random();
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    private Integer speed=1;
    TankFrame tf=null;

    FireStrategy df;
    private  boolean liveing = true;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Group group=Group.BAD;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    boolean moving=true;
    Dir dir=Dir.down;

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    Rectangle rectangle=new Rectangle();
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(Integer x, Integer y, Dir dir, Group group, TankFrame tf,Integer speed) {
        super();
        this.x = x;
        this.y = y;
        this.group=group;
        this.dir = dir;
        this.tf=tf;
        this.speed=speed;

        if(group==Group.GOOD){
            String goodfs=(String)PropertyMgr.get("goodFS");
            try {
                df= (FireStrategy) Class.forName(goodfs).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            String badFS=(String)PropertyMgr.get("badFS");
            try {
                df= (FireStrategy) Class.forName(badFS).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){

        if(!liveing){
            tf.tanks.remove(this);
            return;
        }
        Color c=g.getColor();
        switch(dir) {
            case left:
                g.drawImage(this.group==Group.GOOD ?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
                break;
            case up:
                g.drawImage(this.group==Group.GOOD ?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
                break;
            case right:
                g.drawImage(this.group==Group.GOOD ?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
                break;
            case down:
                g.drawImage(this.group==Group.GOOD ?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move(){
        if(!moving) return;
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

        rectangle.x=this.x;
        rectangle.y=this.y;
        if(this.group== Group.BAD){
            if(random.nextInt(100) > 95) this.fire();

            if(random.nextInt(100) > 95) this.randomdir();
        }

        boundcheck();

    }

    private void boundcheck() {
        if(this.x<0) x=2;
        if(this.y<0) y=20;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH;
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) x=TankFrame.GAME_HEIGHT-Tank.HEIGHT;
    }

    private void randomdir(){
        if(this.group == Group.BAD){
            this.dir=Dir.values()[random.nextInt(4)];
        }
    }

    public void fire() {
        df.fire(this);
    }

    public  void die(){
        this.liveing=false;
    }

}
