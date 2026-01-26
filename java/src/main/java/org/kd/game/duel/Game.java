package org.kd.game.duel;

import org.kd.game.common.Roll;

import java.util.stream.Collectors;

public class Game {

    public Monster currentPlayer;

    private final Board board;
    private final Monster godzilla, trex;

    private Roll roll;

    public Game() {
        board = new Board();

        godzilla = new Godzilla();
        trex = new TRex();

        currentPlayer = godzilla;

        roll = new Roll(6);
    }

    public void executeRoll() {
        var hits = roll.getDiceList().stream().filter(d -> d.equals(Dice.HIT)).count();
        getOppositePlayer().hp -= hits;

        var heals = roll.getDiceList().stream().filter(d -> d.equals(Dice.REGENERATE)).count();
        currentPlayer.hp += heals;

        var direction = currentPlayer.equals(godzilla) ? 1 : -1;

        var stars = roll.getDiceList().stream().filter(d -> d.equals(Dice.STAR_FAME)).count() - 2;
        board.fameMarker.position += direction * Math.max(0, stars);

        var demolish = roll.getDiceList().stream().filter(d -> d.equals(Dice.DEMOLISH_FEAR)).count() - 2;
        board.fearMarker.position += direction * Math.max(0, demolish);
    }

    public void printLog() {
        System.out.println("Godzilla HP = " + godzilla.hp + "T-Rex HP = " + trex.hp);
        System.out.println("Star/Fame marker pos = " + board.fameMarker.position + " Demolish/fear marker pos = "
                + board.fearMarker.position);
        System.out.println();
    }

    private Monster getOppositePlayer() {
        return currentPlayer.equals(godzilla) ? trex : godzilla;
    }
}
