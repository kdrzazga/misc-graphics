package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import org.kd.common.C64Helper;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Wall calendar whose monthly sheets scroll upwards through a fixed frame.
 * scrollPosition 0 shows January, 11 shows December; fractions show two sheets in between.
 */
final class PaperCalendar {

    private final int year;
    private final Rectangle page;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont monthFont, yearFont, dayFont;

    private final float headerHeight = 110;
    private final float gridMargin = 20;
    private final float rowHeight = 50;
    private final String[] weekdays = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
    private final Color paper = new Color(0.97f, 0.96f, 0.92f, 1);
    private final Color headerRed = new Color(0.78f, 0.1f, 0.1f, 1);
    private final Color sundayRed = new Color(0.8f, 0.1f, 0.1f, 1);
    private final Color ink = new Color(0.1f, 0.1f, 0.1f, 1);
    private final Color shadow = new Color(0.25f, 0.25f, 0.25f, 1);
    private final Color ring = new Color(0.35f, 0.35f, 0.38f, 1);

    PaperCalendar(int year, Rectangle page) {
        this.year = year;
        this.page = page;
        this.shapeRenderer = new ShapeRenderer();
        this.monthFont = C64Helper.createFont(44, "Helvetica Regular.otf");
        this.yearFont = C64Helper.createFont(24, "Helvetica Regular.otf");
        this.dayFont = C64Helper.createFont(24, "Helvetica Regular.otf");
    }

    void draw(SpriteBatch batch, float scrollPosition) {
        drawShadow();

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        HdpiUtils.glScissor((int) page.x, (int) page.y, (int) page.width, (int) page.height);

        int upperMonthIndex = (int) Math.floor(scrollPosition);
        float scrolledFraction = scrollPosition - upperMonthIndex;
        float upperSheetBottom = page.y + scrolledFraction * page.height;
        drawSheet(batch, upperMonthIndex, upperSheetBottom);
        if (upperMonthIndex < 11)
            drawSheet(batch, upperMonthIndex + 1, upperSheetBottom - page.height);

        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);

        drawRings();
    }

    private void drawShadow() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(shadow);
        shapeRenderer.rect(page.x + 8, page.y - 8, page.width, page.height);
        shapeRenderer.end();
    }

    private void drawRings() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(ring);
        int ringCount = 9;
        float spacing = page.width / (ringCount + 1);
        for (int i = 1; i <= ringCount; i++)
            shapeRenderer.rect(page.x + i * spacing - 4, page.y + page.height - 14, 8, 28);
        shapeRenderer.end();
    }

    private void drawSheet(SpriteBatch batch, int monthIndex, float bottom) {
        float top = bottom + page.height;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(paper);
        shapeRenderer.rect(page.x, bottom, page.width, page.height);
        shapeRenderer.setColor(headerRed);
        shapeRenderer.rect(page.x, top - headerHeight, page.width, headerHeight);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(shadow);
        shapeRenderer.line(page.x, bottom, page.x + page.width, bottom);
        shapeRenderer.end();

        var firstDay = LocalDate.of(year, monthIndex + 1, 1);
        var monthName = firstDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
        float cellWidth = (page.width - 2 * gridMargin) / 7;
        float weekdayRowY = top - headerHeight - 22;

        batch.begin();
        monthFont.setColor(Color.WHITE);
        monthFont.draw(batch, monthName, page.x, top - 22, page.width, Align.center, false);
        yearFont.setColor(Color.WHITE);
        yearFont.draw(batch, String.valueOf(year), page.x, top - 76, page.width, Align.center, false);

        for (int column = 0; column < 7; column++) {
            dayFont.setColor(column == 6 ? sundayRed : ink);
            dayFont.draw(batch, weekdays[column], page.x + gridMargin + column * cellWidth, weekdayRowY,
                    cellWidth, Align.center, false);
        }

        int firstColumn = firstDay.getDayOfWeek().getValue() - 1; // Monday = 0
        for (int day = 1; day <= firstDay.lengthOfMonth(); day++) {
            int cellIndex = firstColumn + day - 1;
            int row = cellIndex / 7;
            int column = cellIndex % 7;
            dayFont.setColor(column == 6 ? sundayRed : ink);
            dayFont.draw(batch, String.valueOf(day), page.x + gridMargin + column * cellWidth,
                    weekdayRowY - (row + 1) * rowHeight, cellWidth, Align.center, false);
        }
        batch.end();
    }
}
