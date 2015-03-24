package com.my.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.my.camera.OrthoCamera;
import com.my.game.MainGame;
import com.my.game.TextureManager;

public class Player extends Entity{

    private final EntityManager entityManager;
    private final OrthoCamera camera;
    private long lastFire;

    //Default constructor
    public Player (Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera) {
        super(TextureManager.PLAYER, pos, direction);
        this.entityManager = entityManager;
        this.camera = camera;
    }

    @Override
    public void update() {
        pos.add(direction);

        int dir= 0;

        if (Gdx.input.isTouched()) {
            Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
            if (touch.x > MainGame.WIDTH/2) {
                dir = 1;
            }
            else dir = 2;
        }

        if (dir == 1) {
            setDirection(300, 0);
        }

        else if (dir == 2) {
            setDirection(-300, 0);
        }

        else {
            setDirection(0, 0);
        }

        if (Gdx.input.isTouched()) {
            if (System.currentTimeMillis() - lastFire >= 250) {
                entityManager.addEntity(new Missile(pos.cpy().add(25, TextureManager.PLAYER.getHeight())));
                lastFire = System.currentTimeMillis();
            }
        }
    }

}
