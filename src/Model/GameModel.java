package Model;

import Application.Main;
import javafx.scene.image.Image;

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

        public void playerClicked(Player player){
            updatePlayerClicked(player);
        }

        public void bodyClicked(Body body) {
            body.moveRight();
            updateBodyClicked(body);
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
        cell.set_free(false);
        Main.get_drawGame().update(this);
    }

    private void updatePlayerClicked(Player player) {
        System.out.println("UpdatePlayer Ok");
        Main.get_drawGame().update(this);
    }

    private void updateBodyClicked(Body body) {
        System.out.println("UpdateBody Ok");
        Main.get_drawGame().update(this);
    }

}