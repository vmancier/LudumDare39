package Model;

import Application.Main;
import Model.Actions.MoveTo;

public class GameModel {

    private Map map;

    private Player _player;

    public GameModel() {
        Observer observer = new Observer();
        map = new Map(observer);
        _player = new Player(0, 0, CharacterTypes.Player, observer);
    }

    public Player get_player() {
        return _player;
    }

    public class Observer {
        Observer() {

        }

        public void caseClicked(Case cell) {
            updateCaseClicked(cell);
        }

        public void playerClicked(Player player) {
            updatePlayerClicked(player);
        }

        public void bodyClicked(Body body) {
            body.moveRight();
            updateBodyClicked(body);
        }
    }

    public void nextStep(double elapsedTime) {
        _player.getActionQueue().executeNext();
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
        _player.getActionQueue().addLast(new MoveTo(_player.getActionQueue(),cell));
        _player.getActionQueue().executeNext();
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