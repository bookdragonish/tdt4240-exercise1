package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Task2 extends ApplicationAdapter {

    // Setting a game size. This is not needed for the task but nice to know.
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    private SpriteBatch batch;
    private Sprite helicopter;
    private Texture helicopterTexture;

    private BitmapFont font;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector2 touchPos;

    @Override
    public void create() {
        batch = new SpriteBatch();
        helicopterTexture = new Texture("heli1.png");
        helicopter = new Sprite(helicopterTexture);

        // Add font with size and color
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1);        // white
        font.getData().setScale(2f);

        // Defining and setting camera position
        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
        touchPos = new Vector2();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

        // If screen is touched
        if(Gdx.input.isTouched()){

            float oldX = helicopter.getX(); // Store old X value to determine flipping the helicopter

            touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Store the touch coordinate in a vector

            // screen coordinates -> world coordinates
            viewport.unproject(touchPos); // touchPos use screen pixels, helicopter use world coordinates.

            helicopter.setCenter(touchPos.x, touchPos.y);// Helicopter position refers to bottom-left corner, this LibGDX gives us center

            // Check direction of helicopter using old and new coordinates
            float newX = helicopter.getX();

            if (newX < oldX) helicopter.setFlip(false, false);   // face left
            else if (newX > oldX) helicopter.setFlip(true, false); // face right

            // Clamp so it does not go outside
            float minX = 0f;
            float maxX = WORLD_WIDTH - helicopter.getWidth(); // helicopter cannot go past 416
            float minY = 0f;
            float maxY = WORLD_HEIGHT - helicopter.getHeight();

            helicopter.setX(Math.min(maxX, Math.max(minX, helicopter.getX()))); // Force X to between minX maxX
            helicopter.setY(Math.min(maxY, Math.max(minY, helicopter.getY()))); // Force Y to be between minY maxY

        }

        // Coordinates for helicopter
        float heliX = helicopter.getX();
        float heliY = helicopter.getY();

        String text = "("+ Math.floor(heliX) + " , "+  Math.floor(heliY) + ")";

        batch.begin();
        helicopter.draw(batch);
        font.draw(batch, text, 20, WORLD_HEIGHT-20);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // This keeps your game scaling correctly if the window size changes
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        helicopterTexture.dispose();
        font.dispose();
    }
}
