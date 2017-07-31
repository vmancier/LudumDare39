package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static Application.Entities.TILE_PER_HEIGHT;
import static Application.Entities.TILE_PER_WIDTH;

public class Map {

    Case[][] floor;

    public Map(GameModel.Observer observer) {
        Image image = new Image("/resources/base.jpg", true);
        Image img_surbrillance = new Image("resources/surbrillance.png", true);
        Image img_cible = new Image("resources/selected.png", true);

        floor = new Case[TILE_PER_WIDTH][TILE_PER_HEIGHT];

        FileInputStream initFile = null;
        try {
            initFile = new FileInputStream("src/resources/map.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner initsc = new Scanner(initFile);
        int i=0;
        int j=0;
        while (initsc.hasNextLine()){
            for (char c : initsc.nextLine().toCharArray()){
                floor[i][j]=new Case(observer,image,img_cible);
                floor[i][j].set_imgSurbrillance(new ImageView(img_surbrillance));
                floor[i][j].setPos_x(i);
                floor[i][j].setPos_y(j);

                if (c=='1'){
                    floor[i][j].set_free(false);
                }
                j++;
            }
            j=0;
            i++;
        }

        /*floor = new Case[TILE_PER_HEIGHT][TILE_PER_WIDTH];
        for (int i = 0; i < TILE_PER_HEIGHT; i++) {
            for (int j = 0; j < TILE_PER_WIDTH; j++) {
                floor[i][j]=new Case(observer,image);
                floor[i][j].set_imgSurbrillance(new ImageView(img_surbrillance));
                floor[i][j].setPos_x(i);
                floor[i][j].setPos_y(j);
            }
        }*/
    }

    public Case getCase(int i, int j) {
        return floor[i][j];
    }

}
