package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class lawn_controller {

    Pane lawn_parent;
    Player player;
    Shovel shovel;
    Image blank;
    ImageView special_power;
    public static Level levell;
    // experimenting

    ArrayList<ImageView> all_tiles = new ArrayList<>();
    ArrayList<ImageView> buying_tiles = new ArrayList<>();

    public ArrayList<ImageView> getAll_tiles() {
        return all_tiles;
    }

    int selected_buying_plant;  // can be -> "0 = sf", "1 = ps", "2 = wn", "3 = cb"

    {
        blank = new Image(getClass().getResourceAsStream("../main/resources/tiles/3.png"));
    }

    private void add_shovel() {
        shovel = new Shovel();
        shovel.add_shovel_to_pane(lawn_parent);
    }

    public void display_buying_tiles(int level) {
        // this function will display required buying tiles according to level no...

        ImageView buy_sunflower =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/suncost.png")));
        ImageView buy_peashooter =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/peacost.png")));
        ImageView buy_cheerybomb =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/cherrycost.png")));
        ImageView buy_walnut =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/wallnutcost.png")));

        buying_tiles.add(buy_sunflower);
        buying_tiles.add(buy_peashooter);
        buying_tiles.add(buy_walnut);
        buying_tiles.add(buy_cheerybomb);

        for(int i=0;i<=level;i++) {
            if(i < buying_tiles.size()) {
                ImageView temp = buying_tiles.get(i);
                temp.setLayoutX(9);
                temp.setLayoutY( 68*i +7);
                temp.setFitWidth(102);
                temp.setFitHeight(57);
            }
        }

        for (int i=level+1;i<=3;i++) {
            buying_tiles.get(i).setOpacity(0);
        }

        // to set the setOnDragDetected event handler of all the buying tiles
        setting_drag_detected(buying_tiles);
    }

    private void setting_drag_detected(ArrayList<ImageView> buying_tiles) {
        // function used to set "setOnDragDetected" for each buying tile ...

        Image planting_sunflower = new Image(getClass().getResourceAsStream("../main/resources/HD_Sunflower.png"));
        Image planting_peashooter = new Image(getClass().getResourceAsStream("../main/resources/PEAPLANT.JPG"));
        Image planting_cheerybomb = new Image(getClass().getResourceAsStream("../main/resources/CHERRYBOMB.png"));
        Image planting_walnut = new Image(getClass().getResourceAsStream("../main/resources/walnut_full_life.gif"));



        ArrayList<Image> planting_tiles = new ArrayList<>();
        planting_tiles.add(planting_sunflower);
        planting_tiles.add(planting_peashooter);
        planting_tiles.add(planting_walnut);
        planting_tiles.add(planting_cheerybomb);


        for(int i=0; i<planting_tiles.size(); i++) {
            ImageView present_tile = buying_tiles.get(i);

            int finalI = i;
            present_tile.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //System.out.println("drag detected on a buying-tile no. - " + finalI);

                    // setting selected_buying_plant
                    selected_buying_plant = finalI;

                    Dragboard db = present_tile.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(planting_tiles.get(finalI));
                    db.setContent(content);
                    event.consume();
                }
            });
        }
    }


    public void set_all_tiles() {
        // function is used to set all the tiles from 1 to 45 in the arraylist "all_tiles"
        // with 1st tile at 0th index and so on ...

        // setting LayoutX arraylist
        ArrayList<Integer> layoutX = new ArrayList<>();
        layoutX.add(205);
        layoutX.add(295);
        layoutX.add(393);
        layoutX.add(486);
        layoutX.add(584);
        layoutX.add(674);
        layoutX.add(765);
        layoutX.add(861);
        layoutX.add(956);

        for(int i=0; i<9;i++) {
            ImageView temp = new ImageView();
            temp.setLayoutX(layoutX.get(i));
            temp.setLayoutY(493);
            temp.setFitHeight(85);
            temp.setFitWidth(94);
            all_tiles.add(temp);
        }
        for(int i=0; i<9;i++) {
            ImageView temp = new ImageView();
            temp.setLayoutX(layoutX.get(i));
            temp.setLayoutY(393);
            temp.setFitHeight(85);
            temp.setFitWidth(94);
            all_tiles.add(temp);
        }
        for(int i=0; i<9;i++) {
            ImageView temp = new ImageView();
            temp.setLayoutX(layoutX.get(i));
            temp.setLayoutY(291);
            temp.setFitHeight(85);
            temp.setFitWidth(94);
            all_tiles.add(temp);
        }
        for(int i=0; i<9;i++) {
            ImageView temp = new ImageView();
            temp.setLayoutX(layoutX.get(i));
            temp.setLayoutY(189);
            temp.setFitHeight(85);
            temp.setFitWidth(94);
            all_tiles.add(temp);
        }
        for(int i=0; i<9;i++) {
            ImageView temp = new ImageView();
            temp.setLayoutX(layoutX.get(i));
            temp.setLayoutY(84);
            temp.setFitHeight(85);
            temp.setFitWidth(94);
            all_tiles.add(temp);
        }

        for (ImageView i: all_tiles) {
            i.setImage(blank);
            i.setOpacity(0.3);
        }

        for(ImageView i: all_tiles) {
            i.setOnDragEntered(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Bloom bl=new Bloom();
                    bl.setThreshold(0);
                    i.setEffect(bl);
                }
            });

            i.setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    i.setEffect(null);
                }
            });

            i.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != i) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                }
            });

            i.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {


                    String image_url = ""; // for demo, creating always sunflower image
                    System.out.println("dropped detected at tile - " + i);

                    if(shovel!=null && shovel.shovel_activated ){
                        if(i.getImage()!=blank) {
                            System.out.println("Shovel removing the tile no - " + i);
                            i.setImage(blank);
                            i.setOpacity(0.3);
                            shovel.shovel_activated = false;
                            levell.plant_removed(all_tiles.indexOf(i)+1,0);

                        }
                        else {
                            System.out.println("No plant at tile-no- " + i + " (so aborting shovel)");
                        }

                    }


                    // i.e can only place the plant if there was not any before
                    else if(i.getImage()==blank && sun_above_cost(selected_buying_plant,player.getNo_of_suns())) {

                        // creating object of the selected plant object
                        if(selected_buying_plant==0) {
                            image_url = "../main/resources/sun_flower.gif";
                            // create object of sunflower type and
                            // set image url = object.url;
                            // and add that in the array of the player
                        }
                        else if (selected_buying_plant==1) {
                            image_url = "../main/resources/pea_shooter.gif";
                            // create object of pea-shooter type and
                            // set image url = object.url;
                        }
                        else if (selected_buying_plant==2) {
                            image_url = "../main/resources/walnut_full_life.gif";
                            //
                        }
                        else if (selected_buying_plant==3) {
                            image_url = "../main/resources/CHERRYBOMB.png";
                            //
                        }

                        // now setting the image as the image of the object
                        i.setImage(new Image(getClass().getResourceAsStream(image_url)));
                        i.setOpacity(1);

                        // disabling the required buying tile ...
                        System.out.println("plant placed successfully...");
                        add_media_view(selected_buying_plant);

                        plant_placed(selected_buying_plant,i,all_tiles.indexOf(i)+1);
                    }

                    // drop completed
                    if(shovel!=null) {shovel.shovel_activated = false;}
                    //shovel.shovel_activated = false;
                    event.setDropCompleted(true);
                    event.consume();

                }
            });
        }
    }

    ArrayList<MediaPlayer> media_player = new ArrayList<>();
    ArrayList<MediaView> media_view = new ArrayList<>();

    private void create_media_player(int level) {

        ArrayList<String> url = new ArrayList<>();
        url.add("/animations/sunflower.mp4");
        url.add("/animations/pea.mp4");
        url.add("/animations/walnut.mp4");
        url.add("/animations/cherry.mp4");

        for(int i=0;i<4;i++) {
            //Media m = new Media("");
            MediaPlayer mp = new MediaPlayer(new Media(this.getClass().getResource(url.get(i)).toExternalForm()));
            MediaView mv = new MediaView(mp);

            mv.setLayoutX(9);
            mv.setLayoutY( 68*i +7);
            mv.setFitWidth(102);
            mv.setFitHeight(57);

            mv.setDisable(true);
            mv.setOpacity(0);
            mv.setPreserveRatio(false);


            media_player.add(mp);
            media_view.add(mv);

            mp.setOnEndOfMedia(() -> {

                System.out.println("media ended");
                mv.setDisable(true);
                mv.setOpacity(0);
            });

        }
    }

    private void add_media_view(int i) {

        ArrayList<String> url = new ArrayList<>();
        url.add("/animations/sunflower.mp4");
        url.add("/animations/pea.mp4");
        url.add("/animations/walnut.mp4");
        url.add("/animations/cherry.mp4");

        media_view.get(i).setOpacity(0.5);
        media_view.get(i).setDisable(false);

        //media_player.get(i).getMedia() = ;
        media_player.set(i, new MediaPlayer(new Media(this.getClass().getResource(url.get(i)).toExternalForm()))) ;
        media_view.get(i).setMediaPlayer(media_player.get(i));

        media_player.get(i).setOnEndOfMedia(() -> {

            System.out.println("media ended");
            media_view.get(i).setDisable(true);
            media_view.get(i).setOpacity(0);
        });
        media_player.get(i).play();
    }
    private void add_special_power() {

        /**
         <ImageView fx:id="cloud" fitHeight="112.0" fitWidth="133.0" layoutX="7.0" layoutY="547.0" pickOnBounds="true">
         <image>
         <Image url="@../special_power/static.png" />
         </image>
         </ImageView>
         */
        //dsds;

        special_power = new ImageView(new Image(getClass().getResourceAsStream("../main/resources/special_power/static.png")));
        special_power.setFitHeight(112);
        special_power.setFitWidth(133);
        special_power.setLayoutX(7);
        special_power.setLayoutY(547);

        lawn_parent.getChildren().add(special_power);

        // setting functions of special power image

        special_power.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Glow glow = new Glow();
                glow.setLevel(0);
                special_power.setEffect(glow);
            }
        });

        special_power.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Glow glow = new Glow();
                glow.setLevel(0.7);
                special_power.setEffect(glow);
            }
        });

        special_power.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Special power activated");
                for(int i=0;i<5;i++) {
                    ImageView falling_sun = new ImageView(new Image(getClass().getResourceAsStream("../main/resources/brightsun.png")));
                    //randomize setX
                    falling_sun.setX(565-i*100);
                    falling_sun.setY(-50);
                    falling_sun.setFitWidth(60);
                    falling_sun.setFitHeight(60);
                    TranslateTransition tt = new TranslateTransition();
                    tt.setDuration(Duration.seconds(8));
                    tt.setNode(falling_sun);
                    tt.setToY(641);
                    tt.play();


                    falling_sun.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                        player.sun_collected(25);
                        tt.stop();
                        lawn_parent.getChildren().remove(falling_sun);
                        System.gc();
                    });
                    lawn_parent.getChildren().add(falling_sun);
                }
                //
                //
                // here udit will add the code to increase the sun spawning speed for 30 seconds...
                //
                //
                // removing the special power as allowed only once in the level
                special_power.setDisable(true);
                special_power.setOpacity(0);
            }
        });
    }


    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private ImageView menu;


    public boolean sun_above_cost(int x, int s) {
        if((x==0 && s<50) || (x==1 && s<100) || (x==2 && s<50) || (x==3 && s<150))
            return false;
        return true;
    }

    @FXML
    void menu_clicked(MouseEvent event) throws IOException {
        System.out.println("Menu button clicked...");

        // to blur the current anchor pane
        BoxBlur blur = new BoxBlur(5,5,5);
        root_AnchorPane.setEffect(blur);

        // add the function to stop all the threads here.......
        //
        //
        //
        //
        //
        //
        //
        //
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/in_game_menu.fxml"));
        loader.load();

        // to set lawn_window in in_game_menu_controller
        in_game_menu_controller in_game_menu = loader.getController();
        in_game_menu.setLevel(levell);
        System.out.println("level at in-game-menu = " + levell);

        in_game_menu.setLawn_window((Stage) root_AnchorPane.getScene().getWindow());

        // to create the scene and make it transparent  .... and also creating the stage
        Scene menu_scene = new Scene(loader.getRoot());
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("IN-GAME Options");

        menu_scene.setFill(Color.TRANSPARENT);

        // setting scene to window and displaying window...
        window.setScene(menu_scene);
        window.setResizable(false);

        // to hide the above cross/minimize option...
        window.initStyle(StageStyle.TRANSPARENT);
        window.showAndWait();

    }

    @FXML
    void menu_entered(MouseEvent event) {
        glow_image(menu);
    }

    @FXML
    void menu_exited(MouseEvent event) {
        unglow_image(menu);
    }

    // self-defined functions


    void setLawn_parent(Pane l, Level ll) {
        this.levell = ll;

        System.out.println("Level at setLawn-Parent = " + this.levell);
        int level = ll.level_no;
        this.lawn_parent = l;
        System.out.println(lawn_parent==null);

        // now running the initialized functions...
        display_buying_tiles(level);
        set_all_tiles();
        create_media_player(level);

        for(int i=0;i<=level;i++) {
            if(i<buying_tiles.size()) {
                lawn_parent.getChildren().add(buying_tiles.get(i));
                try {
                    lawn_parent.getChildren().add(media_view.get(i));
                }
                catch (Exception e) {}
            }
        }

        for(ImageView i: all_tiles) {
            lawn_parent.getChildren().add(i);
        }

        if(level>3) { add_shovel(); }
        if(level>4) {add_special_power();}


    }

    private void glow_image(ImageView i){
        Glow glow = new Glow();
        glow.setLevel(0.7);
        i.setEffect(glow);
    }

    private void unglow_image(ImageView i){
        Glow glow = new Glow();
        glow.setLevel(0);
        i.setEffect(glow);
    }


    private List<Plants> list_of_plants=new ArrayList<Plants>();
//    Level level;

//    public void set_level(Level lev) {
//        level=lev;
//    }

    public void plant_placed(int x, ImageView i, int tile) {
        levell.place_plant(x,i,tile);
    }

    public void set_player(Player player) {
        this.player = player;
        player.setImg(blank);
    }
// self-defined functions ends here ------

}