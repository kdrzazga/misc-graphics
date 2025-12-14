package org.kd.wishesmount;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.C64Helper;
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
                    WinterEffects.snow(startIndex, this.snowflakes);
                }
            }
            var fontSize = this.standardFont.getLineHeight() + 1;

            if (frame < WishesHelper.KOLENDA_STARTING_FRAME)
                Globals.cursorY = Math.round(Globals.DEFAULT_CURSOR_Y - fontSize * 15);
            else if (frame < WishesHelper.POKE_WHITE_ST_FRAME)
                Globals.cursorY = Math.round(Globals.DEFAULT_CURSOR_Y - fontSize * 14);
            else {
                Globals.cursorY = Math.round(Globals.DEFAULT_CURSOR_Y - fontSize * 18 + 3);
                this.borderColor = C64Colors.WHITE;
                Globals.CURSOR_COLOR = C64Colors.WHITE;
            }
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
            float y = (Globals.DEFAULT_CURSOR_Y - (i - 1) * 16);
            standardFont.draw(batch2, WishesHelper.initialLoading.get(i), 81, y);
        }

        if (this.snowing) {
            for (int i = 0; i < WishesHelper.startAppLines.size(); i++) {
                float y = (Globals.DEFAULT_CURSOR_Y - (8 + i) * 16);
                standardFont.draw(batch2, WishesHelper.startAppLines.get(i), 81, y);
            }

            if (frame >= WishesHelper.POKE_WHITE_ST_FRAME) {
                for (int i = 0; i < WishesHelper.changeColorsLine.size(); i++) {
                    float y = Globals.DEFAULT_CURSOR_Y - (13 + i) * 16;
                    standardFont.draw(batch2, WishesHelper.changeColorsLine.get(i), 81, y);
                }
                var whiteFont = C64Helper.createFont(15, "C64_Pro_Mono-STYLE.ttf");
                whiteFont.draw(batch2, "READY.", 81, Math.round(Globals.DEFAULT_CURSOR_Y - 16 * 16 + 2));
            }

            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (frame > 400 + startIndex * 100) {
                    for (int i = startIndex; i < snowflakes.size(); i += 7) {
                        snowflakes.get(i).draw(batch2);
                    }
                }
            }
        }
        batch2.end();
    }
}
