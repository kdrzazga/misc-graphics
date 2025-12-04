package org.kd.c64;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.BasicC64Screen;
import org.kd.common.Scene;
import org.kd.common.C64Colors;

public class Scene1Tiles extends BasicC64Screen {
    private Music anotherVisitor;

    public Scene1Tiles(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.createMusic();
    }

    @Override
    public void update(float delta) {
        // update scene-specific logic
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
        anotherVisitor = Gdx.audio.newMusic(Gdx.files.internal("anothervisitor.mp3"));
        anotherVisitor.setLooping(false);
        anotherVisitor.setVolume(1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        anotherVisitor.dispose();
    }
}
