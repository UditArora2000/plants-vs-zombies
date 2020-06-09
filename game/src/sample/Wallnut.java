package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.concurrent.TimeUnit;

public class Wallnut extends Plants {
    int max_hp;
    Wallnut(ImageView im,Level level){img =im; max_hp=4000; hp=4000;this.level=level;type_of_plant=2;}
    @Override
    public void setHp(int hp) {
        this.hp = hp;
        if(hp<=0) {
            img.setImage(new Image(getClass().getResourceAsStream("../main/resources/walnut_dead.gif")));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) { }
            img.setImage(player.getImg());
            level.plant_removed(tile_no,1);
        }
        else if(hp<max_hp/2) {
            img.setImage(new Image(getClass().getResourceAsStream("../main/resources/walnut_half_life.gif")));
        }
    }
}
