package Model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Character {

    private int x;
    private int y;
    Image image;

    public Character(int posX, int posY, CharacterTypes characcter){
        x=posX;
        y=posY;
        Image image = new Image("/resources/robot.png", true);
    }

    public void moveTo(int[][] new_position){
    }

    public void drawCharacter(Pane root){

    }

}
