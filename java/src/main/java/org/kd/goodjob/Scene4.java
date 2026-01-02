package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public final class Scene4 extends Scene {

    public static final long START_FRAME = Scene3.START_FRAME + 629;
    private SpriteBatch batch4;
    private Sprite comebackPic, jobs, mac1, mac2;

    public Scene4() {
        super("4.Jobs Returns");
    }

    @Override
    public void create() {
        batch4 = new SpriteBatch();
        var comebackTxtr = new Texture("good-job/bigComeback.png");
        this.comebackPic = new Sprite(comebackTxtr);
        this.comebackPic.setPosition(Gdx.graphics.getWidth() / 8f, Gdx.graphics.getHeight() / 4f);

        var jobsTxtr = new Texture("good-job/JobsComeback/jobs.png");
        this.jobs = new Sprite(jobsTxtr);
        this.jobs.setScale(0.65f);

        var macTexture = new Texture("good-job/macintosh/makintosz.png");
        mac1 = new Sprite(macTexture);
        mac1.setPosition(10, 81);
        mac2 = new Sprite(macTexture);
        mac2.flip(true, false);
        mac2.setPosition(Gdx.graphics.getWidth() * 0.81f, 81);
    }

    @Override
    public void update(float delta) {
        if (getRelativeFrame() > 240) {

            if (getRelativeFrame() % 3 == 1) {
                var newScale = Math.min(1, this.jobs.getScaleX()) + 0.01f;
                this.jobs.setScale(newScale);
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch4.begin();
        if (getRelativeFrame() < 240) {
            comebackPic.draw(batch4);
        } else {
            mac1.draw(batch4);
            mac2.draw(batch4);
        }

        var jobsX = Gdx.graphics.getWidth() / 2f - this.jobs.getWidth() / 2;
        var jobsY = Gdx.graphics.getHeight() - this.jobs.getHeight() - 2;
        batch4.draw(this.jobs, jobsX, jobsY);

        this.drawPixels(batch4);

        batch4.end();
    }

    private void drawPixels(SpriteBatch batch) {
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(1, 1, 3, 3);
        pixmap.fillRectangle(20, 20, 3, 3);
        pixmap.fillRectangle(30, 70, 3, 3);
        pixmap.fillRectangle(70, 30, 3, 3);
        Texture texture = new Texture(pixmap);


        batch.draw(texture, 0, 150);

        texture.dispose();
        pixmap.dispose();
    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}
