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
    static Image img_surbrillance = new Image("resources/surbrillance.png", true);
    static ImageView surbrillance;

    public DrawGame() {

    }

    public void update(GameModel gameModel) {
        Pane pane=new Pane();
        pane.getChildren().add(background);
        drawMap(gameModel.getMap(), pane);
        drawBody(gameModel.get_player(), pane);
        Main.getRoot().getChildren().clear();
        Main.getRoot().getChildren().add(pane);
    }

    private static void drawMap(Map map, Pane root) {
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                map.getCase(i, j).setPosition(i * TILE_SIZE, j * TILE_SIZE);
                root.getChildren().add(map.getCase(i, j).get_imageView());
                if (map.getCase(i, j).is_surbrillance()){
                    surbrillance = new ImageView(img_surbrillance);
                    surbrillance.setX(i * TILE_SIZE);
                    surbrillance.setY(j * TILE_SIZE);
                    root.getChildren().add(surbrillance);
                }
            }
        }
    }

    private static void drawCase(int pos_x, int pos_y, Case cell, Pane root) {

        cell.setPosition(pos_x, pos_y);
        root.getChildren().add(cell.get_imageView());

    }

    private static void drawBody(Body body, Pane root) {

        root.getChildren().add(body.get_imageView());

    }

}