package Model.Actions;

import Application.Main;
import Model.Case;
import com.sun.javafx.scene.traversal.SubSceneTraversalEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Attack implements Action {

    private int x;
    private int y;
    private String folder;
    private int sprites_number;
    private Image[] sprites;
    private ImageView imageView;

    Attack(String directory, int dir_length, int _x, int _y) {
        folder = directory;
        setX(_x);
        setY(_y);
        sprites_number = dir_length;
        sprites = new Image[sprites_number];
        for (int i = 0; i < sprites_number; i++) {
            sprites[i] = new Image("resources/Animations/" + folder + "/" + String.format("%d", i) + ".png");
        }
        imageView = new ImageView(sprites[0]);
        getImageView().setX(getX());
        getImageView().setY(getY());
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSprites_number() {
        return sprites_number;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageNumber(int i) {
        this.imageView.setImage(sprites[i % sprites_number]);
    }

}
