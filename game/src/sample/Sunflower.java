package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.security.provider.Sun;

import java.util.concurrent.TimeUnit;


public class Sunflower extends Plants {
    int sunposx,sunposy;

    Sunflower(ImageView im, Level level) {
        img=im; hp=300;
        this.level=level;
        type_of_plant=0;
    }

    public double getSunposx() {
        return img.getLayoutX()+52;
    }

    public double getSunposy() {
        return img.getLayoutY()-5;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
        if(hp<0) {
            img.setImage(new Image(getClass().getResourceAsStream("../main/resources/sun_flower_dying.gif")));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) { }
            img.setImage(player.getImg());
            level.plant_removed(tile_no,1);
        }
    }
}