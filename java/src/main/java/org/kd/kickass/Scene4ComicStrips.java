package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BaseScene2;

import java.util.List;

public class Scene4ComicStrips extends BaseScene2 {

    final static long START_FRAME = Scene3Counting.START_FRAME + 400;
    private Sprite comic1, comic2, comic3;
    private float spriteY = 125;
    private SpriteBatch batch;
    private Texture comic1Texture;
    private Texture comic2Texture;
    private Texture comic3Texture;

    @Override
    public void show() {
        comic1Texture = new Texture("kickass/comic1.png");
        comic1 = new Sprite(comic1Texture);

        comic2Texture = new Texture("kickass/comic2.png");
        comic2 = new Sprite(comic2Texture);

        comic3Texture = new Texture("kickass/comic3.png");
        comic3 = new Sprite(comic3Texture);

        //float X = Gdx.graphics.getWidth()/2f;
        float Y = Gdx.graphics.getHeight() / 2f;

        List.of(comic1, comic2, comic3).forEach(c -> {
            c.setScale(0.3f);
        });

        batch = new SpriteBatch();

        List.of(comic1, comic2, comic3).forEach(sprite1 -> sprite1.setPosition(20f, spriteY));
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        var fr = Long.valueOf(getCurrentFrame()).shortValue();

        var sprite = comic1;

        if (300 < fr && fr < 600) sprite = comic2;
        else if (599 < fr && fr < 900) sprite = comic3;

        sprite.setPosition(20f, spriteY);

        var alpha = 0.1f + 0.9f * (float) Math.abs(Math.sin(fr / 100f));
        batch.begin();
        sprite.draw(batch, alpha);
        batch.end();

        spriteY -= 1.5f;
    }

    private long getCurrentFrame() {
        return Gdx.graphics.getFrameId() - START_FRAME;
    }
}
