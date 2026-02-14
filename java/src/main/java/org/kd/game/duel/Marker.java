package org.kd.game.duel;

public sealed abstract class Marker permits FameMarker, FearMarker {

    public int position = 0;

    public void moveUp(){
        position++;
    }

    public void moveDown(){
        position--;
    }
}
