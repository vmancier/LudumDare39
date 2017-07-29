package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

enum Type {Floor, Hole}

public class Case {

    private int pos_x;
    private int pos_y;
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
            set_free(false);
            System.out.println("x : " +pos_x+" y : "+ pos_y);
        });
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void setPosition(int pix_x, int pix_y){
        _imageView.setX(pix_x);
        _imageView.setY(pix_y);
    }

    public void setImage(Image img){
        _image=img;
        _imageView = new ImageView(img);
        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.caseClicked(this);
        });
    }

    public void setPos_x(int _x) {
        this.pos_x = _x;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public boolean is_free() {
        return _free;
    }

    public void set_free(boolean _free) {
        this._free = _free;
        if (_free){
            this.setImage(new Image("/resources/base.jpg", true));
        }
        else{
            this.setImage(new Image("/resources/hole.jpg", true));
        }
//        @TODO actualiser graphe
    }
}




