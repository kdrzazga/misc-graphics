package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;
import org.kd.tricks.GradientLines;

public final class Scene4JobsReturn extends Scene {

    public static final long START_FRAME = Scene3Typewriter.START_FRAME + 629;
    private SpriteBatch batch4;
    private Sprite comebackPic, jobs, mac1, mac2, wallpaper;
    private GradientLines trick;

    public Scene4JobsReturn() {
        super("4.Jobs Returns");
    }

    @Override
    public void create() {
        batch4 = new SpriteBatch();
        var comebackTxtr = new Texture("good-job/bigComeback.png");
        this.comebackPic = new Sprite(comebackTxtr);
        int W = Gdx.graphics.getWidth();
        int H = Gdx.graphics.getHeight();
        this.comebackPic.setPosition(W / 8f, H / 4f);

        var jobsTxtr = new Texture("good-job/JobsComeback/jobs.png");
        this.jobs = new Sprite(jobsTxtr);
        this.jobs.setScale(0.65f);

        var macTexture = new Texture("good-job/macintosh/makintosz.png");
        mac1 = new Sprite(macTexture);
        mac1.setPosition(21, 81);
        mac2 = new Sprite(macTexture);
        mac2.flip(true, false);
        mac2.setPosition(W * 0.81f, 81);

        var wallpaperTexture = new Texture("good-job/wallpaper.png");
        var wallpaperX = W / 2f - wallpaperTexture.getWidth() / 2f;
        var wallpaperY = H / 2f - wallpaperTexture.getHeight() / 2f;
        wallpaper = new Sprite(wallpaperTexture);
        wallpaper.setPosition(wallpaperX, wallpaperY);

        this.trick = new GradientLines(new ShapeRenderer(), Math.round(W / 4f), Math.round(H / 4f), Math.round(3f * W / 4f), Math.round(3f * H / 4f), Color.BLACK);

    }

    @Override
    public void update(float delta) {
        if (240 < getRelativeFrame() && getRelativeFrame() < 2800) {

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

        if (getRelativeFrame() < 800) {
            var jobsX = Gdx.graphics.getWidth() / 2f - this.jobs.getWidth() / 2;
            var jobsY = Gdx.graphics.getHeight() - this.jobs.getHeight() - 2;
            jobs.setPosition(jobsX, jobsY);
            jobs.draw(batch4);
        } else if (getRelativeFrame() < 3800) {
            batch4.end();
            this.trick.draw(6);
            batch4.begin();
            wallpaper.draw(batch4);
        }

        if (getRelativeFrame() < 240) {
            comebackPic.draw(batch4);
        } else {
            mac1.draw(batch4);
            mac2.draw(batch4);
        }

        batch4.end();
    }


    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}
