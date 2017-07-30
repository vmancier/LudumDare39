package Model;

import Model.Actions.Action;

import java.lang.Character;
import java.util.ArrayList;

public class ActionQueue {
    Body character;
    private ArrayList<Action> Queue;


    public ActionQueue(Body character) {
        this.character = character;
    }

    public void addFirst(Action action) {
        Queue.add(0, action);
    }

    public void addLast(Action action) {
        Queue.add(action);
    }

    public void removeFirst() {
        Queue.remove(0);
    }

    public Action getFirst() {
        return Queue.get(0);
    }

    public void clearQueue() {
        Queue.clear();
    }

    public void executeNext() {
        if (!Queue.isEmpty()){
            this.getFirst().execute();
            this.removeFirst();
        }
    }

    public Body getCharacter() {
        return character;
    }
}
