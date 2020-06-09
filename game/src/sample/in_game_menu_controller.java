package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class in_game_menu_controller {

    @FXML
    private ImageView back;

    @FXML
    private ImageView restart;

    @FXML
    private ImageView save;

    @FXML
    private ImageView exit;

    public Stage lawn_window;

    Level level;

    public void setLevel(Level level) {
        this.level = level;
        System.out.println("level at in-game-menu(pause) = " + level);
    }

    @FXML
    void back_clicked(MouseEvent event) {

        // un-blur the lawn window
        BoxBlur blur = new BoxBlur(5,5,5);
        lawn_window.getScene().getRoot().setEffect(null);

        // following line is used to get the stage information...
        Stage window = (Stage)back.getScene().getWindow();
        window.close();
    }

    @FXML
    void back_entered(MouseEvent event) {
        glow_image(back);
    }

    @FXML
    void back_exited(MouseEvent event) {
        unglow_image(back);
    }

    @FXML
    void exit_clicked(MouseEvent event) {
        System.out.println("exit-to-main-menu button clicked...");

        // following line is used to remove the in-game-menu...
        back_clicked(event);

        Scene lawn_scene = null;
        try {
            Parent lawn_parent = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
            lawn_scene = new Scene(lawn_parent);
        }
        catch (Exception e) {System.out.println("error in loading_screen_controller.java");}

        // changing scene of lawn-window and set it to main-menu scene...
        lawn_window.setScene(lawn_scene);


    }

    @FXML
    void exit_entered(MouseEvent event) {
        glow_image(exit);
    }

    @FXML
    void exit_exited(MouseEvent event) {
        unglow_image(exit);
    }

    @FXML
    void restart_clicked(MouseEvent event) {
        System.out.println("restart-level button clicked...");

        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/lawn.fxml"));
            loader.load();

            back_clicked(event);

            Pane lawn_parent = loader.load(getClass().getResource("/fxml/lawn.fxml"));
            Scene lawn_scene = new Scene(lawn_parent);
            Player player=new Player();
            Level l;
            if(level.level_no==1) {l=new Level1(player,lawn_parent,0);}
            else if(level.level_no==2) {l=new Level2(player,lawn_parent,0);}
            else if(level.level_no==3) {l=new Level3(player,lawn_parent,0);}
            else if(level.level_no==4) {l=new Level4(player,lawn_parent,0);}
            else  {l=new Level5(player,lawn_parent,0);}

            player.set_level(l);

            lawn_controller lc = loader.getController();
            lc.setLawn_parent(lawn_parent, l);
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
            lawn_window.setScene(lawn_scene);
        }
        catch (Exception e) {
            System.out.println("problem in restarting level...");
            e.printStackTrace();
        }

    }

    @FXML
    void restart_entered(MouseEvent event) {
        glow_image(restart);
    }

    @FXML
    void restart_exited(MouseEvent event) {
        unglow_image(restart);
    }

    @FXML
    void save_clicked(MouseEvent event) throws IOException {
        System.out.println("Save game button clicked...");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/save_game.fxml"));
        loader.load();

        // to set lawn_window in in_game_menu_controller
        save_game_controller sgc=loader.getController();
        sgc.setLevel(level);
        save_game_controller save_game_menu = loader.getController();
        save_game_menu.setPause_menu_window((Stage) exit.getScene().getWindow(), lawn_window);

        // to create the scene and  creating the stage
        Scene save_game_scene = new Scene(loader.getRoot());
        Stage window = new Stage();
        window.setTitle("SAVE-GAME Options");

        // setting scene to window and displaying window...
        window.setScene(save_game_scene);
        window.show();
        window.setResizable(false);

    }

    @FXML
    void save_entered(MouseEvent event) {
        glow_image(save);
    }

    @FXML
    void save_exited(MouseEvent event) {
        unglow_image(save);
    }

    // self-defined functions

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

    void setLawn_window(Stage l) {
        lawn_window = l;
    }
    // self-defined functions ends here ------
}











