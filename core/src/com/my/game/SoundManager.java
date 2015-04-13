package com.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public static Sound shoot;
    public static Sound enemyDeath;
    public static Sound playerDeather;
    public static Music music;

    public static void load() {

        shoot = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
        enemyDeath = Gdx.audio.newSound(Gdx.files.internal("enemy_death.wav"));
        playerDeather = Gdx.audio.newSound(Gdx.files.internal("player_death.wav"));
//        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
    }

}
