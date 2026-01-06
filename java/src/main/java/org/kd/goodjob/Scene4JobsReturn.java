package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;
import org.kd.common.tricks.AlphabetScroll;
import org.kd.tricks.GradientLines;

public final class Scene4JobsReturn extends Scene {

    public static final long START_FRAME = Scene3Typewriter.START_FRAME + 929;
    private SpriteBatch batch4;
    private Sprite comebackPic, jobs, mac1, mac2, wallpaper;
    private Texture tombstone, iMac, iPod, iPhone;
    private GradientLines backgroundLinesTrick;
    private AlphabetScroll scroll;

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
        int macY = 81;
        mac1.setPosition(11, macY);
        mac2 = new Sprite(macTexture);
        mac2.flip(true, false);
        mac2.setPosition(W * 0.81f, macY);

        var wallpaperTexture = new Texture("good-job/wallpaper.png");
        var wallpaperX = W / 2f - wallpaperTexture.getWidth() / 2f;
        var wallpaperY = H / 2f - wallpaperTexture.getHeight() / 2f;
        wallpaper = new Sprite(wallpaperTexture);
        wallpaper.setPosition(wallpaperX, wallpaperY);

        this.tombstone = new Texture("good-job/tombstone/tombstone.png");
        this.iMac = new Texture("good-job/iMac.png");
        this.iPod = new Texture("good-job/iPod.png");
        this.iPhone = new Texture("good-job/iPhone.png");

        this.backgroundLinesTrick = new GradientLines(new ShapeRenderer(), Math.round(W / 4f), Math.round(H / 4f), Math.round(3f * W / 4f), Math.round(3f * H / 4f), Color.BLACK);

        scroll = new AlphabetScroll("after returning to apple  steve jobs spearheaded the launch of the " +
                "imac  revitalizing the company design and technology  he then led the development of " +
                "groundbreaking products like the ipod  iphone   and the ipad   transforming " +
                "multiple industries   jobs continued to innovate until his death  leaving a lasting legacy as a " +
                "visionary tech pioneer", START_FRAME + 480);
        scroll.setShiftY(81f);
        scroll.scale(0.7f);
    }

    @Override
    public void update(float delta) {
        if (240 < getRelativeFrame() && getRelativeFrame() < 4300) {

            if (getRelativeFrame() % 3 == 1) {
                var newScale = Math.min(1, this.jobs.getScaleX()) + 0.01f;
                this.jobs.setScale(newScale);
            }
            if (getRelativeFrame() % 2 == 0)
                scroll.update();
        }

        System.out.println("4.Job's return " + getRelativeFrame());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch4.begin();

        if (getRelativeFrame() < 800) {
            var jobsX = Gdx.graphics.getWidth() / 2f - this.jobs.getWidth() / 2f;
            var jobsY = Gdx.graphics.getHeight() - this.jobs.getHeight() - 2f;
            jobs.setPosition(jobsX, jobsY);
            jobs.draw(batch4);
        } else if (getRelativeFrame() < 3800) {
            batch4.end();
            this.backgroundLinesTrick.draw(6);
            batch4.begin();
            wallpaper.draw(batch4);
            if (1400 < getRelativeFrame() && getRelativeFrame() < 1500)
                batch4.draw(iMac, Gdx.graphics.getWidth() / 2f - 265, Gdx.graphics.getHeight() / 2f);
            else if (2400 < getRelativeFrame() && getRelativeFrame() < 2560)
                batch4.draw(iPod, Gdx.graphics.getWidth() / 2f - 265, Gdx.graphics.getHeight() / 2f);
            else if ( getRelativeFrame() < 2760)
                batch4.draw(iPhone, Gdx.graphics.getWidth() / 2f - 265, Gdx.graphics.getHeight() / 2f);

        }

        if (3300 < getRelativeFrame() && getRelativeFrame() < 3800)
            batch4.draw(tombstone, Gdx.graphics.getWidth() / 2f - 265, Gdx.graphics.getHeight() / 3f);

        if (getRelativeFrame() < 240) {
            comebackPic.draw(batch4);
        } else {
            scroll.render(batch4);
            mac1.draw(batch4);
            mac2.draw(batch4);
        }

        batch4.end();
    }

    @Override
    public void dispose() {
        batch4.dispose();
    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}
