package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Saver implements Serializable {
    int no_of_suns;
    int[] rows_having_lawn_mowers;
    List<Integer> tile_no_of_plants;
    List<Double> plantsx,plantsy;
    List<Integer> type_of_plants;
    int level_no;
    int no_of_zombies_killed;

    Saver(Level l) {
        type_of_plants=new ArrayList<Integer>();
        tile_no_of_plants=new ArrayList<Integer>();
        plantsx=new ArrayList<Double>();
        plantsy=new ArrayList<Double>();
        rows_having_lawn_mowers=new int[5];
        for(int i=0;i<5;i++)
            rows_having_lawn_mowers[i]=0;
        no_of_suns=l.getPlayer().getNo_of_suns();
        List<Lawnmower> lolm=new ArrayList<Lawnmower>(l.getList_of_lm());
        for(int i=0;i<lolm.size();i++) {
            rows_having_lawn_mowers[lolm.get(i).getRow_number()-1]=1;
        }
        List<Plants> lop=new ArrayList<Plants>(l.getList_of_plants());
        for(int i=0;i<lop.size();i++) {
            plantsx.add(lop.get(i).getImg().getLayoutX());
            plantsy.add(lop.get(i).getImg().getLayoutY());
            tile_no_of_plants.add(lop.get(i).getTile_no());
            type_of_plants.add(lop.get(i).getType_of_plant());
        }
        level_no=l.getLevel_no();
        no_of_zombies_killed=l.getNum_of_zombies_killed();
    }

    public void serialize(String path) {
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            if(out!=null)
                try {
                    out.close();
                } catch (IOException e) {}
        }
    }

    public Level deserialize(String path, FXMLLoader loader, Player player, Pane lawn_parent, lawn_controller lc) throws IOException{

        //Pane lawn_parent = loader.load(getClass().getResource("/fxml/lawn.fxml"));

        Level level;
        System.out.println("printing level from saver - " + level_no);
        if(level_no==1) {
            level=new Level1(player,lawn_parent,1);
        }
        else if(level_no==2) {
            level=new Level2(player,lawn_parent,1);
        }
        else if(level_no==3) {
            level=new Level3(player,lawn_parent,1);
        }
        else if(level_no==4) {
            level=new Level4(player,lawn_parent,1);
        }
        else {
            level=new Level5(player,lawn_parent,1);
        }

        lc = loader.getController();
        lc.setLawn_parent(lawn_parent, level);
        lc.set_player(player);


        level.setLevel_complete(false);

        List<Sunflower> list_of_sunflowers=new ArrayList<Sunflower>();
        List<PeaShooter> list_of_shooters=new ArrayList<PeaShooter>();

        List<Plants> list_of_plants=new ArrayList<Plants>();
        for(int i=0;i<type_of_plants.size();i++) {
            Plants p=null;
            ImageView img=null;
            if(type_of_plants.get(i)==0) {
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setImage(new Image(getClass().getResourceAsStream("../main/resources/sun_flower.gif")));
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setOpacity(1);
                p = new Sunflower(lc.getAll_tiles().get(tile_no_of_plants.get(i)-1),level);
                list_of_sunflowers.add((Sunflower) p);
                list_of_plants.add(p);
            }
            else if(type_of_plants.get(i)==1) {
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setImage(new Image(getClass().getResourceAsStream("../main/resources/pea_shooter.gif")));
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setOpacity(1);
                p = new PeaShooter(lawn_parent,lc.getAll_tiles().get(tile_no_of_plants.get(i)-1),tile_no_of_plants.get(i),level);
                list_of_shooters.add((PeaShooter)p);
                list_of_plants.add(p);
            }
            else if(type_of_plants.get(i)==2) {
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setImage(new Image(getClass().getResourceAsStream("../main/resources/walnut_full_life.gif")));
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setOpacity(1);
                p = new Wallnut(lc.getAll_tiles().get(tile_no_of_plants.get(i)-1),level);
                list_of_plants.add(p);
            }
            else if(type_of_plants.get(i)==3) {
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setImage(new Image(getClass().getResourceAsStream("../main/resources/CHERRYBOMB.png")));
                lc.getAll_tiles().get(tile_no_of_plants.get(i)-1).setOpacity(1);
                p = new CherryBomb(new ArrayList<Zombies>(),lc.getAll_tiles().get(tile_no_of_plants.get(i)-1),tile_no_of_plants.get(i),player,level);
                CherryBomb cb=(CherryBomb)p;
                cb.explode();
            }
            p.setTile(tile_no_of_plants.get(i));
            p.setPlayer(player);
        }

        level.setList_of_plants(list_of_plants);
        level.setList_of_shooters(list_of_shooters);
        level.setList_of_sunflowers(list_of_sunflowers);
        level.setList_of_peas(new ArrayList<ImageView>());
        level.setZombies_on_screen(new ArrayList<Zombies>());
        level.setNo_of_suns(new Text(210,42,Integer.toString(no_of_suns)));
        level.setStopped_zombies(new HashMap<Zombies,Plants>());

        List<Lawnmower> list_of_lm=new ArrayList<Lawnmower>();
        for(int i=0;i<5;i++) {
            if(rows_having_lawn_mowers[i]==1 ) {
                if(i==0)
                    list_of_lm.add(new Lawnmower(102,475,lawn_parent,1));
                else if(i==1)
                    list_of_lm.add(new Lawnmower(103,370,lawn_parent,2));
                else if(i==2)
                    list_of_lm.add(new Lawnmower(106,265,lawn_parent,3));
                else if(i==3)
                    list_of_lm.add(new Lawnmower(105,158,lawn_parent,4));
                else if(i==4)
                    list_of_lm.add(new Lawnmower(107,57,lawn_parent,5));
            }
        }
        level.setList_of_lm(list_of_lm);

        level.setRow1(new ArrayList<Zombies>());
        level.setRow2(new ArrayList<Zombies>());
        level.setRow3(new ArrayList<Zombies>());
        level.setRow4(new ArrayList<Zombies>());
        level.setRow5(new ArrayList<Zombies>());



        level.setLevel_failed(false);
        level.setNum_of_zombies_killed(no_of_zombies_killed);
        level.setLevel_no(level_no);

        //Scene lawn_scene = new Scene(lawn_parent);
        player.set_level(level);
        player.sun_collected(no_of_suns-50);

        System.out.println("level at welcome-controller - " + level);
        //lc.set_level(l);
        lc.set_player(player);
        return level;
    }
}
