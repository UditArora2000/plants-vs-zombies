package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


public class Lawnmower implements Serializable {

    ImageView img;
    Pane lawn_parent;
    int row_number;

    public ImageView getImg() {
        return img;
    }

    public int getRow_number() {
        return row_number;
    }

    Lawnmower(double x, double y, Pane pl, int row) {
        img=new ImageView(new Image(getClass().getResourceAsStream("../main/resources/lawn_mower.gif")));
        img.setX(x); img.setY(y); img.setFitHeight(90); img.setFitWidth(100);
        lawn_parent=pl;
        lawn_parent.getChildren().add(img);
        row_number=row;
    }

    public void move() {
        TranslateTransition tt1=new TranslateTransition();
        tt1.setDuration(Duration.seconds(1));
        tt1.setNode(img);
        tt1.setToX(1200);
        tt1.play();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {}
        img.setDisable(true);
        img.setVisible(false);
    }
}
