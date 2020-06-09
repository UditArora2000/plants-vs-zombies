package sample;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Plants extends Character {

    public String description;
    protected int unlock_at_level;
    protected int sun_cost;
    protected int time_to_buy;
    protected int time_to_attack;
    protected List<Zombies> zombies_on_field;
    ImageView img;
    protected int tile_no;
    Pane lawn_parent;
    Player player;
    Level level;
    int type_of_plant;

    public int getType_of_plant() {
        return type_of_plant;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
        if(hp<=0) {
            img.setImage(player.getImg());
            level.plant_removed(tile_no,1);
        }
    }

    public int getTile_no() {
        return tile_no;
    }

    public void setTile(int tile_no) {
        this.tile_no = tile_no;
    }

    public ImageView getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public int getUnlock_at_level() {
        return unlock_at_level;
    }

    public int getSun_cost() {
        return sun_cost;
    }

    public int getTime_to_buy() {
        return time_to_buy;
    }

    public int getTime_to_attack() {
        return time_to_attack;
    }

    public List<Zombies> getZombies_on_field() {
        return zombies_on_field;
    }

}