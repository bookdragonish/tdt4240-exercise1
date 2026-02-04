package com.example.exercise1;

import com.badlogic.gdx.Input;

import java.awt.Graphics;

public interface GameState {
    void handleInput(Input input);
    void update(float deltaTime);
    void render();
}
