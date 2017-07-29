package Application;

import Model.Case;
import Model.GameModel;
import Model.Map;
import Model.Player;
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
    Random rnd = new Random(2);
    public DrawGame() {

    }

    public void update(Pane root, GameModel gameModel) {
        root.getChildren().addAll(background);
        /*
        if(rnd.nextInt()%2==0){
            drawMap(gameModel.getMap(), root);
        }
        */
        drawMap(gameModel.getMap(), root);
    }

    private static void drawMap(Map map, Pane root) {
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                map.getCase(i, j).setPosition(i * TILE_SIZE, j * TILE_SIZE);
                root.getChildren().add(map.getCase(i, j).get_imageView());
            }
        }
        //System.out.println("test");
    }

    private static void drawCase(int pos_x, int pos_y, Case cell, Pane root) {
        cell.setPosition(pos_x, pos_y);

        root.getChildren().add(cell.get_imageView());

    }

    private static void drawPlayer(int x, int y, Player player, Pane root) {

    }

}