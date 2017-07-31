package Application;

import Model.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import static Application.Entities.*;
import static Application.Entities.TILE_SIZE;

public class DrawGame {

    Rectangle background = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
    static Image img_menu = new Image("resources/menutest.jpg", true);

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
        Rectangle menu = new Rectangle(TILE_SIZE * TILE_PER_WIDTH, 0, MENU_WIDTH, WINDOW_HEIGHT-ENERGY_BAR_HEIGHT);
        menu.setFill(Color.MIDNIGHTBLUE);
//        ImageView menu = new ImageView(img_menu);
        root.getChildren().add(menu);

        drawEnergyBar(root);

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
    }

    private static void drawBody(Body body, Pane root) {
        root.getChildren().add(body.get_imageView());
    }

    public static void updateForeground(Pane root) {
        root.getChildren().remove(Main.get_model().get_player().get_imageView());
        root.getChildren().add(Main.get_model().get_player().get_imageView());
    }

    public static void drawEnergyBar(Pane root){
        Rectangle energy_bar = new Rectangle(0, TILE_SIZE * TILE_PER_HEIGHT, WINDOW_WIDTH, ENERGY_BAR_HEIGHT);
        energy_bar.setFill(Color.GOLD);
        root.getChildren().add(energy_bar);

        int energy = Main.get_model().get_player().getEnergy();
        float energyPercentage =  (float)energy/Entities.ENERGY_MAX;
        float hide_pos = energyPercentage*WINDOW_WIDTH;

        Rectangle hide_bar = new Rectangle(hide_pos, TILE_SIZE * TILE_PER_HEIGHT, WINDOW_WIDTH, ENERGY_BAR_HEIGHT);
        hide_bar.setFill(Color.GRAY);
        root.getChildren().add(hide_bar);


    }

}