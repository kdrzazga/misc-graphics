package pl.kaplus.scissors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.ScreenUtils;

public class Scissors extends ApplicationAdapter {
    SpriteBatch batch;
    Texture backgroundTexture;
    Texture choinkaTxtr;
    TravellingLogo logo;

    // Clipping rectangle
    final float clipX = 0;
    final float clipY = 0;
    final float clipWidth = 800;
    final float clipHeight = 600;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("c64.png");
        choinkaTxtr = new Texture("winter/choinka.png");
        Texture logoTxtr = new Texture(Gdx.files.internal("anniversaries/logo.png"));
        logo = new TravellingLogo(logoTxtr, 800, 200, 1000, 50);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Move the logo
        logo.move(deltaTime);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0, 0, 0, 1);

        // Setup scissor for clipping
        int scissorX = (int) clipX;
        int scissorY = (int) clipY;
        var scissors = new Rectangle(scissorX, scissorY, clipWidth, clipHeight);
        if (ScissorStack.pushScissors(scissors)) {
            batch.begin();
            batch.draw(backgroundTexture, 0, 0, 800, 600);
            batch.draw(choinkaTxtr, 250, 50, choinkaTxtr.getWidth(), choinkaTxtr.getHeight());
            logo.draw(batch);
            batch.end();
            ScissorStack.popScissors();
        }

        if (logo.getX() + logo.getWidth() < 0) {
            logo.sprite.setX(801);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
    }
}
