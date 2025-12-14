package org.kd.wishesmount;

import com.badlogic.gdx.graphics.g2d.Sprite;
import org.kd.common.Globals;

import java.util.List;

public class WishesHelper {
    static void snow(int startIndex, List<Sprite> snowflakes) {
        for (int i = startIndex; i < snowflakes.size(); i += 7) {
            var flake = snowflakes.get(i);
            flake.setY(flake.getY() - 1);

            if (flake.getY() <= 0)
                flake.setY(Globals.SCREEN_WIDTH - 40 - i % 5);
        }
    }

    static List<String> initialLoading = List.of("LOAD \"SNOW\"", "", "PRESS PLAY ON TAPE", "OK", "", "SEARCHING FOR SNOW", "FOUND SNOW"
            , "LOADING", "READY.");
}
