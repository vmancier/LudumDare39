package Application;

import Model.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import static Application.Entities.*;
import static Application.Entities.TILE_SIZE;

public class DrawGame {

    Rectangle background = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
    static Image img_menu = new Image("resources/menutest.jpg", true);
    private static AudioClip _sound = new AudioClip(DrawGame.class.getResource("../resources/Sounds/enemy_movement.wav").toString());

    public DrawGame() {

    }

    public void update(GameModel gameModel) {
        Pane pane = new Pane();
        pane.getChildren().add(background);
        drawMap(gameModel.getMap(), pane);
        drawBody(gameModel.get_player(), pane);
        for (Body e : gameModel.getEnemies()) {
            drawBody(e, pane);
        }
        Main.getRoot().getChildren().clear();
        Main.getRoot().getChildren().add(pane);
    }

    private static void drawMap(Map map, Pane root) {
        Main.get_model().getSound().play();// musique d'ambiance

        Rectangle menu = new Rectangle(TILE_SIZE * TILE_PER_WIDTH, 0, MENU_WIDTH, WINDOW_HEIGHT-BAR_HEIGHT);
        menu.setFill(Color.MIDNIGHTBLUE);
//        ImageView menu = new ImageView(img_menu);
        root.getChildren().add(menu);

        for (int i = 0; i < TILE_PER_WIDTH; i++) {
            for (int j = 0; j < TILE_PER_HEIGHT; j++) {
                root.getChildren().add(map.getCase(i, j).get_imageView());
                if (map.getCase(i, j).is_surbrillance()) {
                    root.getChildren().add(map.getCase(i, j).get_imgSurbrillance());
                }
                if(map.getCase(i, j).is_targeted()){
                    root.getChildren().add(map.getCase(i, j).get_imgTarget());
                }
            }
        }
        drawEnergyBar(root);
        drawHealthBar(root);
    }

    private static void drawBody(Body body, Pane root) {
        root.getChildren().add(body.get_imageView());
    }

    public static void updateForeground(Pane root) {
        root.getChildren().remove(Main.get_model().get_player().get_imageView());
        root.getChildren().add(Main.get_model().get_player().get_imageView());
    }

    public static void drawEnergyBar(Pane root){
        Rectangle energy_bar = new Rectangle(0, TILE_SIZE * TILE_PER_HEIGHT+BAR_HEIGHT, WINDOW_WIDTH, BAR_HEIGHT);

        int energy = Main.get_model().get_player().getEnergy();
        float energyPercentage =  (float)energy/Entities.ENERGY_MAX;
        float hide_pos = energyPercentage*WINDOW_WIDTH;

        if(energyPercentage > 0.5){
            energy_bar.setFill(Color.GOLD);
        }
        else if(energyPercentage < 0.25){
            energy_bar.setFill(Color.RED);
            Main.playSound(_sound,"../resources/Sounds/lowEnergy.wav",0.1);
        }
        else{
            energy_bar.setFill(Color.DARKORANGE);
        }

        Rectangle hide_bar = new Rectangle(hide_pos, TILE_SIZE * TILE_PER_HEIGHT+BAR_HEIGHT, WINDOW_WIDTH, BAR_HEIGHT);
        hide_bar.setFill(Color.GRAY);

        
        root.getChildren().add(energy_bar);
        root.getChildren().add(hide_bar);
    }

    public static void drawHealthBar(Pane root){
        Rectangle health_bar = new Rectangle(0, TILE_SIZE * TILE_PER_HEIGHT, WINDOW_WIDTH, BAR_HEIGHT);

        int health = Main.get_model().get_player().getHealth();
        float healthPercentage =  (float)health/Entities.HEALTH_MAX;
        float hide_pos = healthPercentage*WINDOW_WIDTH;

        if(healthPercentage > 0.5){
            health_bar.setFill(Color.DEEPSKYBLUE);
        }
        else if(healthPercentage < 0.25){
            health_bar.setFill(Color.BLUE);
            Main.playSound(_sound,"../resources/Sounds/lowHealth.wav",0.1);
        }
        else{
            health_bar.setFill(Color.INDIGO);
        }

        Rectangle hide_bar = new Rectangle(hide_pos, TILE_SIZE * TILE_PER_HEIGHT, WINDOW_WIDTH, BAR_HEIGHT);
        hide_bar.setFill(Color.GREY);

        root.getChildren().add(health_bar);
        root.getChildren().add(hide_bar);
    }

    public static AudioClip getSound() {
        return _sound;
    }
}