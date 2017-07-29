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
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalTime;


public class Main extends Application {
    private static GameModel _model;
    private static DrawGame _drawGame;
    private static long _time;
    private static Pane root = new Pane();

    public static void main(String args[]) {
        _drawGame = new DrawGame();
        _model = new GameModel();
        _time = System.currentTimeMillis();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Entities.GAME_NAME);
        drawMenu(primaryStage, root);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,Entities.WINDOW_WIDTH ,Entities.WINDOW_HEIGHT ));
        primaryStage.show();
    }

    public void drawMenu(Stage primaryStage, Pane root){
        Button play = new Button();
        play.setTranslateX(0);
        play.setTranslateY(Entities.WINDOW_HEIGHT * 0.2);
        play.setPrefSize(Entities.WINDOW_WIDTH, 60);
        play.setText("Play");
        play.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                root.getChildren().clear();
                //double elapsedTime = (System.currentTimeMillis()-_time)/1000.0;
                //_time = System.currentTimeMillis();

                root.getChildren().clear();
                _drawGame.update(_model);

                final long startNanoTime = System.nanoTime();
                new AnimationTimer()
                {
                    public void handle(long currentNanoTime)
                    {

                        /*try{
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/

                        double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                        //System.out.println(t);
                        /*root.getChildren().clear();
                        _drawGame.update(root,_model);*/
                    }
                }.start();

                //_model.nextStep(elapsedTime);
                //_drawGame.update(root,_model);
            }

        });

        Button settings = new Button();
        settings.setTranslateX(0);
        settings.setTranslateY(Entities.WINDOW_HEIGHT * 0.4);
        settings.setPrefSize(Entities.WINDOW_WIDTH, 60);
        settings.setText("Settings");
        settings.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                drawSettings(primaryStage, root);
            }

        });

        Button quit = new Button();
        quit.setTranslateX(0);
        quit.setTranslateY(Entities.WINDOW_HEIGHT * 0.6);
        quit.setPrefSize(Entities.WINDOW_WIDTH, 60);
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

    public static Pane getRoot() {
        return root;
    }

    public static DrawGame get_drawGame() {
        return _drawGame;
    }
}
