package Model;

import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Tool> inventaire = new ArrayList<Tool>();

    public Player(int posX, int posY, CharacterTypes character) {
        super(posX, posY, character);
    }

}
