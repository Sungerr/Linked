package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class AudioManager {
    private static HashMap<String, Music> music;
    private static HashMap<String, Sound> sound;
    Music menuMusic, gameMusic;
    Sound buttonPress, hitEffect;

    public AudioManager() {
        try {
            music = new HashMap<String,Music>();
            sound = new HashMap<String, Sound>();

            menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.wav"));
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.wav"));
            music.put("menu", menuMusic);
            music.put("game", gameMusic);

            buttonPress = Gdx.audio.newSound(Gdx.files.internal("button_Click.mp3"));
            hitEffect = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
            sound.put("button", buttonPress);
            sound.put("hit", hitEffect);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Music getMusic(String key) {
        return music.get(key);
    }

    public void stopAll() {
        for (java.util.Map.Entry<String, Music> set : music.entrySet()) {
            set.getValue().stop();
        }
    }

    public void playSong(String song) {
        if (!music.get(song).isPlaying()) {
            music.get(song).setVolume(0.8f);
            music.get(song).setLooping(true);
            music.get(song).play();
        }
    }

    public void playSFX(String sfx) {
        sound.get(sfx).play(0.8f);
    }

    public void playMenu() {
        playSong("menu");
    }

    public void playGame() {
        playSong("game");
    }

    public void playButton() {
        playSFX("button");
    }

    public void playHit() {
        playSFX("hit");
    }


}
