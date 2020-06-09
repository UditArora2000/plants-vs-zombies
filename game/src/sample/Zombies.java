package sample;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.text.Element;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Zombies extends Character {

    protected int attack_value;
    protected int speed;
    protected List<Plants> plants_on_field;
    Pane lawn_parent;
    ImageView zombie_image;
    TranslateTransition tt;
    double mili;
    Player player;
    Level level;
    int row_number;
    double duration_of_tt;

    public double getMili() {
        return mili;
    }

    public void setMili(double mili) {
        this.mili = mili;
    }

    public int getRow_number() {
        return row_number;
    }

    Zombies(Pane lp, List<Plants> l, Player player, Level level) {
        lawn_parent=lp;plants_on_field=l;
        tt = new TranslateTransition();

        this.player=player;
        this.level=level;

        //lawn_parent.getChildren().add(zombie_image);
    }

    public void hit_by_pea(int damage, int f) {
        hp-=damage;
        if(hp<=0) {
            tt.stop();
            zombie_image.setImage(new Image(getClass().getResourceAsStream("../main/resources/zombie_normal_dying.gif")));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) { }

            level.zombie_killed(this,f);
            zombie_image.setDisable(true);
            zombie_image.setVisible(false);
            //lawn_parent.getChildren().remove(zombie_image);
        }
    }

    public TranslateTransition getTt() {
        return tt;
    }

    public void attack_plant(Plants p) {
        p.setHp(p.getHp()-attack_value);
    }

    public int getAttack_value() {
        return attack_value;
    }

    public int getSpeed() {
        return speed;
    }

    public List<Plants> getPlants_on_field() {
        return plants_on_field;
    }

    public void setPlants_on_field(List<Plants> plants_on_field) {
        this.plants_on_field = plants_on_field;
    }

    public void attack(Character c) {
        c.setHp(c.getHp()-attack_value);
    }

    public void move() {
        lawn_parent.getChildren().add(zombie_image);
        tt.setDuration(Duration.seconds(duration_of_tt));
        tt.setNode(zombie_image);
        tt.setToX(-1059);
        tt.play();
    }

    public ImageView getZombie_image() {
        return zombie_image;
    }
}



