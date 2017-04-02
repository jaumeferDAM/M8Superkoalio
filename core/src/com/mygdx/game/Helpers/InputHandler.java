package com.mygdx.game.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Objects.Koala;
import com.mygdx.game.Superkoalio;

/**
 * Created by Jaume on 02/04/2017.
 */

public class InputHandler {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Texture koalaTexture;
    private Animation<TextureRegion> stand;
    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> jump;
    private Koala koala;
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();

    private static final float GRAVITY = -2.5f;

    private boolean debug = false;
    private ShapeRenderer debugRenderer;

    public void comprovarTecla() {

        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || isTouched(0.5f, 1)) && koala.grounded) {
            koala.velocity.y += Koala.JUMP_VELOCITY;
            koala.state = Koala.State.Jumping;
            koala.grounded = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A) || isTouched(0, 0.25f)) {
            koala.velocity.x = -Koala.MAX_VELOCITY;
            if (koala.grounded) koala.state = Koala.State.Walking;
            koala.facesRight = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D) || isTouched(0.25f, 0.5f)) {
            koala.velocity.x = Koala.MAX_VELOCITY;
            if (koala.grounded) koala.state = Koala.State.Walking;
            koala.facesRight = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            debug = !debug;


    }

    private boolean isTouched (float startX, float endX) {
        // Check for touch inputs between startX and endX
        // startX/endX are given between 0 (left edge of the screen) and 1 (right edge of the screen)
        for (int i = 0; i < 2; i++) {
            float x = Gdx.input.getX(i) / (float)Gdx.graphics.getWidth();
            if (Gdx.input.isTouched(i) && (x >= startX && x <= endX)) {
                return true;
            }
        }
        return false;
    }
}
