package org.kd.game.duel;

import org.kd.game.common.Diceable;

public enum Dice implements Diceable {
    STAR_FAME, DEMOLISH_FEAR, EXCLAMATION, HIT, REGENERATE, ENERGY;

    public static Dice getRandom() {

        int amount = Dice.values().length;

        var index = (int) (Math.random() * amount);
        return Dice.values()[index];
    }
}
