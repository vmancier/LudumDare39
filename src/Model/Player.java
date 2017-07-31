package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Player extends Body {

    private ArrayList<Tool> inventaire = new ArrayList<Tool>();
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;


    public Player(int posX, int posY, CharacterTypes character, GameModel.Observer observer) {
        super(posX, posY, character, observer);
    }

    public boolean moveUp() {
        playSound("../resources/Sounds/sfx_movement_footstepsloop4_fast.wav",0.5);
        return super.moveUp();
    }

    public boolean moveDown() {
        playSound("../resources/Sounds/sfx_movement_footstepsloop4_fast.wav",0.5);
        return super.moveDown();
    }

    public boolean moveRight() {
        playSound("../resources/Sounds/sfx_movement_footstepsloop4_fast.wav",0.5);
        return super.moveRight();
    }

    public boolean moveLeft() {
        playSound("../resources/Sounds/sfx_movement_footstepsloop4_fast.wav",0.5);
        return super.moveLeft();
    }

}
