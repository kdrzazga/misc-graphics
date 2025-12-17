package org.kd.xmas25;

import java.util.List;

public class WishesHelper {

    static final List<String> initialLoading = List.of("LOAD \"SNOW & MUSIC\"", "", "PRESS PLAY ON TAPE", "OK", "", "SEARCHING FOR SNOW & MUSIC", "FOUND SNOW & MUSIC"
            , "LOADING", "READY.");

    static final List<String> startAppLines = List.of("RUN", "", "Starting SNOW and MUSIC playback...", "", "READY.");
    static final List<String> changeColorsLine = List.of("POKE 53280, 1 : POKE 646, 1", "");
    static final String clearScreenLine ="POKE 53281, 14 : ? CHR$(147)";
    static final long KOLENDA_STARTING_FRAME = 400;
    static final long POKE_WHITE_ST_FRAME = KOLENDA_STARTING_FRAME + 750;
    static final long CLEAR_SCREEN_FRAME = POKE_WHITE_ST_FRAME + 1500;
    static final long ROCK_MUSIC_START_FRAME = 1900;
    static final long SCENE2_START_FRAME = 2800;


}
