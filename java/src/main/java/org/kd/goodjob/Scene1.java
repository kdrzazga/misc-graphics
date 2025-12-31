package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.C64Helper;
import org.kd.common.Scene;
import org.kd.common.tricks.Effects;

public class Scene1 extends Scene {

    private SpriteBatch batch;
    private BitmapFont hugeFont, fontSmall;
    private ShapeRenderer shapeRenderer;
    private Sprite appleLogo;

    public Scene1() {
        super("50");
    }

    @Override
    public void create() {
        this.hugeFont = C64Helper.createFont(700, "Big Daddy LED TFB.ttf");
        this.fontSmall = C64Helper.createFont(50, "Helvetica Regular.otf");

        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        var appleTexture = new Texture("good-job/apple-logo.png");

        this.appleLogo = new Sprite(appleTexture);
    }

    @Override
    public void update(float delta) {

        var frame = Gdx.graphics.getFrameId();

        if (850 < frame && frame < 850 + 500) {
            var a = 1 - (frame - 850f) / 500f;
            hugeFont.setColor(a, a, a, a);
        }
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (720 < frame && frame < 850 + 500)
            hugeFont.draw(batch, "50", 50, 750);

        else if (850 + 550 < frame && frame < 850 + 550 + 630) {
            float a = Math.max(0.01f, 1 - (frame - 850f - 550f) / 500f);
            batch.setColor(new Color(a, a, a, 1f));
            batch.draw(this.appleLogo, 30, 0);
        } else if (2099 < frame && frame < 2180) {
            Effects.typewriter(batch, fontSmall, 20, 900, 2100, "April 1st, 1976...", 2);
            Effects.typewriter(batch, fontSmall, 20, 850, 2140, "the legend was born...", 2);
        }

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
