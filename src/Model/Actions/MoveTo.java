package Model.Actions;

import Model.ActionQueue;
import Model.Case;

import java.util.Queue;

public class MoveTo implements Action {
    ActionQueue queue;
    Case target;
    Character subject;

    @Override
    public void execute() {
//        @TODO Calculer le chemin, se split en plein de mouvements
    }
}
