package org.kd.game.duel;

public class Board {

    final Marker fameMarker, fearMarker;

    public Board() {
        fameMarker = new FameMarker();
        fearMarker = new FearMarker();
    }
}
