package org.kd.fakedemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public class Scene2 extends Scene {
    ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Music anotherVisitor;

    public Scene2(String id) {
        super(id);
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        this.createFont();
        this.createMusic();
    }

    @Override
    public void update(float delta) {
        // update scene-specific logic
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawC64();

        long frame = Gdx.graphics.getFrameId();
        if (frame > 333 && frame < 333 + 7 * 50) {
            anotherVisitor.play();
        }

        if (frame > 333 + 8 * 50){
            Gdx.app.exit();
            System.exit(0);
        }
    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene B");
        shapeRenderer.dispose();
    }
    
    private void createFont() {
        var generator = new FreeTypeFontGenerator(Gdx.files.internal("fake-demo/dulski.ttf"));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        var parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        parameter2.size = 15;
        parameter2.color = Color.ORANGE;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    private void createMusic() {
        anotherVisitor = Gdx.audio.newMusic(Gdx.files.internal("fake-demo/anothervisitor.mp3"));
        anotherVisitor.setLooping(false);
        anotherVisitor.setVolume(1f);
    }


    private void drawC64() {
        Gdx.gl.glClearColor(1, 1, 1, 1); // Clear with white color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(50, 50, 200, 100); // x, y, width, height
        shapeRenderer.end();
        //font.draw(batch, "MSX: MODEM BY WODNIK", 10, Gdx.graphics.getHeight() - 10);
   }

}
