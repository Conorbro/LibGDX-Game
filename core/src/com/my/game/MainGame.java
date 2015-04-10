package com.my.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.my.entity.GameObjects;
import com.my.screen.GameOverScreen;

public class MainGame extends Game implements ApplicationListener {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public GameObjects gameScreen;
    public GameOverScreen gameOverScreen;
    public Screen currentScreen;
    public Texture spriteTexture;
    public Sprite sprite;
    private float scrollTimer;
    public static int HEIGHT = 800, WIDTH = 480;

    @Override
    public void create() {

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        batch = new SpriteBatch();
        gameOverScreen = new GameOverScreen(this);
        gameScreen = new GameObjects(this);
//        spriteTexture = new Texture(Gdx.files.internal("background.png"));
//        spriteTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
//        sprite = new Sprite(spriteTexture, 0, 0, 64, 64);
//        sprite.setSize(480, 800);
//        scrollTimer = 0.0f;
        currentScreen = gameScreen;
        setScreen(currentScreen);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        scrollTimer+=Gdx.graphics.getDeltaTime();
//        if(scrollTimer>10.0f) {
//            scrollTimer = 10.0f;
//        }
//
//        sprite.setV(scrollTimer);
//        sprite.setV2(scrollTimer+1);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        screen.render(0);
//        sprite.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        screen.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}