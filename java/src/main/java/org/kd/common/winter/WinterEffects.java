package org.kd.common.winter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.kd.common.Globals;

import java.util.ArrayList;
import java.util.List;

public class WinterEffects {

    public static List<Sprite> createSnowflakeSprites() {
        var snowflakes = new ArrayList<Sprite>();
        var flakeTexture = new Texture("asterisk.png");
        for (int i = 0; i < 77; i++) {
            var sprite = new Sprite(flakeTexture);
            sprite.setScale(0.6f, 0.6f);
            sprite.setPosition(35 + 14 * i, (float) (Globals.SCREEN_HEIGHT - 24 * Math.sin(i / Math.PI)) + i % 5);
            snowflakes.add(sprite);
        }

        return snowflakes;
    }

    public static void snow(int startIndex, List<Sprite> snowflakes) {
        for (int i = startIndex; i < snowflakes.size(); i += 7) {
            var flake = snowflakes.get(i);
            flake.setY(flake.getY() - 1);

            if (flake.getY() <= 0)
                flake.setY(Globals.SCREEN_WIDTH - 40 - i % 5);
        }

    }
}
