package Model.Actions;

import Application.Entities;
import Application.Main;
import Model.Body;
import Model.Enemy;
import Model.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

import java.net.URL;

public class Lazer extends Attack {

    Body _player;
    Body _enemy;

    public Lazer(Body player, Body ennemy) {
        super("Lazer", 1, player.getPos_x() * Entities.TILE_SIZE, player.getPos_y() * Entities.TILE_SIZE);
        _player = player;
        _enemy = ennemy;
        // Init sons
        String soundFile = "../../resources/Sounds/lazer.wav";
        URL resource = getClass().getResource(soundFile);
        _sound = new AudioClip(resource.toString());
    }

    @Override
    public void execute() {
        _sound.play();
        start();
    }

    @Override
    public void end() {

    }

    public void start() {
        Main.getRoot().getChildren().add(getImageView());
        //create a timeline for moving the circle
        Timeline timeline = new Timeline();
        timeline.setCycleCount(3);

        //You can add a specific action when each frame is started.
        long starter_time = System.currentTimeMillis();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long unused) {
            }
        };

        //create a keyFrame, the keyValue is reached at time 400ms
        Duration duration = Duration.millis(Entities.ANIMATION_DURATION * 3);

        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stop();
                timer.stop();
            }
        };

        float angle = (float) Math.toDegrees(Math.atan2(_enemy.getPos_y() - _player.getPos_y(), _enemy.getPos_x() - _player.getPos_x()));

        if (angle < 0) {
            angle += 360;
        }

        getImageView().setRotate(angle);

        KeyValue keyValueX = new KeyValue(getImageView().xProperty(), _enemy.getPos_x() * Entities.TILE_SIZE);
        KeyValue keyValueY = new KeyValue(getImageView().yProperty(), _enemy.getPos_y() * Entities.TILE_SIZE);
        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();
    }

    public void stop() {
        getImageView().setImage(null);
    }

}
