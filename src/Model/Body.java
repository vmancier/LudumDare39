package Model;

import Application.Entities;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Body {

    private ActionQueue actionQueue;
    private int pos_x;
    private int pos_y;
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;

    public Body(int posX, int posY, CharacterTypes characcter, GameModel.Observer observer) {
        actionQueue = new ActionQueue(this);
        pos_x = posX;
        pos_y = posY;
        Image image = new Image("/resources/robot.png", true);
        _observer = observer;

        setImage(image);
        setPos_x(pos_x);
        setPos_y(pos_y);
    }

    public void setPosition(int posX, int posY) {
        setPos_x(posX);
        setPos_y(posY);
    }

    public boolean moveUp() {
        setPos_y(getPos_y() - 1);
        return true;
    }

    public boolean moveDown() {
        setPos_y(getPos_y() + 1);
        return true;
    }

    public boolean moveRight() {
        setPos_x(getPos_x() + 1);
        return true;
    }

    public boolean moveLeft() {
        setPos_x(getPos_x() - 1);
        return true;
    }

    public void setImage(Image img) {
        _image = img;
        _imageView = new ImageView(img);
        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.bodyClicked(this);
        });
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    private void setPos_x(int pos_x) {
        this.pos_x = pos_x;
        _imageView.setX(pos_x * Entities.TILE_SIZE - 16); // pixels
    }

    private void setPos_y(int pos_y) {
        this.pos_y = pos_y;
        _imageView.setY((pos_y - 1) * Entities.TILE_SIZE); // pixels
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }
}
