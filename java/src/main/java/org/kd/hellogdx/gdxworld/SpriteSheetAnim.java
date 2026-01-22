package org.kd.hellogdx.gdxworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheetAnim extends ApplicationAdapter {
    SpriteBatch batch;
    Texture spriteSheet;
    Animation<TextureRegion> animation;
    float stateTime;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Load sprite sheet texture
        spriteSheet = new Texture("spritesheets/owls.png");

        // Assuming sprite sheet has 4 frames horizontally
        int FRAME_COLS = 2;
        int FRAME_ROWS = 1;
        int FRAME_WIDTH = 49;
        int FRAME_HEIGHT = 44;

        TextureRegion[][] tmpRegions = TextureRegion.split(spriteSheet, FRAME_WIDTH, FRAME_HEIGHT);

        // Convert 2D array to 1D array for animation frames
        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmpRegions[i][j];
            }
        }

        // Create animation with frame duration (e.g., 0.3 seconds per frame)
        animation = new Animation<>(0.3f, frames);
        stateTime = 0f;
    }

    @Override
    public void render() {

        // Clear screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update state time
        stateTime += Gdx.graphics.getDeltaTime();

        // Get current frame based on elapsed time
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true); // loop = true

        // Draw the current frame
        batch.begin();
        batch.draw(currentFrame, 25, 25); // Draw at position (100, 100)
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
    }
}
