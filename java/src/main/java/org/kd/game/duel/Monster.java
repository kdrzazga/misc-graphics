package org.kd.game.duel;

public class Monster {

    protected int hp;
    final protected int maxHp;
    public int energy;

    Monster(int maxHp) {
        energy = 1;
        hp = maxHp;
        this.maxHp = maxHp;
    }
}
