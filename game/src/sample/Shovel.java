package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.awt.*;
import java.io.Serializable;

public class Shovel implements Serializable {

    private Rectangle r;
    private ImageView shovel;
    public boolean shovel_activated = false;

    Shovel() {
        /**
         <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#720e0b" height="50.0" layoutX="303.0" layoutY="7.0" stroke="#7c513a" strokeType="INSIDE" strokeWidth="5.0" width="119.0" />
         <ImageView fitHeight="83.0" fitWidth="77.0" layoutX="328.0" layoutY="-13.0" pickOnBounds="true" preserveRatio="true" rotate="36.0">
         <image>
         <Image url="@../shovel.png" />
         </image>
         </ImageView>

         below code represents the above,but in java instead of java fxml
         **/

        r = new Rectangle();
        r.setArcHeight(5.0);r.setArcWidth(5.0);r.setFill(javafx.scene.paint.Paint.valueOf("#720e0b"));r.setHeight(50);r.setLayoutX(303);r.setLayoutY(7);r.setStroke(Paint.valueOf("#7c513a"));r.setStrokeType(StrokeType.INSIDE);r.setStrokeWidth(5);r.setWidth(119);

        shovel = new ImageView();
        shovel.setImage(new Image(getClass().getResourceAsStream("../main/resources/shovel.png")));
        shovel.setFitHeight(83);shovel.setFitWidth(77);shovel.setLayoutX(328);shovel.setLayoutY(-13); shovel.setPickOnBounds(true);shovel.setPreserveRatio(true);shovel.setRotate(36.0);

        add_shovel_functionality_to_shovel();
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public ImageView getShovel() {
        return shovel;
    }

    public void setShovel(ImageView shovel) {
        this.shovel = shovel;
    }

    public void add_shovel_to_pane(Pane p) {
        p.getChildren().add(r);
        p.getChildren().add(shovel);
    }

    private void add_shovel_functionality_to_shovel() {

        Image planting_shovel = shovel.getImage();

        shovel.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("shovel selected ");

                Dragboard db = shovel.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(planting_shovel);
                shovel_activated = true;
                db.setContent(content);
                event.consume();
            }
        });

        System.out.println("Shovel allowed to use...");
    }

}
