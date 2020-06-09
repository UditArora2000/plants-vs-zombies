package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Level5 extends Level {

    Level5(Player p, Pane pl, int x) {
        super(p,pl,x);
        level_no=5;
        time= new ArrayList<Double>();
        double[] arr= {20, 6, 7, 9, 4, 7, 3.5, 10, 7, 9, 0.5, 0.5, 0.5, 2, 0.5, 4, 3, 2};
        list_of_zombies=new ArrayList<Zombies>();
        for(int i=0;i<arr.length;i++) {
            time.add(arr[i]);
            list_of_zombies.add(new NormalZombie(lawn_parent,list_of_plants,p,this));
        }

        list_of_zombies.set(6, new ConeZombie(lawn_parent,list_of_plants,p,this));
        list_of_zombies.set(10, new ConeZombie(lawn_parent,list_of_plants,p,this));
        list_of_zombies.set(13, new ConeZombie(lawn_parent,list_of_plants,p,this));

        list_of_zombies.set(8, new ShieldZombie(lawn_parent,list_of_plants,p,this));
        list_of_zombies.set(15, new ShieldZombie(lawn_parent,list_of_plants,p,this));

        System.out.println("Started level - " + level_no);

    }
}
