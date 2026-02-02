package org.kd.game.common;

import org.kd.game.duel.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Roll {

    private int diceCount;
    private final List<Dice> diceList;
    private List<Integer> keptDiceList;

    public Roll(int diceCount) {
        this.diceCount = diceCount;

        this.diceList = new ArrayList<>(diceCount);
        this.firstRoll();
    }

    private void firstRoll() {
        for (int i = 0; i < diceCount; i++) {
            this.diceList.add(Dice.getRandom());
        }
    }

    public void reroll() {
        List<Dice> randomList = new ArrayList<>(diceCount);
        for (int i = 0; i < diceCount; i++) {
            var newDice = Dice.getRandom();
            randomList.add(newDice);
        }

        for (int i = 0; i < diceList.size(); i++) {
            if (!keptDiceList.contains(i)) {
                this.diceList.set(i, randomList.get(i));
            }
        }
    }

    public void keep(List<Integer> toBeKeptIndices) {
        keptDiceList = toBeKeptIndices.stream().filter(i -> i < diceCount).collect(Collectors.toList());
    }

    public List<Dice> getDiceList() {
        return diceList;
    }
}
