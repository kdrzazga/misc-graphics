package org.kd.xmas25;

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
        var scene2 = new Scene2("scene2");

        Arrays.asList(scene1, scene2).forEach(s -> {
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
        sceneManager.render();

        batch.begin();
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

        if (frame > WishesHelper.SCENE2_START_FRAME + 6870) {
            float vol = this.tune.getVolume();
            if (vol >= 0.01f) {
                this.tune.setVolume(vol - 0.003f);
            } else {
                this.tune.stop();
            }

            if (frame > WishesHelper.SCENE2_START_FRAME + 7181) {
                Gdx.app.exit();
                System.out.println("\n".repeat(50) + "Herzliche Gruesse an Team210! Danke, dass sie den Drem210 organisiert haben!");

                //this.dispose();
                System.exit(0);
            }
        }

        if (frame == WishesHelper.SCENE2_START_FRAME)
            sceneManager.switchScene("scene2");
    }

    private void moveBannerMerry(float delta) {
        long frame = Gdx.graphics.getFrameId();
        var initFrame = WishesHelper.ROCK_MUSIC_START_FRAME;
        if (frame >= initFrame) {
            this.logoMerry.move(delta, Globals.SCREEN_WIDTH);
            if (frame > initFrame + 50)
                this.logoChristmas.move(delta, Globals.SCREEN_WIDTH);
            if (frame >= initFrame + 500)
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
