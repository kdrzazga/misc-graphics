package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import org.kd.common.AnimationManager;

import java.util.Arrays;

public class GoodJobAnimationMgr extends AnimationManager {

    @Override
    public void create() {
        super.create();
        this.createMusic("good-job/base.mp3");
        tune.setLooping(false);
        tune.play();

        var scene1 = new Scene1();

        Arrays.asList(scene1).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("50");
    }

    @Override
    public void render() {
        super.render();

        var frame = Gdx.graphics.getFrameId();
        //System.out.print(frame + " ");

        sceneManager.update(0);
        sceneManager.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
