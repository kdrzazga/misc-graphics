package org.kd.tricks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public class StripeLettersScene extends Scene {

    private StripeLetters stripeLetters;

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batch;

    public StripeLettersScene() {
        super("stripe-letters");
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
    }

    @Override
    public void create() {
        stripeLetters = new StripeLetters("bkg/dzienDobry.png", 10, 300);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        stripeLetters.draw(shapeRenderer, batch);
    }

    @Override
    public void dispose() {

    }
}
