/*This is the code of Task 1 - you can copy it into the MyGame class and run the code*/


package com.example.exercise1.helicopter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Task1 extends ApplicationAdapter {

    // Setting a game size. This is not needed for the task but nice to know.
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    // pixels to move each second
    private float velX = 120f;
    private  float velY = 180f;

    private SpriteBatch batch;
    private Sprite helicopter;
    private Texture helicopterTexture;

    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        helicopterTexture = new Texture("heli1.png");
        helicopter = new Sprite(helicopterTexture);

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

        // Helicopter coordinates
        float coordinateX = helicopter.getX();
        float coordinateY = helicopter.getY();

        // Changes direction on speed if the helicopter get out of bounds
        if(coordinateX >= (WORLD_WIDTH-helicopter.getWidth()) || coordinateX <= 0){
            velX = -velX;
        }
        if (coordinateY >= (WORLD_HEIGHT-helicopter.getHeight()) || coordinateY <= 0){
            velY = -velY;
        }
        helicopter.translate(velX * dt, velY * dt);

        // Direction of helicopter:
        if (velX < 0) {
            helicopter.setFlip(false, false);   // face left
        } else {
            helicopter.setFlip(true, false);  // face right
        }

        batch.begin();
        helicopter.draw(batch);
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
    }
}
