package org.kd.dream21ordie;

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
        this.snowing = true;
    }

    @Override
    public void update(float delta) {
        long frame = Gdx.graphics.getFrameId();
        if (this.snowing)
            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (frame > 100 + startIndex * 100) {
                    for (int i = startIndex; i < snowflakes.size(); i += 7) {
                        var flake = snowflakes.get(i);
                        flake.setY(flake.getY() - 1);

                        if (flake.getY() <= 0)
                            flake.setY(Globals.SCREEN_WIDTH - 40 - i % 5);
                    }
                }
            }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        for (int startIndex = 0; startIndex <= 6; startIndex++) {
            if (frame > 1100 + startIndex * 100) {
                for (int i = startIndex; i < snowflakes.size(); i += 7) {
                    snowflakes.get(i).draw(batch2);
                }
            }
        }
        batch2.end();
    }
}
