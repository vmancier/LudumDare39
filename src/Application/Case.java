package Application;

import Model.GameModel;

import java.awt.event.MouseEvent;

enum Type {Floor, Hole}

public class Case {

    private int[][] _position;
    private boolean _free;
    private Type _type;
    private GameModel.Observer _observer;

    public Case(GameModel.Observer observer) {
        _observer = observer;
    }

    public void mouseClicked(MouseEvent e) {
        _observer.caseClicked(this);
    }
}
