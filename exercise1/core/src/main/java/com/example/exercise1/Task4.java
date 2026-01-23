package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Task4 extends ApplicationAdapter {
    public static final float WORLD_WIDTH = 800;
    public static final float WORLD_HEIGHT = 480;

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Ball ball;
    private Paddle paddle1, paddle2;
    private int pointPlayer1, pointPlayer2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        ball = new Ball();

        paddle1 = new Paddle(5, 200, 3f);
        paddle2 = new Paddle(5, 200, 3f);

        pointPlayer1 = 0;
        pointPlayer2 = 0;

        ball.setPosition((WORLD_WIDTH-ball.getWidth())/2, (WORLD_HEIGHT-ball.getHeight())/2);

        paddle1.setPosition(WORLD_WIDTH-25, (WORLD_HEIGHT-200)/2);
        paddle2.setPosition(20, (WORLD_HEIGHT-200)/2);

        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

        // Moving ball
        float dt = Gdx.graphics.getDeltaTime();
        ball.moveBall(WORLD_WIDTH, WORLD_HEIGHT, dt);

        // Moving the paddles
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) paddle1.paddleDirection(1, WORLD_HEIGHT);;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) paddle1.paddleDirection(-1, WORLD_HEIGHT);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) paddle2.paddleDirection(1, WORLD_HEIGHT);;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) paddle2.paddleDirection(-1, WORLD_HEIGHT);;

        // Check for collision
        Rectangle boundsPaddle1 = paddle1.getBounds();
        Rectangle boundsPaddle2 = paddle2.getBounds();
        Rectangle boundsBall = ball.getBounds();

        if (boundsPaddle1.overlaps(boundsBall)) { System.out.println("Collision! 1");}
        else if (boundsPaddle2.overlaps(boundsBall)) {  System.out.println("Collision! 2");}


        batch.begin();
        ball.draw(batch);
        font.draw(batch, "Player 1: " + pointPlayer1, 20, WORLD_HEIGHT-20);
        font.draw(batch, "Player 2: " + pointPlayer2, WORLD_WIDTH/2, WORLD_HEIGHT-20);
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
        font.dispose();

    }
}
