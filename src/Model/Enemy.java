package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Enemy extends Body {

    private ArrayList<Tool> inventaire = new ArrayList<Tool>();
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Enemy (int posX, int posY, int health,  CharacterTypes character, GameModel.Observer observer) {
        super(posX, posY, health, character, observer);
    }

    public boolean moveUp() {
        playSound("../resources/Sounds/sfx_sound_neutral6.wav",0.25);
        return super.moveUp();
    }

    public boolean moveDown() {
        playSound("../resources/Sounds/sfx_sound_neutral6.wav",0.25);
        return super.moveDown();
    }

    public boolean moveRight() {
        playSound("../resources/Sounds/sfx_sound_neutral6.wav",0.25);
        return super.moveRight();
    }

    public boolean moveLeft() {
        playSound("../resources/Sounds/sfx_sound_neutral6.wav",0.25);
        return super.moveLeft();
    }
}
