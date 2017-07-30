package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

enum Type {Floor, Hole}

public class Case {

    private int pos_x;
    private int pos_y;
    private boolean _free;
    private int _surbrillance = 0;
    private Type _type;
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Case(GameModel.Observer observer, Image img) {
        _observer = observer;
        _image = img;
        _imageView = new ImageView(_image);

        _imageView.setOnMouseClicked((MouseEvent e) -> {
            System.out.println(e.getButton());
            _observer.caseClicked(this, e);
        });
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void setPosition(int pix_x, int pix_y) {
        _imageView.setX(pix_x);
        _imageView.setY(pix_y);
    }

    public void setImage(Image img) {
        _image = img;
        _imageView = new ImageView(img);
        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.caseClicked(this, e);
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
        if (this.is_free()) {
            this.setImage(new Image("/resources/base.jpg", true));
        } else {
            this.setImage(new Image("/resources/hole.jpg", true));
        }
//        @TODO actualiser graphe
    }

    public boolean is_surbrillance() {
        return (_surbrillance > 0);
    }

    public void add_surbrillance() {
        this._surbrillance += 1;
    }

    public void remove_surbrillance() {
        this._surbrillance = Math.max(0, _surbrillance - 1);
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }
}




