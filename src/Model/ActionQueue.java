package Model;

import java.util.ArrayList;

public class ActionQueue {
    ArrayList<Action> Queue;

    private void addFirst(Action action){
        Queue.add(0,action);
    }
    private void addLast(Action action){
        Queue.add(action)
    }
    private void removeFirst(){
        Queue.remove(0);
    }
    private Action getFirst(){
        return Queue.get(0);
    }
    private void removeAllMovable(){

    }
    private void clearQueue(){
        Queue.clear();
    }
}
