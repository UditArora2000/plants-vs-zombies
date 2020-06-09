package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Level1 extends Level {

    Level1(Player p, Pane pl, int x) {
        super(p,pl,x);
        level_no=1;
        time= new ArrayList<Double>();
        double[] arr= {20, 6, 7, 9, 10, 7, 9, 0.5, 0.5, 0.5};
        list_of_zombies=new ArrayList<Zombies>();
        for(int i=0;i<arr.length;i++) {
            time.add(arr[i]);
            list_of_zombies.add(new NormalZombie(lawn_parent,list_of_plants,p,this));
        }
        System.out.println("Started level - " + level_no);
    }
}
