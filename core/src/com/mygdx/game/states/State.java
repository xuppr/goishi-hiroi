package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by lorenzo on 11/11/17.
 */

public abstract class State {

    protected OrthographicCamera camera;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){

        this.gsm = gsm;
    }

    protected abstract void handleInput();
    protected abstract void update(float dt);
    protected abstract void render(SpriteBatch batch);
    protected abstract void dispose();

}
