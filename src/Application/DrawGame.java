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
    static Image img_menu = new Image("resources/menutest.jpg",true);

    public DrawGame() {

    }

    public void update(GameModel gameModel) {
        Pane pane = new Pane();
        pane.getChildren().add(background);
        drawMap(gameModel.getMap(), pane);
        drawBody(gameModel.get_player(), pane);
        for (Body e:gameModel.getEnemies()){
            drawBody(e,pane);
        }
        Main.getRoot().getChildren().clear();
        Main.getRoot().getChildren().add(pane);
    }

    private static void drawMap(Map map, Pane root) {
//        Rectangle menu = new Rectangle(TILE_SIZE * TILE_PER_WIDTH, 0, MENU_WIDTH, WINDOW_HEIGHT);
//        menu.setFill(Color.MIDNIGHTBLUE);
        ImageView menu=new ImageView(img_menu);
        menu.setX(TILE_SIZE * TILE_PER_WIDTH);
        menu.setY(0);
        root.getChildren().add(menu);
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                root.getChildren().add(map.getCase(i, j).get_imageView());
                if (map.getCase(i, j).get_surbrillance()>0){
                    root.getChildren().add(map.getCase(i, j).get_imgSurbrillance());
                }
            }
        }
    }

    private static void drawBody(Body body, Pane root) {
        root.getChildren().add(body.get_imageView());
    }

    public static void updateForeground(Pane root){
        root.getChildren().remove(Main.get_model().get_player().get_imageView());
        root.getChildren().add(Main.get_model().get_player().get_imageView());
    }

}