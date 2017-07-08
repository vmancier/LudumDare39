package Application;


import Model.GameModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;

enum GameState { Menu, Play, Settings, Lose };

public class Main extends Application {
    private static GameModel _model;
    private static GameState _gs;
    private static long _time;

    public static void main(String args[]) {
        _model = new GameModel();
        _gs = GameState.Menu;
        _time = System.currentTimeMillis();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Entities.GAME_NAME);
        Group root = new Group();

        //Event selon l'etat du jeu
        switch (_gs)
        {
            case Menu :
                drawMenu(primaryStage, root);
                break;
            case Play:
                //Temps écoulé depuis la dernière exécution
                double elapsedTime = (System.currentTimeMillis()-_time)/1000.0;
                _time = System.currentTimeMillis();

                _model.nextStep(elapsedTime);
                drawGame(primaryStage, root);
                break;
            case Settings:
                drawSettings(primaryStage, root);
                break;
            case Lose:
                drawLose(primaryStage, root);
                break;
            default:
                //Problems
                break;
        }

        primaryStage.setScene(new Scene(root,Entities.WINDOW_WIDTH ,Entities.WINDOW_HEIGHT ));
        primaryStage.show();
    }

    public void drawMenu(Stage primaryStage, Group root){
        //Assigne une image à un bouton
        //Image imageDecline = new Image(getClass().getResourceAsStream("not.png"));
        //button5.setGraphic(new ImageView(imageDecline));

        Button play = new Button();
        play.setTranslateX(200);
        play.setTranslateY(0);
        play.setText("Say 'Hello World'");
        play.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                System.out.println(_model.getNumber());
                _model.setNumber();
            }

        });

        Button settings = new Button();
        settings.setTranslateX(200);
        settings.setTranslateY(350);
        settings.setText("Settings");
        settings.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                System.out.println(_model.getNumber());
                _model.setNumber();
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

    public void drawGame(Stage primaryStage, Group root){

    }

    public void drawLose(Stage primaryStage, Group root){

    }

    public void drawSettings(Stage primaryStage, Group root){

    }

    public void drawSubMenu(Stage primaryStage, Group root){

    }
}
