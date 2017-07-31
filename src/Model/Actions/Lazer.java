package Model.Actions;

import Application.Entities;
import Model.Enemy;
import Model.Player;

public class Lazer extends Attack{

    public Lazer(Player player, Enemy ennemy){
        super("Lazer", 1, player.getPos_x() * Entities.TILE_SIZE, player.getPos_y() * Entities.TILE_SIZE);

    }

    @Override
    public void execute() {

    }

    @Override
    public void end() {

    }
}
