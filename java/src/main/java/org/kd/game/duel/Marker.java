package org.kd.game.duel;

public abstract class Marker {

    public int position = 0;

    public void moveUp(){
        position++;
    }

    public void moveDown(){
        position--;
    }
}
