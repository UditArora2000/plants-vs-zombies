package sample;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CherryBomb extends Plants {

    List<Zombies> loz;
    CherryBomb(List<Zombies> l, ImageView im, int tile, Player pl, Level level) {
        img=im;
        loz=new ArrayList<>(l);
        tile_no=tile;
        this.level=level;
        player=pl;
        type_of_plant=3;
    }

    public void explode() {
        double xcen=img.getLayoutX()+47;
        double ycen=img.getLayoutY()+44.5;
//        System.out.println(xcen);
//        System.out.println(ycen);
        for(int i=0;i<loz.size();i++) {
            double zx=loz.get(i).getZombie_image().getBoundsInParent().getMinX();
            double zy=loz.get(i).getZombie_image().getBoundsInParent().getMinY();
            System.out.println(zx+" "+zy);
            if(xcen-141<zx && zx<xcen+141 && ycen-133.5<zy && zy<ycen+133.5) {
                //level.zombie_killed(loz.get(i), 1);
                loz.get(i).hit_by_pea(10000,1);
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) { }
        img.setImage(player.getImg());
        System.out.println("reached");
        //level.plant_removed(tile_no,0);
    }
}