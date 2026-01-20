package com.example.exercise1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

// CLASS USED FOR TASK 3B.

public class Helicopter {

    // pixels to move each second
    private float velX;
    private  float velY;

    // Animations
    private final Texture heli1, heli2, heli3, heli4;
    private final Animation<TextureRegion> heliAnimation;
    private float stateTime;

    // Sprite
    private final Sprite helicopter;

    public Helicopter (float velX, float velY){
        this.velX = velX;
        this.velY = velY;

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
    }

    // Animate helicopter
    public void animateFrames(float dt){
        this.stateTime += dt;
        // Set new frame
        TextureRegion currentFrame = heliAnimation.getKeyFrame(stateTime);
        helicopter.setRegion(currentFrame);
    }

    // Direction of helicopter
    public void checkDirection(float WORLD_WIDTH, float WORLD_HEIGHT, float dt){
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
    }

    public void draw(SpriteBatch batch){
        helicopter.draw(batch);
    }

    public void dispose(){
        heli1.dispose();
        heli2.dispose();
        heli3.dispose();
        heli4.dispose();
    }

    public Rectangle getBounds(){
        return this.helicopter.getBoundingRectangle();
    }

    public void bounceOff(Helicopter other){
        // Each sprite has a rectangle around it (its bounding box).
        // A collision happens when the two rectangles overlap

        float hX = this.helicopter.getX();
        float hY = this.helicopter.getY();

        Rectangle b1 = this.getBounds();
        Rectangle b2 = other.getBounds();

        if (b1.overlaps(b2)) {
            System.out.println("Collision!");

            // AI GEN: had help from ai to calculate the overlap of the rectangles
            float overlapX = Math.min(b1.x + b1.width, b2.x + b2.width) - Math.max(b1.x, b2.x);
            float overlapY = Math.min(b1.y + b1.height, b2.y + b2.height) - Math.max(b1.y, b2.y);


            if(overlapX < overlapY){
                this.velX = -this.velX;
                other.setVelX(-other.getVelX()); // other.velX = -other.velX;

                // AI GEN: had some help generating this code that make sure the helicopters separate
                // so they don't "stick"
                float push = overlapX / 2f;
                if (b1.x < b2.x) {
                    this.helicopter.translate(-push, 0);
                    other.helicopter.translate(push, 0);
                } else {
                    this.helicopter.translate(push, 0);
                    other.helicopter.translate(-push, 0);
                }


            } else {
                this.velY = -this.velY;
                other.setVelY(-other.getVelY()); // other.velX = -other.velX;

                float push = overlapY / 2f;
                if (b1.y < b2.y) {
                    this.helicopter.translate(0, -push);
                    other.helicopter.translate(0, push);
                } else {
                    this.helicopter.translate(0, push);
                    other.helicopter.translate(0, -push);
                }

            }

        }

    }

    // Getters and setters
    public float getX(){
        return helicopter.getX();
    }
    public float getY(){
        return helicopter.getY();
    }

    public float getVelX() { return velX; }
    public float getVelY() { return velY; }

    public void setVelX(float v) { velX = v; }
    public void setVelY(float v) { velY = v; }

    public void setPosition(float x, float y){
        helicopter.setPosition(x, y);
    }



}
