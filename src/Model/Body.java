package Model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Body {

    private int pos_x;
    private int pos_y;
    Image image;

    public Body(int posX, int posY, CharacterTypes characcter){
        pos_x=posX;
        pos_y=posY;
        Image image = new Image("/resources/robot.png", true);
    }

    public void setPosition(int posX, int posY) {
        pos_x = posX;
        pos_y = posY;
    }

    public void moveUp(){}
    public void moveDown(){}
    public void moveRight(){}
    public void moveLeft(){}

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }
}
