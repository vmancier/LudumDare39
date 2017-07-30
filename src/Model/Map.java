package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Application.Entities.TILE_PER_HEIGHT;
import static Application.Entities.TILE_PER_WIDTH;

public class Map {

    Case[][] floor;

    public Map(GameModel.Observer observer) {

        Image image = new Image("/resources/base.jpg", true);
        Image img_surbrillance = new Image("resources/surbrillance.png", true);
        floor = new Case[TILE_PER_WIDTH][TILE_PER_HEIGHT];
        for (int i = 0; i < TILE_PER_WIDTH; i++) {
            for (int j = 0; j < TILE_PER_HEIGHT; j++) {
                floor[i][j]=new Case(observer,image);
                floor[i][j].set_imgSurbrillance(new ImageView(img_surbrillance));
                floor[i][j].setPos_x(i);
                floor[i][j].setPos_y(j);
            }
        }
    }

    public Case getCase(int i, int j) {
        return floor[i][j];
    }

}
