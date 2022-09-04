package tank;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        int bx=t.x+Tank.WIDTH/2-Bullet.width/2+2;
        int by=t.y+Tank.HEIGHT/2-Bullet.height/2+2;
        new Bullet(bx,by,t.dir,t.group,t.tf);

        if(t.group ==Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }

    }
}
