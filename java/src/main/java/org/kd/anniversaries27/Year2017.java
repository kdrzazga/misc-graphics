package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import org.kd.anniversaries27.FittedPicture.Part;
import org.kd.common.C64Helper;

import java.util.List;

public final class Year2017 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final String picturesPath = "anniversaries27/pics/2017/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 2017.mp3.
    private final long priceCaptionFrame = seconds(6);
    private final long athCaptionFrame = seconds(8);
    private final long smartContractsFrame = seconds(11);
    private final long smartContractsFirstSectionFrame = seconds(13);
    private final long bitcoinCashFrame = seconds(18);
    private final long forkedChainFrame = seconds(20.5);
    private final long alphabetFrame = seconds(23);
    private final long iosFrame = seconds(26);
    private final long iosCaptionsFrame = seconds(28);
    private final long iosFirstDeviceFrame = seconds(30);
    private final long fortniteFrame = seconds(39);

    private final float calendarScrollSeconds = 5;
    private final long fadeInFrames = 15;
    private final long nextPartFrames = seconds(1);

    // Picture parts in picture pixels, origin top-left
    private final Part smartContractsHeader = new Part(0, 0, 1024, 165);
    private final List<Part> smartContractsSections = List.of(
            new Part(0, 165, 305, 524), new Part(305, 165, 223, 524),
            new Part(528, 165, 240, 524), new Part(768, 165, 256, 524));
    private final Part originalChain = new Part(0, 0, 1024, 132);
    private final Part forkedChain = new Part(0, 132, 1024, 196);
    private final Part iosHeader = new Part(0, 0, 600, 95);
    private final Part iosCaptions = new Part(0, 95, 600, 100);
    private final List<Part> iosDevices = List.of(
            new Part(0, 195, 183, 167), new Part(183, 195, 60, 167), new Part(243, 195, 110, 167),
            new Part(353, 195, 61, 167), new Part(414, 195, 186, 167));

    private final PaperCalendar calendar;
    private final BitmapFont priceFont, athFont;
    private final Color athGreen = new Color(0.2f, 0.9f, 0.3f, 1);
    private final FittedPicture smartContracts, bitcoinCash, alphabet, ios11;
    private final Sprite fortnite;

    Year2017(long startingFrame) {
        super("anniversaries27/2017.mp3", startingFrame);
        this.endFrame = startingFrame + 2637; // 2017.mp3 lasts 43.944 s at 60 fps

        calendar = new PaperCalendar(2017, new Rectangle(312, 148, 380, 470));
        priceFont = C64Helper.createFont(120, "Helvetica Regular.otf");
        athFont = C64Helper.createFont(140, "Helvetica Regular.otf");

        smartContracts = new FittedPicture(picturesPath + "smart-contracts.png", canvas, new Color(0f, 0.01f, 0.05f, 1));
        bitcoinCash = new FittedPicture(picturesPath + "bch.png", canvas, new Color(0.94f, 0.94f, 0.98f, 1));
        alphabet = new FittedPicture(picturesPath + "alphabet.jpg", canvas, new Color(1f, 1f, 1f, 1));
        ios11 = new FittedPicture(picturesPath + "ios11.jpg", canvas, new Color(0.98f, 0.98f, 0.98f, 1));

        var fortniteTexture = new Texture(picturesPath + "Fortnite.jpg");
        fortnite = new Sprite(fortniteTexture);
        fortnite.setScale(0.75f, 0.75f);
        fortnite.setPosition(-10, 27);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
        long relFrame = frame - this.startingFrame;

        if (relFrame < priceCaptionFrame)
            drawCalendar(batch, relFrame);
        else if (relFrame < smartContractsFrame)
            drawPriceCaptions(batch, relFrame);
        else if (relFrame < bitcoinCashFrame)
            drawSmartContracts(batch, relFrame);
        else if (relFrame < alphabetFrame)
            drawBitcoinCash(batch, relFrame);
        else if (relFrame < iosFrame)
            drawAlphabet(batch, relFrame);
        else if (relFrame < fortniteFrame)
            drawIos11(batch, relFrame);
        else
            drawFortnite(batch);

        log(relFrame);
        writeYear(batch);
    }

    /** Sheets scroll January to December, slowing down, then December stays. */
    private void drawCalendar(SpriteBatch batch, long relFrame) {
        float progress = Math.min(relFrame / 60f / calendarScrollSeconds, 1f);
        float easedOut = 1 - (float) Math.pow(1 - progress, 3);
        calendar.draw(batch, 11 * easedOut);
    }

    private void drawPriceCaptions(SpriteBatch batch, long relFrame) {
        batch.begin();
        priceFont.setColor(1, 1, 1, fadeIn(relFrame, priceCaptionFrame));
        drawCentredCaption(batch, priceFont, "$20'000", canvas.y + canvas.height * 3 / 4);

        if (relFrame >= athCaptionFrame) {
            athFont.setColor(athGreen.r, athGreen.g, athGreen.b, fadeIn(relFrame, athCaptionFrame));
            drawCentredCaption(batch, athFont, "ATH", canvas.y + canvas.height / 4);
        }
        batch.end();
    }

    private void drawCentredCaption(SpriteBatch batch, BitmapFont font, String caption, float centreY) {
        font.draw(batch, caption, canvas.x, centreY + font.getCapHeight() / 2, canvas.width, Align.center, false);
    }

    /** Header first, then the four sections one per second. */
    private void drawSmartContracts(SpriteBatch batch, long relFrame) {
        smartContracts.fillArea(shapeRenderer);
        batch.begin();
        smartContracts.drawPart(batch, smartContractsHeader, fadeIn(relFrame, smartContractsFrame));
        drawPartsOneBySecond(batch, smartContracts, smartContractsSections, smartContractsFirstSectionFrame, relFrame);
        batch.end();
    }

    /** Original chain first, then the forked chain completes the picture. */
    private void drawBitcoinCash(SpriteBatch batch, long relFrame) {
        bitcoinCash.fillArea(shapeRenderer);
        batch.begin();
        bitcoinCash.drawPart(batch, originalChain, fadeIn(relFrame, bitcoinCashFrame));
        if (relFrame >= forkedChainFrame)
            bitcoinCash.drawPart(batch, forkedChain, fadeIn(relFrame, forkedChainFrame));
        batch.end();
    }

    private void drawAlphabet(SpriteBatch batch, long relFrame) {
        alphabet.fillArea(shapeRenderer);
        batch.begin();
        alphabet.drawWhole(batch, fadeIn(relFrame, alphabetFrame));
        batch.end();
    }

    /** "iOS 11" header, then the iPhone/iPad captions, then the five devices one per second. */
    private void drawIos11(SpriteBatch batch, long relFrame) {
        ios11.fillArea(shapeRenderer);
        batch.begin();
        ios11.drawPart(batch, iosHeader, fadeIn(relFrame, iosFrame));
        if (relFrame >= iosCaptionsFrame)
            ios11.drawPart(batch, iosCaptions, fadeIn(relFrame, iosCaptionsFrame));
        drawPartsOneBySecond(batch, ios11, iosDevices, iosFirstDeviceFrame, relFrame);
        batch.end();
    }

    private void drawPartsOneBySecond(SpriteBatch batch, FittedPicture picture, List<Part> parts,
                                      long firstPartFrame, long relFrame) {
        for (int i = 0; i < parts.size(); i++) {
            long appearFrame = firstPartFrame + i * nextPartFrames;
            if (relFrame >= appearFrame)
                picture.drawPart(batch, parts.get(i), fadeIn(relFrame, appearFrame));
        }
    }

    private void drawFortnite(SpriteBatch batch) {
        batch.begin();
        fortnite.draw(batch);
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) fadeInFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == priceCaptionFrame) {
            System.out.print("BTC peak ");
        } else if (relFrame == smartContractsFrame) {
            System.out.print("ETH ");
        } else if (relFrame == bitcoinCashFrame) {
            System.out.print("BCH ");
        } else if (relFrame == alphabetFrame) {
            System.out.print("Alphabet ");
        } else if (relFrame == iosFrame) {
            System.out.print("Apple iOS ");
        } else if (relFrame == fortniteFrame)
            System.out.println("Fortnite");
    }
}
