package Model;

import Model.Actions.Action;

import java.lang.Character;
import java.util.ArrayList;

public class ActionQueue {
    Body body;
    private ArrayList<Action> Queue;

    public ActionQueue(Body body) {
        this.body = body;
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
        if (!Queue.isEmpty()) {
            Action action = this.getFirst();
            this.removeFirst();
            action.execute();
        }
    }

    public Body getBody() {
        return body;
    }
}
