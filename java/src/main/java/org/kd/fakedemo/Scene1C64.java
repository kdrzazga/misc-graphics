package org.kd.fakedemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import org.kd.common.BasicC64Screen;

public class Scene1C64 extends BasicC64Screen {
    private Music anotherVisitor;

    public Scene1C64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.createMusic();
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        if (frame > 333 && frame < 333 + 7 * 50) {
            anotherVisitor.play();
        }
    }

    private void createMusic() {
        anotherVisitor = Gdx.audio.newMusic(Gdx.files.internal("fake-demo/anothervisitor.mp3"));
        anotherVisitor.setLooping(false);
        anotherVisitor.setVolume(1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        anotherVisitor.dispose();
    }
}
