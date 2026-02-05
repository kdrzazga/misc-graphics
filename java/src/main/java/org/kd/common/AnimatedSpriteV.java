package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSpriteV {
    Texture spriteSheet;
    Animation<TextureRegion> animation;
    float stateTime, scale = 1f;
    int x, y;

    public AnimatedSpriteV(String path, int frameCount, float frameDuration, int x, int y) {
        this.x = x;
        this.y = y;
        spriteSheet = new Texture(path);

        int FRAME_COLS = 1;
        int FRAME_ROWS = frameCount;
        int FRAME_WIDTH = spriteSheet.getWidth();
        int FRAME_HEIGHT = spriteSheet.getHeight() / FRAME_ROWS;

        TextureRegion[][] tmpRegions = TextureRegion.split(spriteSheet, FRAME_WIDTH, FRAME_HEIGHT);

        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmpRegions[i][j];
            }
        }
        this.animation = new Animation<>(frameDuration, frames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = this.animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y, currentFrame.getRegionWidth() * scale, currentFrame.getRegionHeight() * scale);
    }

    public void scale(float scaleValue) {
        this.scale = scaleValue;
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
