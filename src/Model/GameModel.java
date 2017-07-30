package Model;

import Application.DrawGame;
import Application.Main;
import Model.Actions.MoveTo;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GameModel implements Runnable{

    private Map map;
    private ArrayList<Enemy> Enemies;
    private Player _player;

    public GameModel() {
        Observer observer = new Observer();
        map = new Map(observer);
        _player = new Player(0, 0, CharacterTypes.Player, observer);
        Enemies=new ArrayList<Enemy>();
        for (int k=0;k<5;k++){
            Enemies.add(new Enemy(k,k,CharacterTypes.Mob,observer));
        }
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

    @Override
    public void run() {
        _player.getActionQueue().executeNext();
        for (Body e:Enemies) {
            e.getActionQueue().executeNext();
//            @TODO attention à la surbrillance
        }
        //Main.get_drawGame().update(this);
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
        //Main.get_drawGame().update(this);
    }

    private void updatePlayerClicked(Player player) {
        System.out.println("You clicked on Player");

    }

    private void updateBodyClicked(Body body) {
        System.out.println("You clicked on Body");
        //A modifier si jamais ça plante
        if (CharacterTypes.Mob.equals(body.getCharacter())){
            body.getActionQueue().addLast(new MoveTo(body.getActionQueue(),
                    map.getCase(_player.getPos_x(),_player.getPos_y())));
        }
        //Main.get_drawGame().update(this);
    }

    public ArrayList<Enemy> getEnemies() {
        return Enemies;
    }
}