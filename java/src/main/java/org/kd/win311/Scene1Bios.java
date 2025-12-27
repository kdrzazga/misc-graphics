package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;
import org.kd.common.Scene;

public final class Scene1Bios extends Scene {

    private SpriteBatch batch;
    private BitmapFont font;

    public Scene1Bios() {
        super("BIOS");
    }

    @Override
    public void create() {
        font = C64Helper.createFont(26, "win311/Ac437_Acer710_Mono.ttf");
        batch = new SpriteBatch();
    }

    @Override
    public void update(float delta) {
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        var h = Gdx.graphics.getHeight();
        batch.begin();
        font.draw(batch, "Award Modular BIOS v0.00PG. An Energy Star Ally.", 30, h-30);
        font.draw(batch, "Copyright (C) 1984-99. Award Software, Inc.", 30, h-30-20);
        batch.end();
    }

    @Override
    public void dispose() {
        //batch.dispose();
    }
}
