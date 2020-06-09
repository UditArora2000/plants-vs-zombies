package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class save_game_controller implements Initializable {

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
    private ImageView level6;

    @FXML
    private ImageView main_menu1;

    private Stage in_game_menu_window;
    private Stage lawn_window;

    Level level;

    public void setLevel(Level level) {
        this.level = level;
        System.out.println("level at save-game = " + level);
    }

    @FXML
    void back_to_menu_entered(MouseEvent event) {
        Glow glow = new Glow();
        glow.setLevel(0.7);
        main_menu1.setEffect(glow);
    }

    @FXML
    void back_to_menu_exited(MouseEvent event) {
        Glow glow = new Glow();
        glow.setLevel(0);
        main_menu1.setEffect(glow);
    }

    @FXML
    void clicked_back_to_pause_menu(MouseEvent event) {
        System.out.println("back-to-pause-menu button clicked...");

        // following line is used to get the stage information...
        Stage window = (Stage)level1.getScene().getWindow();

        // removing the current window and coming back to pause-menu
        window.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ImageView> all = new ArrayList<>();
        all.add(level1);all.add(level2);all.add(level3);all.add(level4);all.add(level5);all.add(level6);

        // names of the saved files
        ArrayList<String> names = new ArrayList<>();
        names.add("saved_games/1.txt");
        names.add("saved_games/2.txt");
        names.add("saved_games/3.txt");
        names.add("saved_games/4.txt");
        names.add("saved_games/5.txt");
        names.add("saved_games/6.txt");

        for(int i=0;i<all.size();i++) {

            // i.e all can be used to save the game
            allow_glow_unglow(all.get(i));
            set_onClickMouse(all.get(i), names.get(i));

            String path = names.get(i);

            File to_check = new File(path);

            if(!to_check.exists()) {
                no_previously_saved_game(all.get(i));
            }
        }
    }

    // helping functions

    private void allow_glow_unglow(ImageView i) {
        i.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Glow glow = new Glow();
                glow.setLevel(0);
                i.setEffect(glow);
            }
        });

        i.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Glow glow = new Glow();
                glow.setLevel(0.7);
                i.setEffect(glow);
            }
        });
    }


    private void no_previously_saved_game(ImageView i) {
        ColorAdjust ca=new ColorAdjust();
        ca.setBrightness(-0.7);
        i.setEffect(ca);

        i.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Glow glow = new Glow();
                glow.setLevel(0);
                i.setEffect(glow);
                ColorAdjust ca=new ColorAdjust();
                ca.setBrightness(-0.7);
                i.setEffect(ca);
            }
        });
    }

    void setPause_menu_window(Stage in_game_menu, Stage lawn) {
        lawn_window = lawn;
        in_game_menu_window = in_game_menu;
    }

    private void set_onClickMouse(ImageView i, String path) {
        // this function is used to set the ImageView according to it corresponding txt file...
        i.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                System.out.println("Serializing level = " + level);

                Saver s=new Saver(level);
                s.serialize(path);
                // add code to serialize the present game to the "path" file
                //
                //
                //
                //
                //
                //
                //

                // now code to go back to main-menu window is as follows

                // closing present window
                Stage window = (Stage)level1.getScene().getWindow();
                window.close();
                // closing in-game-menu-window
                in_game_menu_window.close();
                // now changing scene of lawn window to main-menu-scene
                lawn_window.setTitle("Main Menu");
                try {
                    Parent lawn_parent = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
                    Scene main_menu_scene = new Scene(lawn_parent);
                    lawn_window.setScene(main_menu_scene);
                }
                catch (IOException e) {
                    System.out.println("Cannot load welcome fxml from save-game-option");
                }
            }
        });
    }


}
