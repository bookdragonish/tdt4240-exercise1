package com.example.exercise1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    private final Sprite ball;

    Texture ballImage;

    private float velX = 180f;
    private  float velY = 180f;

    private boolean ballActive;

    public Ball() {
        ballImage = new Texture("pingpongball.png");

        ball = new Sprite(ballImage);

        ball.setSize(20, 20);

        ballActive = true;
    }

    public void moveBall(float WORLD_WIDTH, float WORLD_HEIGHT, float dt) {
        // Ball coordinates
        float coordinateY = ball.getY();

        if (coordinateY >= (WORLD_HEIGHT - ball.getHeight()) || coordinateY <= 0) {
            velY = -velY;
        }
        ball.translate(velX * dt, velY * dt);

    }

    public void setPosition(float x, float y) {
        ball.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        ball.draw(batch);
    }

    public Rectangle getBounds(){
        return this.ball.getBoundingRectangle();
    }

    public float getWidth() {
        return ball.getWidth();
    }

    public float getHeight() {
        return ball.getHeight();
    }

    public float getX() {
        return ball.getX();
    }

    public void switchDirection() {
        this.velX = -velX;
    }

    public void dispose() {
        ballImage.dispose();
    }

    public boolean getBallActive() {
        return ballActive;
    }

    public void setBallActive(boolean ballActive) {
        this.ballActive = ballActive;
    }
}
