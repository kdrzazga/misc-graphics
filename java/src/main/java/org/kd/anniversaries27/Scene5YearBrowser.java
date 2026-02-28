package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import org.kd.win311.Scene4Paintbrush;

import java.util.Arrays;
import java.util.List;

public class Scene5YearBrowser extends Scene4Paintbrush {

    public final static int START_FRAME = Scene4Paintbrush.START_FRAME + 1000;
    public final static String ID = "anniversaries27";

    private final Year2022 year2022;
    private final Year2017 year2017;
    private final Year2012 year2012;
    private final Year2007 year2007;
    private final Year2002 year2002;
    private final Year1997 year1997;
    private final Year1992 year1992;
    private final Year1987 year1987;
    private final Year1982 year1982;
    private final Year1977 year1977;
    private final Year1972 year1972;
    private final Year1967 year1967;
    private final Year1962 year1962;
    private final Year1957 year1957;
    private final Year1952 year1952;

    public Scene5YearBrowser() {
        super(ID);
        year2022 = new Year2022(START_FRAME + 3);
        year2017 = new Year2017(year2022.getEndFrame());
        year2012 = new Year2012(year2017.getEndFrame());
        year2007 = new Year2007(year2012.getEndFrame());
        year2002 = new Year2002(year2007.getEndFrame());
        year1997 = new Year1997(year2002.getEndFrame());
        year1992 = new Year1992(year1997.getEndFrame());
        year1987 = new Year1987(year1992.getEndFrame());
        year1982 = new Year1982(year1987.getEndFrame());
        year1977 = new Year1977(year1982.getEndFrame());
        year1972 = new Year1972(year1977.getEndFrame());
        year1967 = new Year1967(year1972.getEndFrame());
        year1962 = new Year1962(year1967.getEndFrame());
        year1957 = new Year1957(year1962.getEndFrame());
        year1952 = new Year1952(year1957.getEndFrame());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        super.render();

        var frame = Gdx.graphics.getFrameId();
        System.out.println(Scene5YearBrowser.class.getSimpleName() + " frame = " + frame);

        for (Year year : getAllYears()) {
            if (frame == year.getStartingFrame()) {
                year.draw(frame, this);
                break;
            }
        }
    }

    public List<Year> getAllYears(){
        return Arrays.asList(this.year2022, this.year2017, this.year2012, this.year2007, this.year2002,
                year1997, year1992, year1987, year1982, year1977, year1972, year1967, year1962, year1957
                , year1952);
    }
}
