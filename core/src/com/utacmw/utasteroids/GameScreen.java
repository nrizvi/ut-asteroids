package com.utacmw.utasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Controls the screen for the game
 * @author Ben
 */
public class GameScreen extends ScreenAdapter {

    SpriteBatch batch;
    Viewport viewport;
    Player player;
    Ghost ghost;
    Texture background;

    @Override
    public void show() {
        // Set up the necessary objects
        batch = new SpriteBatch();
        // Create the viewport
        viewport = new ExtendViewport(200,200);
        // Create the player
        player = new Player(viewport);
        //
        ghost = new Ghost(player);
        // Create the texture for the background
        background = new Texture("background.png");
        viewport.setScreenHeight(200);
        viewport.setScreenWidth(200);
        System.out.print(viewport.getScreenHeight());
        System.out.print(viewport.getScreenWidth());
        System.out.print("Game Screen");
        System.out.print("\n");

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        // Apply the viewport
        viewport.apply();
        // Some OpenGL stuff. It needs to be there so don't touch this for now
        Gdx.gl.glClearColor(Color.rgb565(Color.RED), 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);
        ghost.update(delta);
        // Set the projection matrix
        batch.setProjectionMatrix(viewport.getCamera().combined);
        // Start the batch
        batch.begin();
        // TODO Fix resize issue where the background does not change with the size of the screen
        // Draw the background
        batch.draw(background, 0, 0, viewport.getScreenWidth() / 2, viewport.getScreenHeight() / 2);
        // Draw the player
        player.draw(batch);
        ghost.draw(batch);
        batch.end();
    }



}
