package Model.Actions;

import Application.Main;
import Model.ActionQueue;
import Model.Body;
import Model.Case;

public class Surcharge implements Action{
    Case cell;

    public Surcharge(Case cell){
        this.cell=cell;
        cell.add_target();
    }
    @Override
    public void execute() {
        cell.remove_target();
        for (Body e : Main.get_model().getEnemies()) {
            if (Math.abs(e.getPos_x()-cell.getPos_x())<=3 && Math.abs(e.getPos_y()-cell.getPos_y())<=3){
                e.loseHealth(50);
            }
        }
    }

    @Override
    public void end() {
        cell.remove_target();
    }
}
