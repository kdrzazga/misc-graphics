package org.kd.game.duel;

import org.junit.Assert;
import org.junit.Test;
import org.kd.game.common.Roll;

import java.util.ArrayList;
import java.util.List;

public class RollTest {

    @Test
    public void testReroll() {
        var r = new Roll(6);
        var diceList = new ArrayList<>(r.getDiceList());

        for (int i = 0; i < diceList.size(); i++) {
            System.out.print("  Dice " + i + " = " + diceList.get(i));
        }
        System.out.println();

        var d1 = diceList.get(1);
        var d2 = diceList.get(2);
        var d3 = diceList.get(3);
        r.keep(List.of(1,2,3));

        r.reroll();

        Assert.assertEquals(d1, diceList.get(1));
        Assert.assertEquals(d2, diceList.get(2));
        Assert.assertEquals(d3, diceList.get(3));

        for (int i = 0; i < r.getDiceList().size(); i++) {
            System.out.print("  Dice " + i + " = " + r.getDiceList().get(i));
        }

    }
}
