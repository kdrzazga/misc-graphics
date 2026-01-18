package org.kd.noname;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;
import org.kd.common.tricks.AlphabetScroll;

import java.util.Arrays;

public class Scene1KickScroll extends Scene {

    private Music fok, whatchaLookinAt;
    private Texture tuffGuy, boot;
    private Sprite kick, bigBoot;
    private SpriteBatch batch;
    private int W, H;
    private BootAlphabetScroll scroll;

    public Scene1KickScroll() {
        super("scene1-kick-scroll");
    }

    @Override
    public void create() {
        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        fok = Gdx.audio.newMusic(Gdx.files.internal("noname/audio/fok.mp3"));
        whatchaLookinAt = Gdx.audio.newMusic(Gdx.files.internal("noname/audio/WhatAreYouLookingAt.mp3"));

        Arrays.asList(fok, whatchaLookinAt).forEach(music -> {
            music.setLooping(false);
            music.setVolume(1f);
        });

        tuffGuy = new Texture("noname/wb.png");
        boot = new Texture("noname/boot.png");
        kick = new Sprite(new Texture("noname/kick.png"));
        kick.setPosition(W * 0.5f - kick.getWidth() / 2f, 0.1f * H);

        bigBoot = new Sprite(new Texture("noname/leftboot.png"));
        bigBoot.setScale(1.3f);
        bigBoot.setPosition(W / 2 - bigBoot.getWidth() / 2, H);

        String scrollText = "Welcome to this ass kicking demo     " +
                "In the beginning I would like to send greetings to Pan Areczek " +
                "of Komoda and Amiga PLUS                   " +
                "multiple industries   He continued to innovate until his death  leaving a lasting legacy " +
                " Tim Cook took over apple and continued Jobs vision creating " +
                "AppleWatch AppleTV and iCloud";
        scroll = new BootAlphabetScroll(scrollText.toLowerCase(), 0 + 480);
    }

    @Override
    public void update(float delta) {
        var frame = Gdx.graphics.getFrameId();
        if (frame == 200)
            whatchaLookinAt.play();

        else if (frame == 370)
            fok.play();
        else if (frame > 370) {
            scroll.update();
        }

        if (frame > 1200 && bigBoot.getY() > 85) {
            var y = bigBoot.getY() - 4;
            if (frame > 1210) {
                y -= 2;
            }
            if (frame > 1270)
                y -= 5;
            bigBoot.setY(y);
        }
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        var frame = Gdx.graphics.getFrameId();

        batch.begin();
        if (200 < frame && frame < 420)
            batch.draw(tuffGuy, W * 0.5f - tuffGuy.getWidth() / 2f, 0.1f * H);

        if (frame > 420) {
            kick.draw(batch);
            scroll.render(batch);
            if (frame > 530 && kick.getColor().a > 0.2f) {
                var a = kick.getColor().a - 0.005f;
                kick.setAlpha(a);
            }

            batch.draw(boot, kick.getX(), kick.getY());

            if (frame > 1200) {
                bigBoot.draw(batch);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
