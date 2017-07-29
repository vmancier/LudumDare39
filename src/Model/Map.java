package Model;

import javafx.scene.image.Image;

import static Application.Entities.TILE_PER_HEIGHT;
import static Application.Entities.TILE_PER_WIDTH;

public class Map {

    Case[][] floor;

    public Map(GameModel.Observer observer) {

        Image image = new Image("/resources/base.jpg", true);
        floor = new Case[TILE_PER_HEIGHT][TILE_PER_WIDTH];
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                floor[i][j]=new Case(observer,image);
                floor[i][j].setPos_x(i);
                floor[i][j].setPos_y(j);
            }
        }
    }

    public Case getCase(int i, int j){
        return floor[i][j];
    }

}
