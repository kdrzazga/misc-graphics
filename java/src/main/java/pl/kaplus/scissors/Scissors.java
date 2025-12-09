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
        logo = new TravellingLogo(new Texture(Gdx.files.internal("anniversaries/logo.png")), 800, 100, 1000, 50);
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
            logo.draw(batch);
            batch.end();
            ScissorStack.popScissors();
        }

        // Reset sprite position if it moves off screen
        if (logo.getX() + logo.getWidth() < 0) {
            // Reset position to start at right edge
            logo.sprite.setX(800);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        // dispose logo texture if necessary
        // (You might want to keep a reference to the texture for disposal)
    }
}
