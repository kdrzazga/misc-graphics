package org.kd.c64;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.c64.data.C64Colors;

public class Scene1Tiles extends Scene {
    ShapeRenderer shapeRenderer;
    private Music anotherVisitor;

    public Scene1Tiles(String id) {
        super(id);
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
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
            anotherVisitor.play();
        }
    }

    private void createMusic() {
        anotherVisitor = Gdx.audio.newMusic(Gdx.files.internal("anothervisitor.mp3"));
        anotherVisitor.setLooping(false);
        anotherVisitor.setVolume(1f);
    }

    private void drawC64() {
        Gdx.gl.glClearColor(C64Colors.LIGHT_BLUE.getR(), C64Colors.LIGHT_BLUE.getG(), C64Colors.LIGHT_BLUE.getB(), 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(C64Colors.BLUE.getR(), C64Colors.BLUE.getG(), C64Colors.BLUE.getB(), 1);
        shapeRenderer.rect(50, 50, 200, 100); // x, y, width, height
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene1Tiles");
        shapeRenderer.dispose();
        anotherVisitor.dispose();
    }
}
