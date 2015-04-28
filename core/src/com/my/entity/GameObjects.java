package com.my.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.my.game.MainGame;
import com.my.game.SoundManager;
import com.my.game.TextureManager;

import java.util.Iterator;

public class GameObjects implements Screen{

    private Animation enemyAnimation;
    private float timePassed = 0, timeLastPowerUp = 0, powerupDuration = 0;
    private Texture playerImage;
    private Texture powerupImage;
    private Array<Enemy> enemies;
    private Array<Missile> missiles;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private MainGame mainGame;
    private Player player;
    private PowerUp powerUp;
    private BitmapFont score;
    private int points;

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
        points = 0;

        //Setup of the Player and the Enemies
        TextureManager.load();
        powerupImage = TextureManager.POWER_UP;
        playerImage = TextureManager.PLAYER;
        enemyAnimation = TextureManager.ENEMY_ANIMATION;

        score = new BitmapFont();
        createFonts();
        score.setColor(6, 229, 20, 1);
        missiles = new Array<Missile>();
        player = new Player(new Vector2(240, 15), new Vector2(0, 0), camera, this);
        enemies = new Array<Enemy>();
        powerUp = new PowerUp(new Vector2(MathUtils.random(0, 480 - 64), 800), new Vector2(0, 0));

        clearEnemies();
        spawnEnemies();
    }

    @Override
    public void render(float delta) {

        score.draw(batch, "Score = " + points, 50, 750);
        timePassed += Gdx.graphics.getDeltaTime();


        //Draws Player and Enemies
        for(Entity enemy : enemies) {
            batch.draw(enemyAnimation.getKeyFrame(timePassed, true), enemy.pos.x, enemy.pos.y);
        }
        batch.draw(playerImage, player.pos.x, player.pos.y);
        player.update();

        if(missiles.size > 0) {
            for(Missile m : missiles) {
                batch.draw(m.texture, m.pos.x, m.pos.y);
                if(powerUp.getActivatedStatus()) {
                    m.pos.y += 6;
                }
                else {
                    m.pos.y += 4;
                }
            }
        }

        // Spawn Enemies taking into account the powerup status
        if(powerUp.getActivatedStatus()) {
            if(TimeUtils.millis() - lastEnemySpawn > 200) {
                spawnEnemies();
            }
        }
        else if(TimeUtils.millis() - lastEnemySpawn > 1000) {
            spawnEnemies();
        }
        powerUp();
        removeObjects();
        checkCollisions();
    }

    public void checkCollisions() {

        for(Missile m : missiles) {
            for (Enemy e : enemies){
                if(m.getBounds().overlaps(e.getBounds())) {
                    missiles.removeValue(m, false);
                    enemies.removeValue(e, false);
                    SoundManager.ENEMY_DEATH.play();
                    points += 1;
                }
            }
            if(powerUp.getBounds().overlaps(player.getBounds())) {
                //Run Power Up
                powerUp.setActivatedStatus(true);
                SoundManager.POWER_UP_SOUND.play();
            }
            if(powerUp.getActivatedStatus()) {
                powerupDuration += Gdx.graphics.getDeltaTime();
                if(powerupDuration > 250) {
                    powerUp.setActivatedStatus(false);
                    powerupDuration = 0;
                }
            }
        }
    }

    public void removeObjects() {

        Iterator<Enemy> iter = enemies.iterator();
        while(iter.hasNext()) {
            Enemy enemy = iter.next();
            enemy.pos.y -= 200 * Gdx.graphics.getDeltaTime(); //Should move this up
            if(enemy.pos.y + 64 < 0) {
                float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.ENEMY.getWidth());
                enemy.pos.set(x, MainGame.HEIGHT);
            };
            if(enemy.getBounds().overlaps(player.getBounds())) {
                SoundManager.PLAYER_DEATH.play();
                mainGame.getScreen().hide();
                mainGame.setScreen(mainGame.gameOverScreen);
            }
            for(Missile m : missiles) {
                if(m.checkEnd()) {
                    missiles.removeValue(m, false);
                }
            }
        }
        if(powerUp.getActivatedStatus()) {
            powerUp.pos = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    public void createFonts() {
        FileHandle fontFile = Gdx.files.internal("SHLOP.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        score = generator.generateFont(parameter);
        generator.dispose();
    }

    public void powerUp() {
        //Periodically releases a random power up
        if((timeLastPowerUp > 10 && powerUp.isOnScreen() && !powerUp.getActivatedStatus())) {
            batch.draw(powerupImage, powerUp.pos.x, powerUp.pos.y);
            powerUp.pos.y -= 8;
        }
        else {
            timeLastPowerUp += 1.00f;
        }

        if(!powerUp.isOnScreen()) {
            timeLastPowerUp = 0;
        }

        powerUp.update();
    }

    public boolean getPowerUpStatus() {
        return powerUp.getActivatedStatus();
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
