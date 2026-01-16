package org.kd.noname;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

import java.util.Arrays;

public class Scene1KickScroll extends Scene {

    private Music fok, whatchaLookinAt;
    private Texture tuffGuy, kick;
    private SpriteBatch batch;

    public Scene1KickScroll() {
        super("scene1-kick-scroll");
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        fok = Gdx.audio.newMusic(Gdx.files.internal("noname/audio/fok.mp3"));
        whatchaLookinAt = Gdx.audio.newMusic(Gdx.files.internal("noname/audio/WhatAreYouLookingAt.mp3"));

        Arrays.asList(fok, whatchaLookinAt).forEach(music -> {
            music.setLooping(false);
            music.setVolume(1f);
        });

        tuffGuy = new Texture("noname/wb.png");
        kick = new Texture("noname/kick.png");
    }

    @Override
    public void update(float delta) {
        var frame = Gdx.graphics.getFrameId();
        if (frame == 200)
            whatchaLookinAt.play();

        else if (frame == 450)
            fok.play();
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        var frame = Gdx.graphics.getFrameId();
        var W = Gdx.graphics.getWidth();
        var H = Gdx.graphics.getHeight();

        batch.begin();
        if (200 < frame && frame < 500)
            batch.draw(tuffGuy, W *0.5f - tuffGuy.getWidth() / 2f, 0.1f*H);

        if (frame > 500)
            batch.draw(kick, W *0.51f - tuffGuy.getWidth() / 2f, 0.1f*H);

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
