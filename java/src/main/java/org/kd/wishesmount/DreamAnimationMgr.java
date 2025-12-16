package org.kd.wishesmount;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.kd.common.AnimationManager;
import org.kd.common.C64Colors;
import org.kd.common.Globals;
import org.kd.common.TravellingLogo;

import java.util.Arrays;

public class DreamAnimationMgr extends AnimationManager {
    private TravellingLogo logoMerry;
    private TravellingLogo logoChristmas;

    @Override
    public void create() {
        super.create();
        this.createMusic("dream210/przybiezeli.mp3");
        this.logoMerry = this.initLogo("dream210/merry.png", Globals.SCREEN_HEIGHT - 79);
        this.logoChristmas = this.initLogo("dream210/christmas.png", 39);

        var scene1 = new Scene1("scene1");

        Arrays.asList(scene1).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("scene1");
    }

    private TravellingLogo initLogo(String path, float y) {
        var logoTxtr = new Texture(Gdx.files.internal(path));
        var logo = new TravellingLogo(logoTxtr, Globals.SCREEN_WIDTH, y, 1000, 50);
        logo.colorize(C64Colors.LIGHT_BLUE);
        logo.spriteSpeed = 325f;

        return logo;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        long frame = Gdx.graphics.getFrameId();
        sceneManager.update(delta);

        batch.begin();
        sceneManager.render();
        if (frame > WishesHelper.ROCK_MUSIC_START_FRAME) {
            this.logoMerry.draw(batch, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
            this.logoChristmas.draw(batch, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        }
        batch.end();

        if (frame == WishesHelper.KOLENDA_STARTING_FRAME)
            tune.play();

        if (frame > WishesHelper.ROCK_MUSIC_START_FRAME) {
            moveBannerMerry(delta);
        }

    }

    private void moveBannerMerry(float delta) {
        long frame = Gdx.graphics.getFrameId();
        var initFrame =  WishesHelper.ROCK_MUSIC_START_FRAME;
        if (frame >= initFrame) {
            this.logoMerry.move(delta, Globals.SCREEN_WIDTH);
            if (frame > initFrame + 50)
                this.logoChristmas.move(delta, Globals.SCREEN_WIDTH);
            if (frame >= initFrame +500)
                Arrays.asList(this.logoMerry, this.logoChristmas).forEach(logo -> {
                    if (logo.getX() < -355 || logo.getX() > 260)
                        logo.changeDirection();
                });
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        tune.dispose();
        sceneManager.disposeScenes();
    }
}
