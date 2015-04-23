package com.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureManager {

    public static Texture PLAYER;
    public static Texture MISSILE;
    public static Texture ENEMY;
    public static Texture GAME_OVER;
    public static Texture PLAY;
    public static Texture POWER_UP;
    public static Animation ENEMY_ANIMATION;
    public static TextureAtlas ENEMY_ATLAS;


    public static void load() {
        PLAYER = new Texture(Gdx.files.internal("player.png"));
        MISSILE = new Texture(Gdx.files.internal("missile.png"));
        ENEMY = new Texture(Gdx.files.internal("enemy.png"));
        GAME_OVER = new Texture(Gdx.files.internal("gameover.png"));
        PLAY = new Texture(Gdx.files.internal("play.png"));
        POWER_UP = new Texture(Gdx.files.internal("power_up.png"));
        ENEMY_ATLAS = new TextureAtlas(Gdx.files.internal("enemy_animation.atlas"));
        ENEMY_ANIMATION = new Animation(1/15f, ENEMY_ATLAS.getRegions());
    }

}
