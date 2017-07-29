package Model;

import javafx.scene.image.Image;

import java.awt.event.MouseEvent;

enum Type {Floor, Hole}

public class Case {

    private int[][] _position;
    private boolean _free;
    private Type _type;
    private GameModel.Observer _observer;
    private Image image;

    public Case(GameModel.Observer observer) {
        _observer = observer;
    }

    public void mouseClicked(MouseEvent e) {
        _observer.caseClicked(this);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
