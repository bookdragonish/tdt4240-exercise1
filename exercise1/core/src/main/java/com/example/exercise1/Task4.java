package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
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

    private Sprite ball;
    private Sprite paddle1, paddle2;

    Texture ballImage;
    Texture paddleImage;

    @Override
    public void create() {
        batch = new SpriteBatch();

        ballImage = new Texture("pingpongball.png");
        ball = new Sprite(ballImage);

        paddleImage = new Texture("line.png");
        paddle1 = new Sprite(paddleImage);
        paddle2 = new Sprite(paddleImage);

        ball.setPosition((WORLD_WIDTH-ball.getWidth())/2, 0);
        paddle1.setPosition(180, 60);
        paddle2.setPosition(333, 160);

        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

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
        ballImage.dispose();
        paddleImage.dispose();
    }
}
