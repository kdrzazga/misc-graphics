package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TwoMovingBackgrounds {

    private TravellingLogo rearBackground;
    private TravellingLogo frontBackground;
    private float screenWidth;
    private float screenHeight;

    public TwoMovingBackgrounds(String rearBackgroundPath, String frontBackgroundPath, float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        var rearBackgroundTxt = new Texture(rearBackgroundPath);
        var frontBackgroundTxt = new Texture(frontBackgroundPath);

        this.rearBackground = new TravellingLogo(rearBackgroundTxt, screenWidth, 0, rearBackgroundTxt.getWidth()*2f
                , rearBackgroundTxt.getHeight());
        this.frontBackground = new TravellingLogo(frontBackgroundTxt, screenWidth, 80, frontBackgroundTxt.getWidth()
                , frontBackgroundTxt.getHeight());
        this.rearBackground.spriteSpeed = 43f;
        this.frontBackground.spriteSpeed = 12f;
    }

    public void update() {
        var delta = Gdx.graphics.getDeltaTime();
        var frame = Gdx.graphics.getFrameId();

        this.rearBackground.move(delta, Math.round(screenWidth));
        this.frontBackground.move(delta, Math.round(screenWidth));
        var y = (float) (80 + 30 * Math.sin(frame/Math.PI/100));
        System.out.println("y " + y);
        this.frontBackground.sprite.setY(y);
    }

    public void draw(SpriteBatch batch) {
        this.rearBackground.draw(batch, Math.round(screenWidth), Math.round(screenHeight));
        this.frontBackground.draw(batch, Math.round(screenWidth), Math.round(screenHeight));
    }
}
