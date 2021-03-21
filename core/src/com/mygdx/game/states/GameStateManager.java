package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by lorenzo on 11/11/17.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){

        states = new Stack<State>();
    }

    public void push(State state){

        states.push(state);
    }

    public void pop(){

        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){

        states.peek().update(dt);
    }

    public void render(SpriteBatch batch){

        states.peek().render(batch);


    }

    public void dispose(){
        states.peek().dispose();
    }
}
