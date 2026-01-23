package org.kd.hellogdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;
import org.kd.hellogdx.ScreensGame;

public class Screen1 implements Screen {

    private final ScreensGame game;
    private final BitmapFont font;
    private final SpriteBatch batch;

    public Screen1(ScreensGame game) {
        this.game = game;
        font = C64Helper.createFont(26, "DRENA.ttf");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        batch.begin();
        font.draw(batch, "THIS IS SCREEN 1", 30, 40);
        if (Gdx.input.justTouched()) {
            game.setScreen(new Screen2(game));
        }

        batch.end();
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
