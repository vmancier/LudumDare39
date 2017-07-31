package Model.Actions;

import Model.ActionQueue;
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
    }

    @Override
    public void end() {
        cell.remove_target();
    }
}
