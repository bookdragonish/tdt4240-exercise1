package com.example.exercise1.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.exercise1.MyGame;
import com.example.exercise1.Task1;
import com.example.exercise1.Task2;
import com.example.exercise1.Task3a;
import com.example.exercise1.Task3b;
import com.example.exercise1.Task4;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new Task4(), configuration);
    }
}
