package org.kd.tricks.letters;

import java.util.Arrays;

public class LetterA extends Letter {
    public LetterA(int size) {
        super(size);
        for (int y = 0; y < 7; y++) {
            int finalY = y;
            Arrays.asList(0, 1, 5, 6).forEach(x -> this.pattern[finalY][x] = Field.FULL);
        }
        Arrays.asList(2, 3, 4).forEach(x -> {
            Arrays.asList(0, 1, 4).forEach(y -> this.pattern[y][x] = Field.FULL);
        });
        this.pattern[0][0] = Field.BOTTOM_RIGHT;
        this.pattern[6][6] = Field.TOP_LEFT;
    }
}
