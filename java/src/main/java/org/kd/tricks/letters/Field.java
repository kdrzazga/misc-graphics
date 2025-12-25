package org.kd.tricks.letters;

import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.List;

public enum Field {
    BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT, FULL, EMPTY;

    public int size;

    Field(){
        this.size = 8;
    }

    public List<Point> getPoints() {
        var result = new ArrayList<Point>(size * size);
        switch (this) {
            case FULL:
                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++)
                        result.add(new Point(x, y));
                return result;
            case BOTTOM_LEFT:
                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        if (y >= x)
                            result.add(new Point(x, y));
                    }
                return result;
            case TOP_RIGHT:
                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        if (y <= x)
                            result.add(new Point(x, y));
                    }
                return result;
            case TOP_LEFT:
                for (int x = size; x > 0; x--)
                    for (int y = 0; y < size; y++) {
                        if (y <= size - x)
                            result.add(new Point(x, y));
                    }
                return result;
            case BOTTOM_RIGHT:
                for (int x = size; x > 0; x--)
                    for (int y = 0; y < size; y++) {
                        if (y >= size - x)
                            result.add(new Point(x, y));
                    }
                return result;
        }
        return result; //case EMPTY:
    }

    public Field setSize(int size){
        this.size = size;
        return this;
    }
}
