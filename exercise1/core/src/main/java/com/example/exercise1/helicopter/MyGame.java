/*This is the code of Task 1 - you can copy it into the MyGame class and run the code*/


package com.example.exercise1.helicopter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGame extends ApplicationAdapter {

    // Setting a game size. This is not needed for the task but nice to know.
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    // Sprites
    private SpriteBatch batch;
    private Helicopter h1, h2;

    // View
    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();

        h1 = new Helicopter(120f, 380f);
        h2 = new Helicopter(120f, 180f);

        h1.setPosition(140, 210);
        h1.setPosition(300, 50);

        // Defining and setting camera position
        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

        // Getting the time and moving the helicopter based on the time
        float dt = Gdx.graphics.getDeltaTime();
        h1.animateFrames(dt);
        h2.animateFrames(dt);

        h1.bounceOff(h2);

        h1.checkDirection(WORLD_WIDTH, WORLD_HEIGHT, dt);
        h2.checkDirection(WORLD_WIDTH, WORLD_HEIGHT, dt);

        batch.begin();
        h1.draw(batch);
        h2.draw(batch);
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
        h1.dispose();
        h2.dispose();
    }
}
