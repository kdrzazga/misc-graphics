package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public class Scene2 extends Scene {

    public static final long START_FRAME = 2180;

    private SpriteBatch batch;
    private Sprite jobsWayneWoz;

    public Scene2() {
        super("2");
    }

    @Override
    public void create() {
        var jobsWayneWozTexture = new Texture("good-job\\jobsWayneWoz.jpg");
        batch = new SpriteBatch();
        this.jobsWayneWoz = new Sprite(jobsWayneWozTexture);
        this.jobsWayneWoz.setScale(1.8f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {

        batch.begin();
        if (this.getRelativeFrame() > 500) {
            batch.draw(this.jobsWayneWoz, 0, Gdx.graphics.getHeight() / 2f - 50);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}
