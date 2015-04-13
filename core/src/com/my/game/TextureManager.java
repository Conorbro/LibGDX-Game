package com.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

    public static Texture PLAYER;
    public static Texture MISSILE;
    public static Texture ENEMY;
    public static Texture GAME_OVER;
    public static Texture GAME_WON;
    public static Texture ENEMY_DEAD;
    public static Texture BACKGROUND;
    public static Texture PLAY;

    public static void load() {
        PLAYER = new Texture(Gdx.files.internal("player.png"));
        MISSILE = new Texture(Gdx.files.internal("missile.png"));
        ENEMY = new Texture(Gdx.files.internal("enemy.png"));
        GAME_OVER = new Texture(Gdx.files.internal("gameover.png"));
        GAME_WON = new Texture(Gdx.files.internal("gamewon.png"));
//        ENEMY_DEAD = new Texture(Gdx.files.internal("explosion.JPG"));
//        BACKGROUND = new Texture(Gdx.files.internal("background.png"));
        PLAY = new Texture(Gdx.files.internal("play.png"));

    }

}
