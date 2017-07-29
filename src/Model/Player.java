package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Player extends Body {

    private ArrayList<Tool> inventaire = new ArrayList<Tool>();
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Player(int posX, int posY, CharacterTypes character) {
        super(posX, posY, character);
        setImage(new Image("/resources/robot.png", true));
    }

}
