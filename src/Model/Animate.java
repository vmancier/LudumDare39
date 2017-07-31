package Model;

import Application.Entities;
import Application.Main;
import Model.Actions.Action;
import Model.Actions.Attack;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Animate {

    private String _folder;
    private Image[] sprites;
    private Attack _action;

    public Animate(Attack action) {
        _folder = action.getFolder();
        _action = action;
    }

    public void start() {
        Main.getRoot().getChildren().add(_action.getImageView());
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
                float step = (float) Entities.ANIMATION_DURATION * (x / _action.getSprites_number());
                for (int i = 1; i < _action.getSprites_number(); i++) {
                    if (elapsed < step * i) {
                        _action.setImageNumber(i);
                        break;
                    } else {
                        _action.setImageNumber(_action.getSprites_number() - 1);
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
        _action.getImageView().setImage(null);
    }

}
