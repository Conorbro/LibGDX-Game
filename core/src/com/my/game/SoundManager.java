package com.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public static Sound FIRE;
    public static Sound ENEMY_DEATH;
    public static Sound POWER_UP_SOUND;
    public static Music PLAYER_DEATH;
    public static Music MUSIC;

    public static void load() {

        FIRE = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
        ENEMY_DEATH = Gdx.audio.newSound(Gdx.files.internal("enemy_death.wav"));
        POWER_UP_SOUND = Gdx.audio.newSound(Gdx.files.internal("Powerup.wav"));
        PLAYER_DEATH = Gdx.audio.newMusic(Gdx.files.internal("player_death.wav"));
//        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
    }

}
