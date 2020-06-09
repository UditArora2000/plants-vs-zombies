package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;



public class loading_screen_controller implements Initializable {

    @FXML
    private MediaView media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MediaPlayer media_player = new MediaPlayer(new Media(this.getClass().getResource("/loading_screen.mp4").toExternalForm()));
        media.setMediaPlayer(media_player);

        media_player.setOnEndOfMedia(() -> {
            // media finished
            Scene lawn_scene = null;
            try {
                Parent lawn_parent = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
                lawn_scene = new Scene(lawn_parent);
            }
            catch (Exception e) {System.out.println("error in loading_screen_controller.java");}

            // following line is used to get the stage information...
            Stage window = (Stage)media.getScene().getWindow();

            // setting scene to window and displaying window...
            window.setScene(lawn_scene);
            window.show();
        });

        media_player.play();


    }
}
