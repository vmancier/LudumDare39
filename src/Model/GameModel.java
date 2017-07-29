package Model;

import Application.DrawGame;
import javafx.scene.image.Image;

enum CharacterTypes {Player};

public class GameModel {

    private Map map;
    private DrawGame drawGame;


    public GameModel(DrawGame drawGame) {
        Observer observer = new Observer();
        map = new Map(observer);
        this.drawGame = drawGame;
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

    public void updateCaseClicked(Case cell) {
        cell.setImage(new Image("/resources/hole.jpg", true));
    }
}


