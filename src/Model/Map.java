package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static Application.Entities.TILE_PER_HEIGHT;
import static Application.Entities.TILE_PER_WIDTH;
import static Application.Entities.TILE_SIZE;

public class Map {

    public Map() {
    }

    public void drawMap(Pane root) {
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
}
