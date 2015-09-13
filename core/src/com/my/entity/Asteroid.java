package com.my.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.my.game.MainGame;
import com.my.game.TextureManager;

public class Asteroid extends Entity {

    public Asteroid(Vector2 pos, Vector2 direction) {
        super(TextureManager.ASTEROID, pos, direction);
    }

    @Override
    public void update() {
        pos.add(direction);

        if (pos.y <= -TextureManager.ASTEROID.getHeight()) {
            float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.ASTEROID.getWidth());
            pos.set(x, MainGame.HEIGHT);
        }
    }
}