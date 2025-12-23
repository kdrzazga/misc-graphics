package org.kd.test;

import org.junit.Test;
import org.kd.org.kd.tricks.letters.LetterO;

public class FieldTest {

    @Test
    public void testField() {
        var o = new LetterO(12);
        var fieldTriangle = o.getPattern()[0][0];
        fieldTriangle.getPoints().forEach(System.out::println);
    }
}
