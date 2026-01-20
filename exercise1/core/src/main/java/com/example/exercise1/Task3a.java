/*This is the code of Task 1 - you can copy it into the MyGame class and run the code*/


package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Task3a extends ApplicationAdapter {

    // Setting a game size. This is not needed for the task but nice to know.
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    // pixels to move each second
    private float velX = 120f;
    private  float velY = 180f;

    // Sprites
    private SpriteBatch batch;
    private Sprite helicopter;

    // Animations
    private Texture heli1, heli2, heli3, heli4;
    private Animation<TextureRegion> heliAnimation;
    private float stateTime;

    // View
    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();

        heli1 = new Texture("heli1.png");
        heli2 = new Texture("heli2.png");
        heli3 = new Texture("heli3.png");
        heli4 = new Texture("heli4.png");

        Array<TextureRegion> frames = new Array<>(); // Adds all frames to display on loop
        frames.add(new TextureRegion(heli1));
        frames.add(new TextureRegion(heli2));
        frames.add(new TextureRegion(heli3));
        frames.add(new TextureRegion(heli4));

        heliAnimation = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
        stateTime = 0f;

        helicopter = new Sprite(frames.first());
        helicopter.setPosition(140, 210);

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
        stateTime += dt;

        // Correct frame for current time
        TextureRegion currentFrame = heliAnimation.getKeyFrame(stateTime);
        helicopter.setRegion(currentFrame);

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
        heli1.dispose();
        heli2.dispose();
        heli3.dispose();
        heli4.dispose();
    }
}
