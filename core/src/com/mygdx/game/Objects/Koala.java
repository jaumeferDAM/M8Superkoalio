package com.mygdx.game.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Superkoalio;

/**
 * Created by Jaume on 02/04/2017.
 */

public class Koala extends Actor {
    public static float WIDTH;
    public static float HEIGHT;
    public static float MAX_VELOCITY = 10f;
    public static float JUMP_VELOCITY = 40f;
    public  static float DAMPING = 0.87f;

    public enum State {
        Standing, Walking, Jumping
    }

    public final Vector2 position = new Vector2();
    public final Vector2 velocity = new Vector2();
    public Koala.State state = Koala.State.Walking;
    public float stateTime = 0;
    public boolean facesRight = true;
    public boolean grounded = false;
}
