package Application;

import Model.Case;
import Model.GameModel;
import Model.Map;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static Application.Entities.*;
import static Application.Entities.TILE_SIZE;

public class DrawGame {

    Rectangle background = new Rectangle(WINDOW_WIDTH,WINDOW_HEIGHT, Color.BLACK);

    public DrawGame(){
    }

    public void update(Pane root, GameModel gameModel){
        root.getChildren().addAll(background);
        drawMap(gameModel.getMap(),root);
    }

    private static void drawMap(Map map,Pane root){
        Image image = new Image("/resources/base.jpg", true);
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                ImageView imageView = new ImageView(image);
                imageView.setX(TILE_SIZE * j);
                imageView.setY(TILE_SIZE * i);
                switch((((i*2+j)*i)%7)%4){
                    case 0:
                        imageView.setRotate(0);
                        break;
                    case 1:
                        imageView.setRotate(90);
                        break;
                    case 2:
                        imageView.setRotate(180);
                        break;
                    case 3:
                        imageView.setRotate(270);
                        break;
                }

                root.getChildren().addAll(imageView);
            }
        }
    }

    private static void drawCase(int x,int y,Case cell, Pane root){
        cell.setPosition(x,y);

        root.getChildren().add(cell.get_imageView());

    }

}