package org.kd.winter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Colors;
import org.kd.common.Scene;

public class Scene1c64 extends Scene {
    SpriteBatch batch;
    Texture backgroundTexture;
    private Music kolendaRamosa;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("c64.png"));
        this.createMusic();
    }

    @Override
    public void update(float delta) {
        // update scene-specific logic
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(C64Colors.LIGHT_BLUE.getR(), C64Colors.LIGHT_BLUE.getG(), C64Colors.LIGHT_BLUE.getB(), 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawC64();

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

    private void drawC64() {
        float scale = 1f; // better don't scale, results are pathetic

        float scaledWidth = backgroundTexture.getWidth() * scale;
        float scaledHeight = backgroundTexture.getHeight() * scale;
        float x = (Gdx.graphics.getWidth() - scaledWidth) / 2;
        float y = (Gdx.graphics.getHeight() - scaledHeight) / 2;

        batch.begin();
        batch.draw(backgroundTexture, x, y, scaledWidth, scaledHeight);
        batch.end();
    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene1Tiles");
        batch.dispose();
        backgroundTexture.dispose();
        kolendaRamosa.dispose();
    }
}
