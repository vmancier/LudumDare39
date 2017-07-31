package Model.Actions;

import Application.Entities;
import Model.Enemy;
import Model.Player;
import javafx.scene.media.AudioClip;

import java.net.URL;

public class Lazer extends Attack{

    public Lazer(Player player, Enemy ennemy){
        super("Lazer", 1, player.getPos_x() * Entities.TILE_SIZE, player.getPos_y() * Entities.TILE_SIZE);
        // Init sons
        String soundFile = "../../resources/Sounds/lazer.wav";
        URL resource = getClass().getResource(soundFile);
        _sound = new AudioClip(resource.toString());
    }

    @Override
    public void execute() {
        _sound.play();


        
    }

    @Override
    public void end() {

    }
}
