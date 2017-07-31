package Model.Actions;

import Application.Entities;
import Application.Main;
import Model.ActionQueue;
import Model.Animate;
import Model.Body;
import Model.Case;

public class Surcharge extends Attack {

    private Animate animate;
    private Case _cell;

    public Surcharge(Case cell) {
        super("Surcharge", 4,
                (cell.getPos_x() - 3) * Entities.TILE_SIZE, (cell.getPos_y() - 3) * Entities.TILE_SIZE);
        _cell = cell;
        _cell.add_target();
        animate = new Animate(this);
    }

    @Override
    public void execute() {
        animate.start();
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

}
