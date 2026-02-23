package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.tricks.RollerShades;
import org.kd.common.tricks.TravellingLogo;

public class Scene2Karateka implements Screen {

    final static long START_FRAME = 3550;
    private TravellingLogo logo, wallpaper;
    private SpriteBatch batch;
    private AnimatedSpriteV karateka;
    private RollerShades shades;
    private ShapeRenderer shapeRenderer;

    @Override
    public void show() {
        float X = Gdx.graphics.getWidth() / 2f;// - 250f/2f;
        float Y = Gdx.graphics.getHeight() - 60;
        var texture = new Texture("kickass/ka.png");
        logo = new TravellingLogo(texture, 1700, Y, 721, 50);
        batch = new SpriteBatch();
        karateka = new AnimatedSpriteV("kickass/karateka4.png", 53, 0.05f
                , Math.round(X - 200), 1);
        karateka.scale(3.0f);

        var texture2 = new Texture("kickass/karateksa.jpg");
        wallpaper = new TravellingLogo(texture2, 1, 0, 3500, 990);
        wallpaper.spriteSpeed = 2f;
        shapeRenderer = new ShapeRenderer();
        shades = new RollerShades(Scene3Counting.START_FRAME - 350, Color.BLACK, 11);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        update();

        batch.begin();
        logo.draw(batch, 2 * 1700, 990);
        logo.spriteSpeed = 700f;

        wallpaper.draw(batch, 2 * 1700, 990);
        wallpaper.spriteSpeed = 700f;

        karateka.draw(batch);
        batch.end();

        var frame = Gdx.graphics.getFrameId();
        if (frame > shades.getStartFrame()) shades.render(shapeRenderer);
    }

    private void update() {
        var delta = Gdx.graphics.getDeltaTime();
        logo.move(delta, 1700);
        wallpaper.move(delta/5f, 1700);

        long limit = 565;
        if (getCurrentFrame() > limit) {
            var newScale = 3f + (getCurrentFrame() - limit) * 0.999999f;
            if (newScale > 0) karateka.scale(newScale);
        }
    }

    private long getCurrentFrame() {
        return Gdx.graphics.getFrameId() - START_FRAME;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
