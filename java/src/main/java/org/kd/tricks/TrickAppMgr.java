package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kd.common.AnimationManager;

public class TrickAppMgr extends AnimationManager {

    @Override
    public void create() {
        super.create();

        var scene1 = new TrickScene1(50, 50, Gdx.graphics.getWidth() - 50, Gdx.graphics.getHeight() - 50);
        var scene2 = new TrickScene2(50, 50, Gdx.graphics.getWidth() - 50, Gdx.graphics.getHeight() - 50);
        var scene3 = new StripeLettersScene();

        sceneManager.addScene(scene1.id, scene1);
        sceneManager.addScene(scene2.id, scene2);
        sceneManager.addScene(scene3.id, scene3);
        sceneManager.switchScene("trick-scene1");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        sceneManager.update(delta);
        sceneManager.render();

        var frame = Gdx.graphics.getFrameId();
        if (frame == Global.TRICK2_FRAME) {
            sceneManager.switchScene("trick-scene2");
            System.out.println("Scene 2 - stars");
        }else if (frame == Global.TRICK3_FRAME) {
            sceneManager.switchScene("stripe-letters");
            System.out.println("Scene 3 - stripe letters");
        }
    }
}
