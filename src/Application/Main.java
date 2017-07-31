package Application;


import Model.Case;
import Model.GameModel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;


public class Main extends Application {
    private static GameModel _model;
    private static DrawGame _drawGame;
    private static long _time;
    private static Pane root = new Pane();
    private double last = 0;
    private static Scene scene;

    public static void main(String args[]) {
        _drawGame = new DrawGame();
        _model = new GameModel();
        _time = System.currentTimeMillis();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scene=new Scene(root, Entities.WINDOW_WIDTH, Entities.WINDOW_HEIGHT);
        primaryStage.setTitle(Entities.GAME_NAME);
        drawMenu(primaryStage, root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        _model.setKeyListener(scene);
    }

    public void drawMenu(Stage primaryStage, Pane root) {
        Button play = new Button();
        play.setTranslateX(0);
        play.setTranslateY(Entities.WINDOW_HEIGHT * 0.2);
        play.setPrefSize(Entities.WINDOW_WIDTH, 60);
        play.setText("Play");
        play.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                root.getChildren().clear();
                _drawGame.update(_model);
                final long startNanoTime = System.nanoTime();
                new AnimationTimer() {
                    public void handle(long currentNanoTime) {
                        double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                        if (t - last > 0.3) {
                            last = t;
                            _model.run();
                        }
                    }
                }.start();
            }

        });

        Button settings = new Button();
        settings.setTranslateX(0);
        settings.setTranslateY(Entities.WINDOW_HEIGHT * 0.4);
        settings.setPrefSize(Entities.WINDOW_WIDTH, 60);
        settings.setText("Settings");
        settings.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String musicFile = "src/resources/Sounds/Spinning.mp3";

                Media sound = new Media(Paths.get(musicFile).toUri().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();


                //drawSettings(primaryStage, root);
            }

        });

        Button quit = new Button();
        quit.setTranslateX(0);
        quit.setTranslateY(Entities.WINDOW_HEIGHT * 0.6);
        quit.setPrefSize(Entities.WINDOW_WIDTH, 60);
        quit.setText("Quit");
        quit.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.exit(0);//Quitter le jeu
            }

        });

        root.getChildren().add(play);
        root.getChildren().add(settings);
        root.getChildren().add(quit);
    }

    public void drawLose(Stage primaryStage, Pane root) {

    }

    public void drawSettings(Stage primaryStage, Pane root) {

    }

    public void drawSubMenu(Stage primaryStage, Pane root) {

    }

    public static Pane getRoot() {
        return root;
    }

    public static void setRoot(Pane root) {
        Main.root = root;
    }

    public static DrawGame get_drawGame() {
        return _drawGame;
    }

    public static GameModel get_model() {
        return _model;
    }

    public static Scene getScene() {
        return scene;
    }
}
