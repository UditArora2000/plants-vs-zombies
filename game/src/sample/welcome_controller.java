package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

public class welcome_controller {

    // self-defined functions

    private void glow_image(ImageView i){
        Glow glow = new Glow();
        glow.setLevel(1.0);
        i.setEffect(glow);
    }

    private void unglow_image(ImageView i){
        Glow glow = new Glow();
        glow.setLevel(0.3);
        i.setEffect(glow);
    }

    // self-defined functions ends here ------

    @FXML
    private ImageView new_game_image;

    @FXML
    private ImageView choose_level_image;

    @FXML
    private ImageView load_game_image;

    @FXML
    private ImageView exit_image;

    @FXML
    void clicked_exit_game(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void clicked_load_game(MouseEvent event) throws IOException {
        Pane level_parent = FXMLLoader.load(getClass().getResource("/fxml/load_game_menu.fxml"));
        Scene level_scene=new Scene(level_parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(level_scene);
        window.show();

    }

    @FXML
    void clicked_choose_level(MouseEvent event) throws IOException {
        Pane level_parent = FXMLLoader.load(getClass().getResource("/fxml/choose_level.fxml"));
        Scene level_scene=new Scene(level_parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(level_scene);
        window.show();
    }

    /**
     * will change scene when new game button is being clicked...
     * i.e when this function will run, scene will be changed.
     * **/
    @FXML
    void clicked_new_game(MouseEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/lawn.fxml"));
        loader.load();

        Pane lawn_parent = loader.load(getClass().getResource("/fxml/lawn.fxml"));
        Scene lawn_scene = new Scene(lawn_parent);
        Player player=new Player();
        Level l=new Level1(player,lawn_parent,0);

        player.set_level(l);

        lawn_controller lc = loader.getController();
        lc.setLawn_parent(lawn_parent, l);
        System.out.println("level at welcome-controller - " + l);
        //lc.set_level(l);
        lc.set_player(player);

        l.start_level();


        ImageView zombiehead =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/head.png")));
        zombiehead.setLayoutX(882);zombiehead.setLayoutY(-6);zombiehead.setFitHeight(57);zombiehead.setFitWidth(58);

        TranslateTransition tt3=new TranslateTransition();
        tt3.setNode(zombiehead);
        tt3.setDuration(Duration.seconds(l.getTimesum()));
        tt3.setToX(-233);
        tt3.play();

        lawn_parent.getChildren().add(zombiehead);
//        lawn_parent.getChildren().add(pea);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(lawn_scene);
        window.show();

    }

    @FXML
    void mouse_entered_exit(MouseEvent event) {
        glow_image(exit_image);
    }

    @FXML
    void mouse_entered_load_game(MouseEvent event) {
        glow_image(load_game_image);
    }

    @FXML
    void mouse_entered_new_game(MouseEvent event) { glow_image(new_game_image); }

    @FXML
    void mouse_entered_choose_level(MouseEvent event) { glow_image(choose_level_image); }

    @FXML
    void mouse_exited_exit(MouseEvent event) {
        unglow_image(exit_image);
    }

    @FXML
    void mouse_exited_load_game(MouseEvent event) {
        unglow_image(load_game_image);
    }

    @FXML
    void mouse_exited_new_game(MouseEvent event) {
        unglow_image(new_game_image);
    }

    @FXML
    void mouse_exited_choose_level(MouseEvent event) {
        unglow_image(choose_level_image);
    }
}