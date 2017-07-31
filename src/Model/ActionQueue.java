package Model;

import Model.Actions.Action;

import java.lang.Character;
import java.util.ArrayList;

public class ActionQueue {
    Body body;
    private ArrayList<Action> Queue;

    public ActionQueue(Body character) {
        Queue=new ArrayList<Action>();
        this.body = character;
    }

    public void addFirst(Action action) {
        Queue.add(0, action);
    }

    public void addLast(Action action) {
        Queue.add(action);
    }

    public void removeFirst() { Queue.remove(0); }

    public Action getFirst() {
        return Queue.get(0);
    }

    public Action getEnd() {
        return Queue.get(Queue.size()-1);
        //@TODO attention si la Queue est vide !
    }

    public void clearQueue() {
        while (Queue.size()!=0){
            this.endFirst();
        }
        Queue.clear();
    }

    public void endFirst(){
        this.getFirst().end();
        this.removeFirst();
    }

    public void executeNext() {
        if (!Queue.isEmpty()) {
            Action action = this.getFirst();
            this.removeFirst();
            action.execute();
        } else {
            body.stand();
        }
    }

    public Body getBody() {
        return body;
    }
}
