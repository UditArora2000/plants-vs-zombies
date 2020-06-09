package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;
import java.io.Serializable;

public class Player implements Serializable {

    Level level;
    Image img;

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    private int no_of_suns=50;

    public void sun_collected(int n) {
        no_of_suns+=n;
        System.out.println("no of suns - " + no_of_suns);
        level.update_no_of_suns(no_of_suns);
    }

    public void set_level(Level l) {
        level=l;
        level.update_no_of_suns(no_of_suns);
    }

    public void plant_purchased(int x) {
        System.out.println("purchased");
        sun_collected(-x);
    }

    public int getNo_of_suns() {
        return no_of_suns;
    }
}
