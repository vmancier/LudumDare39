package Model;

import Application.DrawGame;
import Application.Main;
import Model.Actions.MoveTo;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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

        public void caseClicked(Case cell,MouseEvent e) {
            updateCaseClicked(cell,e);
        }

        public void playerClicked(Player player) {
            updatePlayerClicked(player);
        }

        public void bodyClicked(Body body) {
            updateBodyClicked(body);
        }
    }

    public void nextStep(double elapsedTime) {
        _player.getActionQueue().executeNext();
        Main.get_drawGame().update(this);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private void updateCaseClicked(Case cell,MouseEvent e) {
        if (e.getButton()!= MouseButton.PRIMARY){
            _player.getActionQueue().clearQueue();
        }
        cell.add_surbrillance();
        _player.getActionQueue().addLast(new MoveTo(_player.getActionQueue(),cell));
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