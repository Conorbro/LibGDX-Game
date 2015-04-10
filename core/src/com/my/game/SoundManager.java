package com.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public static Sound shoot = Gdx.audio.newSound(Gdx.files.internal("pew.mp3"));
    public static Sound enemyDeath = Gdx.audio.newSound(Gdx.files.internal("SFX_Explosion_07.wav"));
    public static Sound playerDeather = Gdx.audio.newSound(Gdx.files.internal("SFX_Explosion_19.wav"));
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

}
