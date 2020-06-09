package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;

public class NormalZombie extends Zombies {

    NormalZombie(Pane lp, List<Plants> l, Player player, Level level) {
        super(lp,l,player,level);
        int[] arr={25,126,239,321,441};
        zombie_image=new ImageView(new Image(getClass().getResourceAsStream("../main/resources/zombie_normal.gif")));
        Random random=new Random();
        int ran=random.nextInt(5);
        hp=60; attack_value=50;
        zombie_image.setLayoutY(arr[ran]); //change row number as well
        zombie_image.setLayoutX(1139);
        zombie_image.setFitHeight(138);
        zombie_image.setFitWidth(100);
        row_number=5-ran;
        duration_of_tt=30.1047;
    }

}