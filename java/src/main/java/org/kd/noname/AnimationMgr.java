package org.kd.noname;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kd.common.AnimationManager;
import org.kd.winter25.Scene2Outro;

import java.util.Arrays;

public class AnimationMgr extends AnimationManager {

    @Override
    public void create() {
        super.create();
        this.createMusic("noname/audio/PieceSzit2.mp3");
        this.tune.setLooping(true);
        var scene1 = new Scene1KickScroll();
        var sceneOutro = new Scene2Outro();

        Arrays.asList(scene1).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });

        sceneManager.switchScene("scene1-kick-scroll");
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sceneManager.update(delta);

        batch.begin();
        sceneManager.render();
        batch.end();

        long frame = Gdx.graphics.getFrameId();

        if (frame > 400/* && frame < 433 + 7 * 50*/) {
            tune.play();
        }

        if (frame == 9160) {
            sceneManager.switchScene("scene2");
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        tune.dispose();
        sceneManager.disposeScenes();
    }
}
