package com.my.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.my.game.MainGame;
import com.my.game.SoundManager;
import com.my.game.TextureManager;

import java.util.Iterator;

public class GameObjects implements Screen{

    private Texture enemyImage = TextureManager.ENEMY;
    private Texture playerImage = TextureManager.PLAYER;
    private Array<Enemy> enemies;
    private Array<Missile> missiles;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private MainGame mainGame;
    private Player player;


    private long lastEnemySpawn;

    public GameObjects(MainGame mainGame) {
        this.mainGame = mainGame;

//        SoundManager.music.setLooping(true);
//        SoundManager.music.play();

    }

    @Override
    public void show() {

        this.camera = mainGame.camera;
        this.batch = mainGame.batch;

        //Setup of the Player and the Enemies
        TextureManager.load();
        enemyImage = TextureManager.ENEMY;
        playerImage = TextureManager.PLAYER;

        missiles = new Array<Missile>();
        player = new Player(new Vector2(240, 15), new Vector2(0, 0), camera, this);

        enemies = new Array<Enemy>();

        clearEnemies();
        spawnEnemies();
    }

    @Override
    public void render(float delta) {

        //Draws Player and Enemies
        for(Entity enemy : enemies) {
            batch.draw(enemyImage, enemy.pos.x, enemy.pos.y);
        }
        batch.draw(playerImage, player.pos.x, player.pos.y);
        player.update();

        if(missiles.size > 0) {
            for(Missile m : missiles) {
                batch.draw(m.texture, m.pos.x, m.pos.y);
                m.pos.y += 4;
            }
        }

        // check if we need to create a new enemy
        if(TimeUtils.millis() - lastEnemySpawn > 1000) spawnEnemies();
        removeObjects();
        checkCollisions();
    }

    public void checkCollisions() {

        for(Missile m : missiles) {
            for (Enemy e : enemies){
                if(m.getBounds().overlaps(e.getBounds())) {
                    missiles.removeValue(m, false);
                    enemies.removeValue(e, false);
                    SoundManager.enemyDeath.play();
                }
            }
        }
    }

    public void removeObjects() {

        Iterator<Enemy> iter = enemies.iterator();
        while(iter.hasNext()) {
            Enemy enemy = iter.next();
            enemy.pos.y -= 200 * Gdx.graphics.getDeltaTime();
            if(enemy.pos.y + 64 < 0) {
                float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.ENEMY.getWidth());
                enemy.pos.set(x, MainGame.HEIGHT);
            };
            if(enemy.getBounds().overlaps(player.getBounds())) {
                SoundManager.playerDeather.play();
                mainGame.getScreen().hide();
                mainGame.setScreen(mainGame.gameOverScreen);
            }
            for(Missile m : missiles) {
                if(m.checkEnd()) {
                    missiles.removeValue(m, false);
                }
            }
        }
    }

    public void clearEnemies() {

        Iterator<Enemy> iter = enemies.iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }

    public void spawnEnemies() {
        Enemy enemy = new Enemy(new Vector2(MathUtils.random(0, 480 - 64), 800), new Vector2(0, 0));
        enemies.add(enemy);
        lastEnemySpawn = TimeUtils.millis();
    }

    public void addMissiles(Missile missile) {
        missiles.add(missile);
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
