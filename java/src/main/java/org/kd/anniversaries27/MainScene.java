package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import org.kd.win311.Scene4Paintbrush;

import java.util.Arrays;

public class MainScene extends Scene4Paintbrush {

    public final static int START_FRAME = Scene4Paintbrush.START_FRAME + 1000;
    public final static String ID = "anniversaries27";

    private Year2022 year2022;
    private Year2017 year2017;

    public MainScene() {
        super(ID);
        year2022 = new Year2022(START_FRAME + 2);
        year2017 = new Year2017(year2022.getEndFrame());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        super.render();

        var frame = Gdx.graphics.getFrameId();

        System.out.println(MainScene.class.getSimpleName() + " frame = " + frame + " relative frame = " );

        var allYears = Arrays.asList(this.year2022, this.year2017);

        allYears.forEach(year -> {
            if (frame == year.getStartingFrame()) year.draw(frame, this);
        });
    }
}
