package com.my.entity;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.my.game.MainGame;
import com.my.game.TextureManager;

public class PowerUp extends Entity {

    PowerUp(Vector2 pos, Vector2 direction) {
        super(TextureManager.POWER_UP, pos, direction);
    }

    @Override
    public void update() {
        pos.add(direction);

        //Rest PowerUp once it has moved off the screen
        if (!isOnScreen()) {
            float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.POWER_UP.getWidth());
            pos.set(x, MainGame.HEIGHT);        }
    }

    public boolean isOnScreen() {
        if(pos.y <= -TextureManager.POWER_UP.getHeight()) {
            return false;
        }
        else return true;
    }

}
