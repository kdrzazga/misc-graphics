package pl.kaplus.scissors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.graphics.glutils.ScissorStack;

public class Scissors extends ApplicationAdapter {
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    float spriteSpeed = 100f; // pixels per second

    // Define the clipping rectangle (for example, the game viewport)
    // Let's clip to the game window size: 800x600
    final float clipX = 0;
    final float clipY = 0;
    final float clipWidth = 800;
    final float clipHeight = 600;

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("anniversaries/logo.png"));
        sprite = new Sprite(texture);
        sprite.setSize(1000, 50);
        sprite.setPosition(800, 100);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        float newX = sprite.getX() - spriteSpeed * deltaTime;
        sprite.setX(newX);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Setup scissor for clipping
        // Push scissor to clip rendering to the specified rectangle
        ScreenUtils.clear(0, 0, 0, 1);
        // Calculate scissor bounds in terms of screen pixels
        // For simplicity, use the clip rectangle defined above
        // Convert to integer pixel bounds
        int scissorX = (int) clipX;
        int scissorY = (int) clipY;

        Rectangle scissors = new Rectangle(scissorX, scissorY, clipWidth, clipHeight);
        ScissorStack pushScissors = new ScissorStack();
        // Push scissor rectangle
        if (pushScissors.pushScissors(scissors)) {
            // Draw sprite within scissor
            batch.begin();
            sprite.draw(batch);
            batch.end();

            // Pop scissor after drawing
            ScissorStack.popScissors();
        }

        // Optional: Reset sprite position if it moves off-screen
        if (sprite.getX() + sprite.getWidth() < 0) {
            sprite.setX(800);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
