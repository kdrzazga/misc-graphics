package org.kd.game.duel;

public sealed class Monster permits Godzilla, TRex {

    protected int hp;
    final protected int maxHp;
    public int energy;

    Monster(int maxHp) {
        energy = 1;
        hp = maxHp;
        this.maxHp = maxHp;
    }
}
