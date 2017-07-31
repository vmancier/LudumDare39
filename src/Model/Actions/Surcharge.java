package Model.Actions;

import Application.Entities;
import Application.Main;
import Model.Body;
import Model.Case;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.net.URL;

public class Surcharge extends Attack {

    private Case _cell;

    public Surcharge(Case cell) {
        super("Surcharge", 4,
                (cell.getPos_x() - 3) * Entities.TILE_SIZE, (cell.getPos_y() - 3) * Entities.TILE_SIZE);
        _cell = cell;
        _cell.add_target();

        // Init sons
        String soundFile = "../../resources/Sounds/bolt.wav";
        URL resource = getClass().getResource(soundFile);
        _sound = new AudioClip(resource.toString());
    }

    @Override
    public void execute() {
        _sound.play();
        start();
        _cell.remove_target();
        for (Body e : Main.get_model().getEnemies()) {
            if (Math.abs(e.getPos_x() - _cell.getPos_x()) <= 3 && Math.abs(e.getPos_y() - _cell.getPos_y()) <= 3) {
                e.loseHealth(50);
            }
        }
    }

    @Override
    public void end() {
        _cell.remove_target();
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
                int x = 4;
                long now = System.currentTimeMillis();
                int elapsed = (int) (now - starter_time);
                float step = (float) Entities.ANIMATION_DURATION * (x / getSprites_number());
                for (int i = 1; i < getSprites_number(); i++) {
                    if (elapsed < step * i) {
                        setImageNumber(i);
                        break;
                    } else {
                        setImageNumber(getSprites_number() - 1);
                    }
                }
            }
        };

        //create a keyFrame, the keyValue is reached at time 400ms
        Duration duration = Duration.millis(Entities.ANIMATION_DURATION * 5);

        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stop();
                timer.stop();
            }
        };

//        KeyValue keyValueX = new KeyValue(_action.getImageView().scaleXProperty(), 1);
//        KeyValue keyValueY = new KeyValue(_action.getImageView().scaleYProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(duration, onFinished);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();
    }

    public void stop() {
        getImageView().setImage(null);
    }

}
