package Model;

import Application.Main;
import javafx.scene.image.Image;

enum CharacterTypes {Player};

public class GameModel {

    private Map map;

    public GameModel() {
        Observer observer = new Observer();
        map=new Map(observer);
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

    private void updateCaseClicked(Case cell) {
        System.out.println("UpdateCase Ok");
        cell.set_surbrillance(true);
        Main.get_drawGame().update(this);
    }
}