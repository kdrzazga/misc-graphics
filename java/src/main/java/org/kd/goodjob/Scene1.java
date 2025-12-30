package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.C64Helper;
import org.kd.common.Scene;

public class Scene1 extends Scene {

    private Batch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Texture appleLogo;

    public Scene1() {
        super("50");
    }

    @Override
    public void create() {
        this.font = C64Helper.createFont(700, "Big Daddy LED TFB.ttf");

        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.appleLogo = new Texture("good-job/apple-logo.png");
    }

    @Override
    public void update(float delta) {

        var frame = Gdx.graphics.getFrameId();

        if (850 < frame && frame < 850 + 500) {
            var a = 1 - (frame - 850f) / 500f;
            font.setColor(a, a, a, a);
        }
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();

        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (720 < frame && frame < 850 + 500)
            font.draw(batch, "50", 50, 750);

        else if (850 + 550 < frame && frame < 850 + 550 + 630)
            batch.draw(this.appleLogo, 0, 0);

        batch.end();

    }

    @Override
    public void dispose() {

    }
}
