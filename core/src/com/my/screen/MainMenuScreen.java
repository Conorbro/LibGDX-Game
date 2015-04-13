package com.my.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.my.game.MainGame;
import com.my.game.TextureManager;

public class MainMenuScreen implements Screen {

    private OrthographicCamera camera;
    private Texture texture;
    private SpriteBatch sb;
    private MainGame mainGame;

    public MainMenuScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        sb = new SpriteBatch();
        camera = new OrthographicCamera();
        TextureManager.load();
    }

    @Override
    public void show() {
        camera.setToOrtho(false, 480, 800);
        texture = TextureManager.PLAY;
    }

    @Override
    public void render(float delta) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(texture, MainGame.WIDTH/2 - texture.getWidth()/2, MainGame.HEIGHT/2 - texture.getHeight()/2);
        sb.end();


        if(Gdx.input.isTouched()) {
            mainGame.getScreen().hide();
            mainGame.setScreen(mainGame.gameScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 480, 800);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        sb.dispose();
        texture.dispose();
    }
}
