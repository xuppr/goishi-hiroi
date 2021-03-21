package com.mygdx.game.griglia;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utility.ColorSet;


/**
 * Created by lorenzo on 07/12/17.
 */

public class Connettore {

    Pixmap connPixmap;
    Texture connTxt;
    Sprite connSpr;

    float x;
    float y;

    float width;
    float height;

    Color neutro;
    Color attivo;

    Quad quadL;
    Quad quadR;

    public Connettore(Quad quadL, Quad quadR, float width, float height, ColorSet colorSet){
        this.quadL = quadL;
        this.quadR = quadR;
        this.width = width;
        this.height = height;
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){

    }

    public void dispose(){

    }

}
