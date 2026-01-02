package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;
import org.kd.common.ConsoleLogger;
import org.kd.common.Scene;
import org.kd.common.tricks.Effects;
import org.kd.goodjob.appendix.BannerApple;

public final class Scene3 extends Scene {

    public static final long START_FRAME = Scene2.START_FRAME + 5029;

    private SpriteBatch batch3;
    private BitmapFont  fontSmall;
    private Sprite comebackPic;

    public Scene3() {
        super("3");
    }

    @Override
    public void create() {
        batch3 = new SpriteBatch();
        this.fontSmall = C64Helper.createFont(50, "Helvetica Regular.otf");
        var comebackTxtr = new Texture("good-job/bigComeback.png");
        this.comebackPic = new Sprite(comebackTxtr);
        this.comebackPic.setScale(0.01f);
    }

    @Override
    public void update(float delta) {
        if (getRelativeFrame() > 480){
            float newScale = Math.min(1f, this.comebackPic.getScaleX() + 0.01f);
            this.comebackPic.setScale(newScale);
            this.comebackPic.setPosition(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/4);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch3.begin();
        Effects.typewriter(batch3, fontSmall, 20, 900, START_FRAME + 60, 260, "Steve Jobs quit APPLE in 1985 and founded NeXT(another computer producer).", 2);
        Effects.typewriter(batch3, fontSmall, 20, 830, START_FRAME + 210, 140, "Apple continued development of new models of Macintosh,", 2);
        Effects.typewriter(batch3, fontSmall, 20, 830-70, START_FRAME + 330,160, "but was no longer a key player on a market.", 1);
        Effects.typewriter(batch3, fontSmall, 20, 830-140, START_FRAME + 350, 90,"Finally, in 1997 the company bought NeXT.", 1);

        if (getRelativeFrame() > 350){
            comebackPic.draw(batch3);
        }

        batch3.end();

        ConsoleLogger.logBannerWithElapsedTime(BannerApple.lines);
    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}
