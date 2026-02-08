package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.tricks.TravellingLogo;

public class Scene2Karateka implements Screen {

    final static long START_FRAME = 3300;
    private TravellingLogo logo, karatekas;
    private SpriteBatch batch;
    private AnimatedSpriteV karateka;
    @Override
    public void show() {
        float X = Gdx.graphics.getWidth() / 2f;// - 250f/2f;
        float Y = Gdx.graphics.getHeight() - 60;
        var texture = new Texture("kickass/ka.png");
        logo = new TravellingLogo(texture, 1700, Y, 721, 50);
        batch = new SpriteBatch();
        karateka = new AnimatedSpriteV("kickass/karateka4.png", 53, 0.05f, Math.round(X), 1);
        karateka.scale(3.0f);

        var texture2 = new Texture("kickass/karateksa.jpg");
        karatekas = new TravellingLogo(texture2, 1, 0, 3500, 990);
        karatekas.spriteSpeed = 2f;
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        update();

        batch.begin();
        logo.draw(batch, 2 * 1700, 990);
        logo.spriteSpeed = 700f;

        karatekas.draw(batch, 2 * 1700, 990);
        karatekas.spriteSpeed = 700f;

        karateka.draw(batch);
        batch.end();
    }

    private void update() {
        var delta = Gdx.graphics.getDeltaTime();
        logo.move(delta, 1700);
        karatekas.move(delta/5f, 1700);

        long limit = 565;
        if (getCurrentFrame() > limit) {
            var newScale = 3f + (getCurrentFrame() - limit) * 0.0021f;
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
