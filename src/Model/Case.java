package Model;

import Application.DrawGame;
import Application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static Application.Entities.TILE_PER_HEIGHT;
import static Application.Entities.TILE_PER_WIDTH;
import static Application.Entities.TILE_SIZE;

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
    private ImageView _imgSurbrillance;

    public Case(GameModel.Observer observer, Image img) {
        _observer = observer;
        _image = img;
        _imageView = new ImageView(_image);

        _imageView.setOnMouseClicked((MouseEvent e) -> {
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

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
        this._imageView.setX(pos_x * TILE_SIZE);
        this._imgSurbrillance.setX(pos_x * TILE_SIZE);
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
        this._imageView.setY(pos_y * TILE_SIZE);
        this._imgSurbrillance.setY(pos_y * TILE_SIZE);
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
        if (_surbrillance==1){
            Main.getRoot().getChildren().add(_imgSurbrillance);
        }
    }

    public void remove_surbrillance() {
        this._surbrillance -=1;
        if (this._surbrillance<=0){
            this._surbrillance =0;
            Main.getRoot().getChildren().remove(_imgSurbrillance);
            DrawGame.updateForeground(Main.getRoot());
        }
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void set_imgSurbrillance(ImageView _imgSurbrillance) {
        this._imgSurbrillance = _imgSurbrillance;
    }

    public ImageView get_imgSurbrillance() {
        return _imgSurbrillance;
    }

    public int get_surbrillance() {
        return _surbrillance;
    }
}




