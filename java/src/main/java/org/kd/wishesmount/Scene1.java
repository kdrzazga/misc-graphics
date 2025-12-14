package org.kd.wishesmount;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;
import org.kd.common.Globals;
import org.kd.common.winter.WinterEffects;

import java.util.List;

public class Scene1 extends BasicC64Screen {
    private boolean snowing;
    private List<Sprite> snowflakes;
    public SpriteBatch batch2;

    public Scene1(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
        this.snowflakes = WinterEffects.createSnowflakeSprites();
        this.snowflakes.forEach(flake -> flake.setX((float) (flake.getX() + 21 * Math.random())));
        this.snowflakes.forEach(flake -> flake.setY(flake.getY() - 40));
        this.snowing = false;
        var fontSize = this.standardFont.getLineHeight() + 1;
        Globals.cursorY = Math.round(Globals.DEFAULT_CURSOR_Y - fontSize * WishesHelper.initialLoading.size());
    }

    @Override
    public void update(float delta) {
        long frame = Gdx.graphics.getFrameId();

        if (this.snowing) {
            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (snowing && frame > 400 + startIndex * 100) {
                    WishesHelper.snow(startIndex, this.snowflakes);
                }
            }
            var fontSize = this.standardFont.getLineHeight() + 1;
            Globals.cursorY = Math.round(Globals.DEFAULT_CURSOR_Y - fontSize * 15);
        }

        if (frame >= WishesHelper.KOLENDA_STARTING_FRAME) {
            this.snowing = true;
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();

        for (int i = 0; i < WishesHelper.initialLoading.size(); i++) {
            standardFont.draw(batch2, WishesHelper.initialLoading.get(i), 81, (float) (Globals.DEFAULT_CURSOR_Y - (i - 1) * 16));
        }

        if (this.snowing) {
            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (frame > 400 + startIndex * 100) {
                    for (int i = startIndex; i < snowflakes.size(); i += 7) {
                        snowflakes.get(i).draw(batch2);
                    }
                }
            }

            for (int i = 0; i < WishesHelper.running1.size(); i++) {
                float y = (Globals.DEFAULT_CURSOR_Y - (8 + i) * 16);
                standardFont.draw(batch2, WishesHelper.running1.get(i), 81, y);
            }
        }
        batch2.end();
    }
}
