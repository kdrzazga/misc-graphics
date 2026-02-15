package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.tricks.Rosette;
import org.kd.kickass.lib.BootAlphabetScroll;

import java.util.Arrays;

public class Scene1KickScroll implements Screen {

    private Sound fok, whatchaLookinAt;
    private Texture tuffGuy;
    private Sprite kick, boot, bigBoot;
    private SpriteBatch batch;
    private int W, H;
    private BootAlphabetScroll scroll;
    private Rosette rosette;
    private ShapeRenderer shapeRenderer;

    @Override
    public void show() {
        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        fok = Gdx.audio.newSound(Gdx.files.internal("kickass/audio/fok.mp3"));
        whatchaLookinAt = Gdx.audio.newSound(Gdx.files.internal("kickass/audio/WhatAreYouLookingAt.mp3"));

        Arrays.asList(fok, whatchaLookinAt).forEach(sound -> {
            sound.setLooping(1, false);
            sound.setVolume(1L, 1f);
        });

        tuffGuy = new Texture("kickass/wb.png");
        boot = new Sprite(new Texture("kickass/boot.png"));
        kick = new Sprite(new Texture("kickass/kick.png"));
        kick.setPosition(W * 0.5f - kick.getWidth() / 2f, 0.1f * H);
        boot.setPosition(kick.getX(), kick.getY());

        bigBoot = new Sprite(new Texture("kickass/leftboot.png"));
        bigBoot.setScale(1.3f);
        bigBoot.setPosition(W / 2f - bigBoot.getWidth() / 2f, 1.2f * H);

        String scrollText = "Welcome to this ass kicking demo     " +
                "In the beginning I would like to send greetings to Pan Areczek " +
                "of Komoda and Amiga PLUS            " +
                "Respect for publishing a great magazine and releasing games " +
                "    Hope to see you soon   ";
        scroll = new BootAlphabetScroll(scrollText.toLowerCase(), 0 + 480);

        shapeRenderer = new ShapeRenderer();
        rosette = new Rosette(3300, Color.BLACK);
    }

    public void update() {
        var frame = Gdx.graphics.getFrameId();

        switch (Long.valueOf(frame).shortValue()) {
            case 200 -> {
                Gdx.input.setCursorPosition(W, H);
                whatchaLookinAt.play();
            }
            case 370 -> fok.play();
        }

        if (frame > 370) {
            scroll.update();
        }

        if (frame > 3200 - 150 && bigBoot.getY() > 85) {
            var y = bigBoot.getY() - 4;
            if (frame > 3210 - 150) {
                y -= 2;
            }
            if (frame > 3270 - 150) {
                y -= 5;
                var newScale = kick.getScaleY() * 0.985f;
                kick.setScale(1f, newScale);
                kick.setY(kick.getY() - 3f);
                boot.setY(boot.getY() - 6.6f);
            }
            bigBoot.setY(y);
        }
    }

    @Override
    public void render(float v) {

        update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        var frame = v;

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

            boot.draw(batch);

            if (frame > 1200) {
                bigBoot.draw(batch);
            }
        }
        batch.end();

        if (frame > rosette.getStartFrame()) rosette.render(shapeRenderer);
    }

    @Override
    public void resize(int i, int i1) {
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
