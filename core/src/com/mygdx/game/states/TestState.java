package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Quaternion;
import com.mygdx.game.background.Background;
import com.mygdx.game.button.RefreshButton;
import com.mygdx.game.controller.TouchController;
import com.mygdx.game.griglia.Griglia;
import com.mygdx.game.utility.ColorSet;
import com.mygdx.game.utility.PadPod;

/**
 * Created by lorenzo on 11/11/17.
 */

public class TestState extends State {

    PadPod padPod;
    Griglia griglia;
    TouchController touchController;
    RefreshButton refreshBtn;
    ColorSet colors;

    public TestState(GameStateManager gsm){
        super(gsm);
        colors = new ColorSet();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1080,1920);
        camera.update();
        padPod = new PadPod();
        // righe 4, colonne 4, boxSize 115, numPad 9; testa 130, vuoto 70, accessibile 104
        //griglia = new Griglia(1920/(float)2,1080/(float)2,6,6,160,2,camera,colors);
        griglia = new Griglia(1080/(float)2,1920/(float)2,7,5,210,21,camera,colors);
        //refreshBtn = new RefreshButton(6*(1920/(float)7),1080/(float)2);
        refreshBtn = new RefreshButton(1080/(float)2,(1920/(float)14));
        touchController = new TouchController(griglia.getQuadForRender(),griglia.getMatrixBox(),refreshBtn);
    }

    @Override
    protected void handleInput() {
        touchController.handleInput(camera);
    }

    @Override
    protected void update(float dt) {
        griglia.update(dt);
    }

    @Override
    protected void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(colors.getBackground().r, colors.getBackground().g,colors.getBackground().b, colors.getBackground().a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //griglia.render(batch);
        refreshBtn.draw(batch);
        batch.end();

        griglia.render2();

        handleInput();

    }

    @Override
    protected void dispose() {
        griglia.dispose();
    }
}
