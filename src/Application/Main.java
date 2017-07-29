package Application;


import Model.GameModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;


public class Main extends Application {
    private static GameModel _model;
    private static DrawGame _drawGame;
    private static long _time;

    public static void main(String args[]) {
        _model = new GameModel();
        _drawGame = new DrawGame();
        _time = System.currentTimeMillis();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Entities.GAME_NAME);
        Pane root = new Pane();

        drawMenu(primaryStage, root);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,Entities.WINDOW_WIDTH ,Entities.WINDOW_HEIGHT ));
        primaryStage.show();
    }

    public void drawMenu(Stage primaryStage, Pane root){
        Button play = new Button();
        play.setTranslateX(200);
        play.setTranslateY(0);
        play.setText("Play");
        play.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                //root.getChildren().clear();
                double elapsedTime = (System.currentTimeMillis()-_time)/1000.0;
                _time = System.currentTimeMillis();

                _model.nextStep(elapsedTime);
                _drawGame.update(root,_model);
            }

        });

        Button settings = new Button();
        settings.setTranslateX(200);
        settings.setTranslateY(350);
        settings.setText("Settings");
        settings.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                drawSettings(primaryStage, root);
            }

        });

        Button quit = new Button();
        quit.setTranslateX(200);
        quit.setTranslateY(450);
        quit.setText("Quit");
        quit.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                System.exit(0);//Quitter le jeu
            }

        });

        root.getChildren().add(play);
        root.getChildren().add(settings);
        root.getChildren().add(quit);
    }

    public void drawLose(Stage primaryStage, Pane root){

    }

    public void drawSettings(Stage primaryStage, Pane root){

    }

    public void drawSubMenu(Stage primaryStage, Pane root){

    }
}
