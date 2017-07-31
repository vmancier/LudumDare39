package Application;

import java.awt.*;

public class Entities {

    public static final int TILE_PER_HEIGHT = 20;
    public static final int TILE_PER_WIDTH = 25;

    public static final String GAME_NAME = "CUSO4";
    public static final int TILE_SIZE = 32;
    public static final int MENU_WIDTH = 128;
    public static final int BAR_HEIGHT = 10;
    public static final int WINDOW_HEIGHT = TILE_SIZE * TILE_PER_HEIGHT + (2*BAR_HEIGHT);//default window's height
    public static final int WINDOW_WIDTH = TILE_SIZE * TILE_PER_WIDTH + MENU_WIDTH;//default window's width

    //Player
    public static final int HEALTH_MAX = 100;
    public static final int ENERGY_MAX = 1000;
    public static final int ENERGY_COST_MOVE = 5;
    public static final int ANIMATION_DURATION = 280;


    //Enemies
    public static final int ENEMY_HEALTH = 100;

}

