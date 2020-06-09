package sample;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.*;

public class load_game_menu_controller implements Initializable {

    @FXML
    private ImageView main_menu1;

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


    // self-defined functions

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

            String path = names.get(i);
            System.out.println(path);

            File to_check = new File(path);

            if(!to_check.exists()) {
                // i.e cannot be loaded from this file as it don't exists...
                cannot_access(all.get(i));
            }
            else {
                // i.e can be loaded from this file
                can_access(all.get(i));

                // here add code to serialize it when click...
                int finalI = i;
                all.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        try {

                            Level l;
                            String path = names.get(finalI);
                           // Saver s=new Saver(new Level1(new Player(),new Pane()));
                            //s.deserialize(path);
                            //
                            //
                            //
                            //
                            // de-serialize function here
                            FXMLLoader loader=new FXMLLoader();
                            loader.setLocation(getClass().getResource("/fxml/lawn.fxml"));
                            loader.load();

                            Player player=new Player();
                            Pane lawn_parent = loader.load(getClass().getResource("/fxml/lawn.fxml"));

                            lawn_controller lc = null;



                            // l = deserialized level
                            // and path = path of file from where has to desearalize
                            Saver s=null;
                            ObjectInputStream in=null;
                            try {
                                in=new ObjectInputStream(new FileInputStream(path));
                                s=(Saver)in.readObject();
                                System.out.println("desearilizing level - " + s.level_no);
                            }
                            catch(FileNotFoundException e) {
                                System.out.println("fnofe");
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                System.out.println("ioe");
                                e.printStackTrace();
                            }
                            catch (ClassNotFoundException e) {
                                System.out.println("cnfe");
                                e.printStackTrace();
                            }
                            finally {
                                if(in!=null)
                                    try {
                                        in.close();
                                    } catch (IOException e) { e.printStackTrace(); }
                            }

                            l = s.deserialize(path, loader, player, lawn_parent, lc);
                            //
                            //
                            //
                            //
                            //
                            //



                            Scene lawn_scene = new Scene(lawn_parent);


                            // setting level according to the i ....
                            // UDIT see if tits what you want

                            l.start_level();
                            //player.set_level(l);

                            //lawn_controller lc = loader.getController();
                            //lc.setLawn_parent(lawn_parent, l);
                            //lc.set_level(l);
                            //lc.set_player(player);



//                            ImageView zombiehead =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/head.png")));
//                            zombiehead.setLayoutX(882);zombiehead.setLayoutY(-6);zombiehead.setFitHeight(57);zombiehead.setFitWidth(58);
//
//                            TranslateTransition tt3=new TranslateTransition();
//                            tt3.setNode(zombiehead);
//                            tt3.setDuration(Duration.seconds(30));
//                            tt3.setToX(-82);
//                            tt3.play();
                            System.out.println("Reached");
                            ImageView zombiehead =new ImageView(new Image(getClass().getResourceAsStream("../main/resources/head.png")));
                            zombiehead.setLayoutY(-6);zombiehead.setFitHeight(57);zombiehead.setFitWidth(58);
                            double total_time=0;
                            for(int i=0;i<l.getTime().size();i++) {
                                total_time+=l.getTime().get(i);
                            }
                            double speed=233.0/total_time;
                            double partial_time=0;
                            for(int i=0;i<l.getNum_of_zombies_killed();i++) {
                                partial_time+=l.getTime().get(i);
                            }
                            double partial_dist=speed*partial_time;

                            zombiehead.setLayoutX(882-partial_dist);

                            TranslateTransition tt3=new TranslateTransition();
                            tt3.setNode(zombiehead);
                            tt3.setDuration(Duration.seconds(total_time-partial_time+6));
                            tt3.setToX(-(233-partial_dist));
                            tt3.play();


                            lawn_parent.getChildren().add(zombiehead);
                            //lawn_parent.getChildren().add(pea);
                            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                            window.setScene(lawn_scene);
                            window.show();

                        }
                        catch (IOException e) {
                            System.out.println("cannot desearalize the required file due to some reason ");
                            e.printStackTrace();
                        }

                    }
                });

            }

        }

    }


    // helping functions ...

    private void can_access(ImageView i) {
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

    private void cannot_access(ImageView i) {
        ColorAdjust ca=new ColorAdjust();
        ca.setBrightness(-0.7);
        i.setEffect(ca);
    }
}
