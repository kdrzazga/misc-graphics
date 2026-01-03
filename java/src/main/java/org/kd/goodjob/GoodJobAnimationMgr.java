package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kd.common.AnimationManager;

import java.util.Arrays;

public class GoodJobAnimationMgr extends AnimationManager {

    @Override
    public void create() {
        super.create();
        this.createMusic("good-job/base.mp3");
        tune.setLooping(false);
        tune.play();

        var scene1 = new Scene1intro();
        var scene2 = new Scene2();
        var scene3 = new Scene3();
        var scene4 = new Scene4JobsReturn();
        var scene5 = new Scene5Exit();

        Arrays.asList(scene1, scene2, scene3, scene4, scene5).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("50");
    }

    @Override
    public void render() {

        var frame = Gdx.graphics.getFrameId();
        //System.out.print(frame + " ");
        if (frame == Scene2.START_FRAME) {
            sceneManager.switchScene("2");
            this.createMusic("good-job/Ramosnoname.mp3"); //https://csdb.dk/sid/?id=23786
            this.tune.setVolume(1f);
            this.tune.setPosition(0.15f);
            tune.setLooping(false);
            tune.play();
        } else if (frame == Scene3.START_FRAME) {
            tune.stop();

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            sceneManager.switchScene("3");

        } else if (frame == Scene4JobsReturn.START_FRAME) {
            sceneManager.switchScene("4.Jobs Returns");
            this.createMusic("good-job/Fancyramos.mp3"); //https://csdb.dk/sid/?id=23786
            this.tune.setVolume(1f);
            this.tune.setPosition(0.15f);
            tune.setLooping(false);
            tune.play();
        } else if (frame == Scene5Exit.START_FRAME) {
            sceneManager.switchScene("exit");
        } else if (frame > Scene5Exit.DEMO_END_FRAME - 125) {
            var newVolume = tune.getVolume() * 0.97f;
            tune.setVolume(newVolume);

            if (frame == Scene5Exit.DEMO_END_FRAME) {
                Gdx.app.exit();
            }
        }

        sceneManager.update(0);
        sceneManager.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
