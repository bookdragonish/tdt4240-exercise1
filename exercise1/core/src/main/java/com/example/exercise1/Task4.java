package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Task4 extends ApplicationAdapter {
    public static final float WORLD_WIDTH = 800;
    public static final float WORLD_HEIGHT = 480;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Ball ball;
    private Paddle paddle1, paddle2;

    @Override
    public void create() {
        batch = new SpriteBatch();

        ball = new Ball();

        paddle1 = new Paddle(5, 200);
        paddle2 = new Paddle(5, 200);

        ball.setPosition((WORLD_WIDTH-ball.getWidth())/2, (WORLD_HEIGHT-ball.getHeight())/2);

        paddle1.setPosition(WORLD_WIDTH-25, (WORLD_HEIGHT-200)/2);
        paddle2.setPosition(20, (WORLD_HEIGHT-200)/2);
//        paddle2.setPosition(0, 0);
//        paddle2.setSize(800, 480);

        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

        float dt = Gdx.graphics.getDeltaTime();
        ball.moveBall(WORLD_WIDTH, WORLD_HEIGHT, dt);

        batch.begin();
        ball.draw(batch);
        paddle1.draw(batch);
        paddle2.draw(batch);
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
        ball.dispose();
        paddle1.dispose();
        paddle2.dispose();
    }
}
