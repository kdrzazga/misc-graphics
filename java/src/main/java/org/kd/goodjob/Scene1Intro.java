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

public final class Scene1Intro extends Scene {

    private SpriteBatch batch;
    private BitmapFont hugeFont, fontSmall;
    private Sprite appleLogo;
    private Texture appleTexture;
    private final int fiftyFontSize = 700;

    private float progressBarWidth = 0f;
    private ShapeRenderer shapeRenderer;

    public Scene1Intro() {
        super("50");
    }

    @Override
    public void create() {
        this.hugeFont = C64Helper.createFont(fiftyFontSize, "Big Daddy LED TFB.ttf");
        this.fontSmall = C64Helper.createFont(45, "Helvetica Regular.otf");

        this.batch = new SpriteBatch();

        this.appleTexture = new Texture("good-job/apple-logo.png");
        this.appleLogo = new Sprite(appleTexture);

        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
        var frame = Gdx.graphics.getFrameId();
        if (25 < frame)
            Gdx.input.setCursorPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (850 < frame && frame < 850 + 500) {
            var colorParam = 1 - (frame - 850f) / 500f;
            hugeFont.setColor(colorParam, colorParam, colorParam, colorParam);
        }

        progressBarWidth += (float) (0.5 + 3.5*Math.random());
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (frame < 720) {
            shapeRenderer.setColor(Color.LIGHT_GRAY);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(140f, 300, progressBarWidth, 30);
            shapeRenderer.end();
        } else if (frame < 850 + 500)
            hugeFont.draw(batch, "50", Gdx.graphics.getWidth() / 2f - 3 * fiftyFontSize / 4f, Gdx.graphics.getHeight() / 2f + fiftyFontSize / 2f);

        else if (850 + 550 < frame && frame < 850 + 550 + 630) {
            float a = Math.max(0.01f, 1 - (frame - 850f - 550f) / 500f);
            batch.setColor(new Color(a, a, a, 1f));
            var x = Gdx.graphics.getWidth() / 2 - appleTexture.getWidth() / 2;
            var y = Gdx.graphics.getHeight() / 2 - appleTexture.getHeight() / 2;
            batch.draw(this.appleLogo, x, y);
        } else if (2099 < frame && frame < 2180) {
            Effects.typewriter(batch, fontSmall, 20, 900, 2100, 80, "On April 1st, 1976", 2);
            Effects.typewriter(batch, fontSmall, 20, 850, 2141, 62, "the legend was born...........", 2);
        }

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
