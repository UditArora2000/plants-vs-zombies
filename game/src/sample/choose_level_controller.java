package sample;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class choose_level_controller implements Initializable {

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView level3;

    @FXML
    private ImageView level4;

    @FXML
    private ImageView level5;

    @FXML
    private ImageView main_menu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ImageView> all = new ArrayList<>();
        all.add(level1);all.add(level2);all.add(level3);all.add(level4);all.add(level5);all.add(main_menu);

        // setting glowing image when entered the mouse
        for(ImageView i: all) {
            i.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow = new Glow();
                    glow.setLevel(0.7);
                    i.setEffect(glow);
                }
            });

            i.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow = new Glow();
                    glow.setLevel(0);
                    i.setEffect(glow);
                }
            });
        }

        set_mouseClickedFunctions(all);
    }


    private void set_mouseClickedFunctions(ArrayList<ImageView> all) {

        for(int i=0;i<5;i++) {

            int finalI = i;
            all.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                 public void handle(MouseEvent event) {
                     // open the lawn according to the level
                    try{
                        FXMLLoader loader=new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxml/lawn.fxml"));
                        loader.load();

                        Pane lawn_parent = loader.load(getClass().getResource("/fxml/lawn.fxml"));
                        Scene lawn_scene = new Scene(lawn_parent);
                        Player player=new Player();

                        // setting level according to the i ....
                        // UDIT see if tits what you want
                        Level l;

                        if(finalI == 0) {l = new Level1(player,lawn_parent,0);}
                        else if(finalI == 1) {l = new Level2(player,lawn_parent,0);}      // chnage late when level changed
                        else if(finalI == 2) {l = new Level3(player,lawn_parent,0);}
                        else if(finalI == 3) {l = new Level4(player,lawn_parent,0);}
                        else {l = new Level5(player,lawn_parent,0);}


                        l.start_level();
                        player.set_level(l);

                        lawn_controller lc = loader.getController();
                        lc.setLawn_parent(lawn_parent, l);
                        //lc.set_level(l);
                        lc.set_player(player);



                        ImageView zombiehead =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/head.png")));
                        zombiehead.setLayoutX(882);zombiehead.setLayoutY(-6);zombiehead.setFitHeight(57);zombiehead.setFitWidth(58);

                        TranslateTransition tt3=new TranslateTransition();
                        tt3.setNode(zombiehead);
                        tt3.setDuration(Duration.seconds(l.getTimesum()));
                        tt3.setToX(-233);
                        tt3.play();

                        lawn_parent.getChildren().add(zombiehead);
                        //lawn_parent.getChildren().add(pea);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(lawn_scene);
                        window.show();
                    }
                    catch (IOException e) {
                        System.out.println("Cannot load the level due to some reason");
                        e.printStackTrace();
                    }
                }
             });


        }
    }



    @FXML
    void clicked_back_to_main_menu(MouseEvent event) throws IOException {
        System.out.println("back-to-main-menu button clicked...");

        Scene lawn_scene = null;
        try {
            Parent lawn_parent = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
            lawn_scene = new Scene(lawn_parent);
        }
        catch (Exception e) {System.out.println("error in loading_screen_controller.java");}

        // following line is used to get the stage information...
        Stage window = (Stage)level1.getScene().getWindow();

        // setting scene to window and displaying window...
        window.setScene(lawn_scene);
        window.show();

    }

}
