package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.TestState;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new TestState(gsm));
		
	}

	public void update(float dt){
		gsm.update(dt);
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.end();

		gsm.render(batch);
		update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gsm.dispose();
	}
}
