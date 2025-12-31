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
        var scene2 = new Scene2();

        Arrays.asList(scene1, scene2).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("50");
    }

    @Override
    public void render() {
        super.render();

        var frame = Gdx.graphics.getFrameId();
        System.out.print(frame + " ");
        if (frame == Scene2.START_FRAME){
            sceneManager.switchScene("2");
            this.createMusic("good-job/Ramosnoname.mp3"); //https://csdb.dk/sid/?id=23786
            this.tune.setVolume(1f);
            tune.setLooping(true);
            tune.play();
        }

        sceneManager.update(0);
        sceneManager.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
