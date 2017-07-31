package Model;

import Application.DrawGame;
import Application.Entities;
import Application.Main;
import Model.Actions.Action;
import Model.Actions.Lazer;
import Model.Actions.MoveTo;
import Model.Actions.Surcharge;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static Application.Entities.*;

public class GameModel implements Runnable {

    private Map map;
    private ArrayList<Enemy> Enemies;
    private Player _player;
    private HashSet<KeyCode> keyPressed;
    private AudioClip _sound;
    private String _backgroundMusic;
    private int day;

    public GameModel() {
        day = 1;
        keyPressed = new HashSet<>();
        Observer observer = new Observer();
        map = new Map(observer);
        _player = new Player(10, 10, HEALTH_MAX, CharacterTypes.Player, observer);
        Enemies = new ArrayList<Enemy>();
        for (int k = 0; k < 5; k++) {
            Enemies.add(new Enemy(k, 0, ENEMY_HEALTH, CharacterTypes.Mob, observer));
        }

        _backgroundMusic = "../resources/Sounds/backgroundMusic.mp3";
        URL resource = getClass().getResource(_backgroundMusic);
        _sound = new AudioClip(resource.toString());
    }

    public void setKeyListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed.add(event.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed.remove(event.getCode());
            }
        });
    }

    public class Observer {
        Observer() {

        }

        public void caseClicked(Case cell, MouseEvent e) {
            updateCaseClicked(cell, e);
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
        for (Body e : Enemies) {
            e.getActionQueue().executeNext();
        }
        //Main.get_drawGame().update(this);
    }

    private void updateCaseClicked(Case cell, MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) {
            _player.getActionQueue().clearQueue();
        }
        if (keyPressed.contains(KeyCode.A)) {
            if (cell.is_free()) {
                _player.getActionQueue().addLast(new MoveTo(_player.getActionQueue(), cell));
                _player.getActionQueue().addLast(new Surcharge(cell));
            }
        } else {
            if (cell.is_free()) {
                cell.add_surbrillance();
                _player.getActionQueue().addLast(new MoveTo(_player.getActionQueue(), cell));
            }
        }
        Main.get_drawGame().updateForeground(Main.getRoot());
    }

    private void updatePlayerClicked(Player player) {
        System.out.println("You clicked on player");
    }

    private void updateBodyClicked(Body body) {
        //A modifier si jamais ça plante
        if (CharacterTypes.Mob.equals(body.getCharacter())) {
            _player.getActionQueue().addLast(new MoveTo(_player.getActionQueue(),map.getCase(body.getPos_x(),body.getPos_y())));
           _player.getActionQueue().addLast(new Lazer(_player,(Enemy) body));
        }

    }

    public void killEnemy(Body e){
        Enemies.remove(e);
        Main.get_drawGame().update(this);
    }

    public boolean isFree(int i, int j) {
        boolean free = true;
        if (!map.getCase(i, j).is_free()) free = false;
        if (_player.getPos_x() == i && _player.getPos_y() == j) free = false;
        for (Body e : Enemies) {
            if (e.getPos_x() == i && e.getPos_y() == j) free = false;
        }

        return free;
    }

    public LinkedList<Integer[]> neighbours(int i, int j) {
        LinkedList<Integer[]> l = new LinkedList<>();
        if (i > 0) {
            Integer[] array = {i - 1, j};
            l.add(array);
        }
        if (i < TILE_PER_WIDTH - 1) {
            Integer[] array = {i + 1, j};
            l.add(array);
        }
        if (j > 0) {
            Integer[] array = {i, j - 1};
            l.add(array);
        }
        if (j < TILE_PER_HEIGHT - 1) {
            Integer[] array = {i, j + 1};
            l.add(array);
        }

        return l;
    }

    public int getDay() {
        return day;
    }

    public Player get_player() {
        return _player;
    }

    public ArrayList<Enemy> getEnemies() {
        return Enemies;
    }

    public Map getMap() {
        return map;
    }

    public AudioClip getSound() {
        return _sound;
    }

}