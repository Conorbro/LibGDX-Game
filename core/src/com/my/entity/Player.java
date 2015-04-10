package com.my.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.my.game.MainGame;
import com.my.game.SoundManager;
import com.my.game.TextureManager;

public class Player extends Entity{

    private final OrthographicCamera camera;
    private GameObjects gameObjects;
    private long lastFire;

    //Default constructor
    public Player (Vector2 pos, Vector2 direction, OrthographicCamera camera, GameObjects gameObjects) {
        super(TextureManager.PLAYER, pos, direction);
        this.gameObjects = gameObjects;
        this.camera = camera;
    }

    @Override
    public void update() {
        pos.add(direction);

        int dir= 0;

            if (Gdx.input.getAccelerometerX() < -1) {
                dir = 1;
            }
            else if (Gdx.input.getAccelerometerX() > 1) {dir = 2;}


        if (dir == 1 && pos.x <= MainGame.WIDTH - texture.getWidth()) {
            setDirection(250, 0);
        }

        else if (dir == 2 && pos.x >= 0) {
            setDirection(-250, 0);
        }

        else {
            setDirection(0, 0);
        }

        if (Gdx.input.isTouched()) {
            if (System.currentTimeMillis() - lastFire >= 450) {
                gameObjects.addMissiles(new Missile(pos.cpy().add(25, TextureManager.PLAYER.getHeight())));
                SoundManager.shoot.play();
                lastFire = System.currentTimeMillis();
            }
        }
    }
}

