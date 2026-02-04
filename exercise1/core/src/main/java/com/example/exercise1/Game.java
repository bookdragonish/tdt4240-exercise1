package com.example.exercise1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
    public static final float WORLD_WIDTH = 800;
    public static final float WORLD_HEIGHT = 480;

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private Viewport viewport;

    private GameManager gameManager;
    private Ball ball;
    private Paddle paddle1, paddle2;
    private GameState currentState;

    final int victoryScore = 2;

    public void create() {
        currentState = new PlayingState(this);
        batch = new SpriteBatch();
        font = new BitmapFont();

        gameManager = GameManager.getInstance();

        ball = new Ball();

        paddle1 = new Paddle(5, 200, 3f);
        paddle2 = new Paddle(5, 200, 3f);

        ball.setPosition((WORLD_WIDTH-ball.getWidth())/2, (WORLD_HEIGHT-ball.getHeight())/2);

        paddle1.setPosition(20, (WORLD_HEIGHT-200)/2);
        paddle2.setPosition(WORLD_WIDTH-25, (WORLD_HEIGHT-200)/2);

        camera = new OrthographicCamera(); // Camera with no perspective for 2D
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera); // Handles the scaling on different devices
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0); // 2f puts the camera in center using float
    }

    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined); // sets origo to be bottom left of world not screen

        currentState.render();
    }

    public void dispose() {
        batch.dispose();
        ball.dispose();
        paddle1.dispose();
        paddle2.dispose();
        font.dispose();
    }

    public void handlePoints() {
        if (ball.getX() >= (WORLD_WIDTH - ball.getWidth()) || ball.getX() <= 0) {
            if (ball.getX() >= (WORLD_WIDTH - ball.getWidth())) {
                gameManager.addScorePlayer1();
                if (gameManager.getScorePlayer1() == victoryScore) {
                    currentState = new GameOverState(this);
                    ball.setBallActive(false);
                }
            }
            else {
                gameManager.addScorePlayer2();
                if (gameManager.getScorePlayer2() == victoryScore) {
                    currentState = new GameOverState(this);
                    ball.setBallActive(false);
                }
            }
            ball.setPosition((WORLD_WIDTH-ball.getWidth())/2, (WORLD_HEIGHT-ball.getHeight())/2);
            ball.switchDirection();
        }
    }

    public String victoryString() {
        if (gameManager.getScorePlayer1() == victoryScore) {
            return "Player 1 has won, press R to restart";
        }
        if (gameManager.getScorePlayer2() == victoryScore) {
            return "Player 2 has won, press R to restart";
        }
        return "";
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Paddle getPaddle2() {
        return paddle2;
    }

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Ball getBall() {
        return ball;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setGameOverState() {
        currentState = new GameOverState(this);
    }

    public void setPlayingState() {
        currentState = new PlayingState(this);
        gameManager.resetScores();
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
