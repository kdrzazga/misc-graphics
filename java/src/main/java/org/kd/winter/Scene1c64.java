package org.kd.winter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Scene1c64 extends BasicC64Screen {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    Texture backgroundTexture;
    private Music kolendaRamosa;

    public Scene1c64(String id) {
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
            kolendaRamosa.play();
        }
    }

    private void createMusic() {
        kolendaRamosa = Gdx.audio.newMusic(Gdx.files.internal("winter/HejKoledaRamos.mp3"));
        kolendaRamosa.setLooping(false);
        kolendaRamosa.setVolume(1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        kolendaRamosa.dispose();
    }
}
