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

    static List<String> initialLoading = List.of("LOAD \"SNOW & MUSIC\"", "", "PRESS PLAY ON TAPE", "OK", "", "SEARCHING FOR SNOW & MUSIC", "FOUND SNOW & MUSIC"
            , "LOADING", "READY.");

    static List<String> running1 = List.of("RUN", "", "Staring SNOW and MUSIC playback...", "", "READY.");
    static List<String> running2 = List.of("POKE 53280, 1 : POKE 646, 1", "");
    static final long KOLENDA_STARTING_FRAME = 400;
    static final long POKE_WHITE_ST_FRAME = KOLENDA_STARTING_FRAME + 750;
}
