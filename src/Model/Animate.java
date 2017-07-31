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

}
