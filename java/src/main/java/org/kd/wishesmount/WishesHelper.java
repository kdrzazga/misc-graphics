package org.kd.wishesmount;

import java.util.List;

public class WishesHelper {

    static final List<String> initialLoading = List.of("LOAD \"SNOW & MUSIC\"", "", "PRESS PLAY ON TAPE", "OK", "", "SEARCHING FOR SNOW & MUSIC", "FOUND SNOW & MUSIC"
            , "LOADING", "READY.");

    static final List<String> startAppLines = List.of("RUN", "", "Staring SNOW and MUSIC playback...", "", "READY.");
    static final List<String> changeColorsLine = List.of("POKE 53280, 1 : POKE 646, 1", "");
    static final long KOLENDA_STARTING_FRAME = 400;
    static final long POKE_WHITE_ST_FRAME = KOLENDA_STARTING_FRAME + 750;
    static final long CLEAR_SCREEN_FRAME = POKE_WHITE_ST_FRAME + 750;


}
