package com.my.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.my.game.MainGame;
import com.my.game.SoundManager;
import com.my.game.TextureManager;


public class GameOverScreen implements Screen {

    private MainGame mainGame;

    private OrthographicCamera camera;
    private SpriteBatch sb;
    public Texture spriteTexture;
    private Sprite sprite;
    private float scrollTimer;

    public GameOverScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        sb = new SpriteBatch();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

        spriteTexture = TextureManager.GAME_OVER;
        spriteTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sprite = new Sprite(spriteTexture, 0, 0, spriteTexture.getWidth(), spriteTexture.getHeight());
        sprite.setSize(spriteTexture.getWidth(), spriteTexture.getHeight());
        sprite.setPosition(MainGame.WIDTH/2 - spriteTexture.getWidth()/2, MainGame.HEIGHT/2 - spriteTexture.getHeight()/2);
        scrollTimer = 0.0f;
    }

    @Override
    public void render(float delta) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        scrollTimer+=Gdx.graphics.getDeltaTime();
        if(scrollTimer>10.0f) {
            scrollTimer = 10.0f;
        }

        sprite.setU(scrollTimer);
        sprite.setU2(scrollTimer+1);
        camera.update();

        sb.begin();
        sprite.draw(sb);
        sb.end();

        //Cool down between game over screen and new game
        if(Gdx.input.isTouched() && !SoundManager.playerDeather.isPlaying()) {
            mainGame.getScreen().hide();
            mainGame.setScreen(mainGame.gameScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 480, 800);
    }

    @Override
    public void dispose() {
        sb.dispose();
        spriteTexture.dispose();
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
}
