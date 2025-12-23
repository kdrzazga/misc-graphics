package org.kd.org.kd.tricks.letters;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.PrintStream;
import java.util.Map;


public class Letter {
    private int size;
    protected Field[][] pattern;

    public Letter(int size) {
        this.size = size;
        pattern = new Field[8][8];
        for (int i = 0; i < pattern.length; i++)
            for (int j = 0; j < pattern[0].length; j++) {
                var field = Field.EMPTY;
                field.setSize(size);
                pattern[i][j] = Field.EMPTY;
            }
    }

    public void draw(ShapeRenderer shapeRenderer, float letterX, float letterY) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Field[] fields : pattern)
            for (int j = 0; j < pattern[0].length; j++) {
                var field = fields[j];
                field.getPoints().forEach(point -> {
                    var x1 = letterX + 2 * point.getX();
                    var y1 = letterX + 2 * point.getY();

                    shapeRenderer.rect(x1, y1, 1, 1);
                });
            }

        shapeRenderer.end();
    }

    public void print() {
        PrintStream o = System.out;

        Map<Field, Character> map = Map.of(
                Field.EMPTY, '.'
                , Field.FULL, '#'
                , Field.BOTTOM_LEFT, '\\'
                , Field.TOP_LEFT, '/'
                , Field.BOTTOM_RIGHT, '/'
                , Field.TOP_RIGHT, '\\'
        );

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                o.print(map.get(pattern[i][j]));
            }
            o.println();
        }
    }

    public Field[][] getPattern() {
        return pattern;
    }
}

