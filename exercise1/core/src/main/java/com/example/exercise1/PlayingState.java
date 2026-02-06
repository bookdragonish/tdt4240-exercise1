package com.example.exercise1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import static com.example.exercise1.Game.WORLD_HEIGHT;
import static com.example.exercise1.Game.WORLD_WIDTH;

public class PlayingState implements GameState {
    private final Game game;
    private final SpriteBatch batch;
    private final Paddle paddle1, paddle2;
    private final Ball ball;
    private final BitmapFont font;

    public PlayingState(Game game) {
        this.game = game;
        this.batch = game.getBatch();
        this.paddle1 = game.getPaddle1();
        this.paddle2 = game.getPaddle2();
        this.font = game.getFont();
        this.ball = game.getBall();
    }

    @Override
    public void render() {
        // Moving ball
        float dt = Gdx.graphics.getDeltaTime();

        if (ball.getBallActive()) {
            ball.moveBall(WORLD_WIDTH, WORLD_HEIGHT, dt);
            // Check for collision
            Rectangle boundsPaddle1 = paddle1.getBounds();
            Rectangle boundsPaddle2 = paddle2.getBounds();
            Rectangle boundsBall = ball.getBounds();

            if (boundsPaddle1.overlaps(boundsBall)) {
                System.out.println("Collision! 1");
                ball.switchDirection();
            } else if (boundsPaddle2.overlaps(boundsBall)) {
                System.out.println("Collision! 2");
                ball.switchDirection();
            }

            game.handlePoints();


            // Moving the paddles
            if (Gdx.input.isKeyPressed(Input.Keys.W)) paddle1.paddleDirection(1, WORLD_HEIGHT);
            ;
            if (Gdx.input.isKeyPressed(Input.Keys.S)) paddle1.paddleDirection(-1, WORLD_HEIGHT);

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) paddle2.paddleDirection(1, WORLD_HEIGHT);
            ;
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) paddle2.paddleDirection(-1, WORLD_HEIGHT);
            ;

            batch.begin();
            ball.draw(batch);

            font.draw(batch, "Player 1: " + game.getGameManager().getScorePlayer1(), WORLD_WIDTH / 4, WORLD_HEIGHT - 20);
            font.draw(batch, "Player 2: " + game.getGameManager().getScorePlayer2(), WORLD_WIDTH / 2, WORLD_HEIGHT - 20);

            paddle1.draw(batch);
            paddle2.draw(batch);
            batch.end();
        }
    }
}
