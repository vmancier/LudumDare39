package Model;

import Application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Enemy extends Body {

    private ArrayList<Tool> inventaire = new ArrayList<Tool>();
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;
    private Image _imgTargeted;
    private ImageView _targeted;


    public Enemy (int posX, int posY, int health,  CharacterTypes character, GameModel.Observer observer) {
        super(posX, posY, health, character, observer);
        _imgTargeted=new Image("resources/CibleMonstre.png", true);
        _targeted=new ImageView(_imgTargeted);
    }

    @Override
    public void die(){
        Main.get_model().killEnemy(this);
    }


    public boolean moveUp() {
        Main.playSound(_sound,"../resources/Sounds/enemy_movement.wav",0.25);
        return super.moveUp();
    }

    public boolean moveDown() {
        Main.playSound(_sound,"../resources/Sounds/enemy_movement.wav",0.25);
        return super.moveDown();
    }

    public boolean moveRight() {
        Main.playSound(_sound,"../resources/Sounds/enemy_movement.wav",0.25);
        return super.moveRight();
    }

    public boolean moveLeft() {
        Main.playSound(_sound,"../resources/Sounds/enemy_movement.wav",0.25);
        return super.moveLeft();
    }

}
