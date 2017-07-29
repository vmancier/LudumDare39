package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Body {

    private int pos_x;
    private int pos_y;
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Body(int posX, int posY, CharacterTypes characcter){
        pos_x=posX;
        pos_y=posY;
        Image image = new Image("/resources/robot.png", true);
    }

    public void setPosition(int posX, int posY) {
        pos_x = posX;
        pos_y = posY;
    }

    public void moveUp(){
        pos_y += 1;
    }
    public void moveDown(){
        pos_y -= 1;
    }
    public void moveRight(){
        pos_x += 1;
    }
    public void moveLeft(){
        pos_y -= 1;
    }


    public ImageView get_imageView() {
        return _imageView;
    }

    public void setImage(Image img) {
        _image = img;
        _imageView = new ImageView(img);
        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.bodyClicked(this);
        });
    }



}
