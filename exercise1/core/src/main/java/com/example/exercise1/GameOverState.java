package com.example.exercise1;

import static com.example.exercise1.Game.WORLD_HEIGHT;
import static com.example.exercise1.Game.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState implements GameState {

    private final Game game;
    private final SpriteBatch batch;
    private final Paddle paddle1, paddle2;
    private final Ball ball;
    private final BitmapFont font;

    public GameOverState(Game game) {
        this.game = game;
        this.batch = game.getBatch();
        this.paddle1 = game.getPaddle1();
        this.paddle2 = game.getPaddle2();
        this.font = game.getFont();
        this.ball = game.getBall();
    }

    @Override
    public void handleInput(Input input) {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            resetGame();
        }

        batch.begin();

        paddle1.draw(batch);
        paddle2.draw(batch);
            font.draw(batch, game.victoryString(), WORLD_WIDTH / 3, WORLD_HEIGHT / 2);

        batch.end();
    }

    public void resetGame() {
        game.setPlayingState();
        ball.setBallActive(true);
    }

}
