package Model;

import Application.Case;
import javafx.scene.image.Image;

enum CharacterTypes {Player};

public class GameModel {

    private Map map = new Map();


    public GameModel() {
        Observer observer = new Observer();
    }

    public class Observer {
        Observer() {

        }

        public void caseClicked(Case cell) {
            updateCaseClicked(cell);
        }
    }

    public void nextStep(double elapsedTime) {

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private static void updateCaseClicked(Case cell) {
        cell.setImage(new Image("/resources/hole.jpg", true));
    }
}


