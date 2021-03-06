package Model;

import Application.Entities;
import Application.Main;
import Model.Actions.Action;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import Model.Actions.Directions;

import java.net.URL;
import java.util.Arrays;

public abstract class Body {

    private ActionQueue actionQueue;
    private int pos_x;
    private int pos_y;
    private int _health;
    private GameModel.Observer _observer;
    private Image _image;
    private ImageView _imageView;
    private CharacterTypes character;
    private Image[] _up = new Image[4];
    private Image[] _down = new Image[4];
    private Image[] _left = new Image[4];
    private Image[] _right = new Image[4];
    private int actual_sprite = 0;
    protected AudioClip _sound;


    public Body(int posX, int posY, int health, CharacterTypes character, GameModel.Observer observer) {
        actionQueue = new ActionQueue(this);
        _observer = observer;
        _health = health;
        this.character = character;

        // Chargement des sprites
        String _sprites_path = "/resources/Sprites/" + character.toString().toLowerCase() + "/";
        for (int i = 0; i < 4; i++) {
            _up[i] = new Image(_sprites_path + "up/" + String.format("%d", i) + ".png");
            _down[i] = new Image(_sprites_path + "down/" + String.format("%d", i) + ".png");
            _right[i] = new Image(_sprites_path + "right/" + String.format("%d", i) + ".png");
            _left[i] = new Image(_sprites_path + "left/" + String.format("%d", i) + ".png");
        }

        setImage(_down[0]);

        //Emplacement
        pos_x = posX;
        pos_y = posY;
        setPosition(pos_x,pos_y);

        // Init sons
        String soundFile = "../resources/Sounds/player_movement.wav";
        URL resource = getClass().getResource(soundFile);
        _sound = new AudioClip(resource.toString());
    }

    public void setPosition(int posX, int posY) {
        setPos_x(posX);
        setPos_y(posY);
    }

    public void animation(Image[] sprites, Directions direction) {
        //create a timeline for moving the circle
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        //You can add a specific action when each frame is started.
        long starter_time = System.currentTimeMillis();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long unused) {
                int x = 4;
                long now = System.currentTimeMillis();
                int elapsed = (int) (now % (Entities.ANIMATION_DURATION * x));
                int step = Entities.ANIMATION_DURATION * (x/4);
                if (elapsed < step) {
                    get_imageView().setImage(sprites[0]);
                } else if (elapsed < 2 * step) {
                    get_imageView().setImage(sprites[1]);
                } else if (elapsed < 3 * step) {
                    get_imageView().setImage(sprites[2]);
                } else {
                    get_imageView().setImage(sprites[3]);
                }
            }
        };

        //create a keyFrame, the keyValue is reached at time 400ms
        Duration duration = Duration.millis(Entities.ANIMATION_DURATION);

        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                switch (direction) {
                    case right:
                        setPos_x(getPos_x() + 1);
                        break;
                    case left:
                        setPos_x(getPos_x() - 1);
                        break;
                    case up:
                        setPos_y(getPos_y() - 1);
                        break;
                    case down:
                        setPos_y(getPos_y() + 1);
                        break;
                }
                timer.stop();
            }
        };
        KeyValue keyValue = new KeyValue(get_imageView().xProperty(),
                get_imageView().getX() + Entities.TILE_SIZE);
        switch (direction) {
            case right:
                keyValue = new KeyValue(get_imageView().xProperty(),
                        get_imageView().getX() + Entities.TILE_SIZE);
                break;
            case left:
                keyValue = new KeyValue(get_imageView().xProperty(),
                        get_imageView().getX() - Entities.TILE_SIZE);
                break;
            case up:
                keyValue = new KeyValue(get_imageView().yProperty(),
                        get_imageView().getY() - Entities.TILE_SIZE);
                break;
            case down:
                keyValue = new KeyValue(get_imageView().yProperty(),
                        get_imageView().getY() + Entities.TILE_SIZE);
                break;
        }

//        KeyValue keyValueY = new KeyValue(get_imageView().yProperty(), get_imageView().getY());
        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValue);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Body)) return false;

        Body body = (Body) o;

        if (getPos_x() != body.getPos_x()) return false;
        if (getPos_y() != body.getPos_y()) return false;
        if (_health != body._health) return false;
        if (actual_sprite != body.actual_sprite) return false;
        if (!getActionQueue().equals(body.getActionQueue())) return false;
        if (!_observer.equals(body._observer)) return false;
        if (!_image.equals(body._image)) return false;
        if (!get_imageView().equals(body.get_imageView())) return false;
        if (getCharacter() != body.getCharacter()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(_up, body._up)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(_down, body._down)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(_left, body._left)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(_right, body._right)) return false;
        return _sound != null ? _sound.equals(body._sound) : body._sound == null;
    }

    public abstract void die();

    public boolean moveUp() {
        animation(_up, Directions.up);
        return true;
    }

    public boolean moveDown() {
        animation(_down, Directions.down);
        return true;
    }

    public boolean moveRight() {
        animation(_right, Directions.right);
        return true;
    }

    public boolean moveLeft() {
        animation(_left, Directions.left);
        return true;
    }

    public void loseHealth(int damages){
        if(_health>=damages){
            _health-=damages;
        }else{
            _health=0;
        }

        if(_health==0){
            this.die();
        }
    }

    public void stand() {
        get_imageView().setImage(_down[0]);
    }

    public void setImage(Image img) {
        _image = img;
        _imageView = new ImageView(img);
        _imageView.setOnMouseClicked((MouseEvent e) -> {
            _observer.bodyClicked(this);
        });

    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void next_sprite(Image[] sprites) {
        actual_sprite = (actual_sprite + 1) % _up.length;
        get_imageView().setImage(sprites[actual_sprite]);
    }

    private void setPos_x(int pos_x) {
        this.pos_x = pos_x;
        _imageView.setX(pos_x * Entities.TILE_SIZE - 16); // pixels
    }

    private void setPos_y(int pos_y) {
        this.pos_y = pos_y;
        _imageView.setY((pos_y - 1) * Entities.TILE_SIZE); // pixels
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getHealth() {
        return _health;
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }

    public CharacterTypes getCharacter() {
        return this.character;
    }

    public AudioClip getSound() {
        return _sound;
    }

    public Case getCase() {
        return Main.get_model().getMap().getCase(pos_x, pos_y);
    }
}
