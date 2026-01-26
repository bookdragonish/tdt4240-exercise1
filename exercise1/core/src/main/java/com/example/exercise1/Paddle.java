package com.example.exercise1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    final Sprite paddle;

    Texture paddleImage;

    float speed;

    public Paddle(float width, float height, float speed) {
        this.speed = speed;
        paddleImage = new Texture("line.png");
        paddle = new Sprite(paddleImage);

        paddle.setSize(width, height);
    }

    public void paddleDirection(int direction, float worldHeight) {
        // Calculate the y coordinate that we want to move to
        float yCoordinate = (direction * speed) + paddle.getY();

        // Calculates the top bound, bottom bound is 0
        float topBounds = worldHeight - paddle.getHeight();

        if (yCoordinate < topBounds && yCoordinate > 0) {
            paddle.setPosition(paddle.getX(), yCoordinate);
        }
    }

    public Rectangle getBounds(){
        return paddle.getBoundingRectangle();
    }

    public void setPosition(float x, float y) {
        paddle.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        paddle.draw(batch);
    }

    public void dispose() {
        paddleImage.dispose();
    }

}
