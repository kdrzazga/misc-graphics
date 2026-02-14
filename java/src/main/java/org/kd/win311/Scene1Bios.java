package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Colors;
import org.kd.common.C64Helper;
import org.kd.common.Scene;

public final class Scene1Bios extends Scene {

    private SpriteBatch batch;
    private BitmapFont font, font2;
    private Texture ludzik, awardLogo;

    public Scene1Bios() {
        super("BIOS");
    }

    @Override
    public void create() {
        font = C64Helper.createFont(26, C64Colors.LIGHT_GRAY, "win311/Ac437_Acer710_Mono.ttf");
        font2 = C64Helper.createFont(26, "win311/Ac437_Acer710_Mono.ttf");
        batch = new SpriteBatch();
        awardLogo = new Texture("win311/Energy-Star-logo.png");
        ludzik = new Texture("win311/ludzik.png");
    }

    @Override
    public void update(float delta) {
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        var H = Gdx.graphics.getHeight();
        var W = Gdx.graphics.getWidth();
        var frame = Gdx.graphics.getFrameId();

        batch.begin();
        font.draw(batch, "Award Modular BIOS v0.00PG. An Energy Star Ally.", 60, H - 30);
        font.draw(batch, "Copyright (C) 1984-99. Award Software, Inc.", 60, H - 30 - 20);
        batch.draw(ludzik, 0, H - 1.5f* ludzik.getHeight());
        batch.draw(awardLogo, W - awardLogo.getWidth(), H - awardLogo.getHeight());

        font.draw(batch, "B1W1M/B1DUPA BIOS V1.3", 5, H - 30 - 20 - 60);
        font.draw(batch, "Main Processor: MOS 6510 1000 GHz", 5, H - 30 - 20 - 30 - 90);
        font.draw(batch, "Memory Testing: 64 KB RAM + some Shared Memory", 5, H - 30 - 20 - 30 - 30 - 127);
        font.draw(batch, "Award Plug & Play BIOS Extension v1.0A", 5, H - 30 - 20 - 30 - 30 - 177);
        font.draw(batch, "Copyright (C) 1981, Award Sotfware, Inc.", 5, H - 30 - 20 - 30 - 30 - 207);


        if (frame > 100){
            font.draw(batch, "HIMEM: DOS XMS Driver, Version 100.2 - 02/15/26", 5, 250);
            font.draw(batch, "Extended Memory Specification (XMS) Version 3.0", 5, 220);
            font.draw(batch, "Copyleft 1988-2026 Microsoft Corp.", 5, 190);
            font2.draw(batch, "Trend ChipAwayVirus(R) On Guard Ver 1.81", 5, 50);

        }

        batch.end();
    }

    @Override
    public void dispose() {
        //batch.dispose();
    }
}
