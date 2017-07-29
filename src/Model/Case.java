package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static Application.Entities.TILE_SIZE;

enum Type {Floor, Hole}

public class Case {

    private int _x;
    private int _y;
    private boolean _free;
    private Type _type;
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Case(GameModel.Observer observer,Image img) {
        _observer = observer;
        _image=img;
        _imageView = new ImageView(_image);

        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.caseClicked(this);
        });
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void setPosition(int x, int y){
        _imageView.setX(x);
        _imageView.setY(y);
    }

    public void setImage(Image img){
        _imageView = new ImageView(_image);

        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.caseClicked(this);
        });
    }
}




