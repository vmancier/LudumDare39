package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Enemy extends Body {

    private ArrayList<Tool> inventaire = new ArrayList<Tool>();
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Enemy (int posX, int posY, CharacterTypes character, GameModel.Observer observer) {
        super(posX, posY, character, observer);
    }
}
