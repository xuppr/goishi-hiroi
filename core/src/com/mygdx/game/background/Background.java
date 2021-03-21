package com.mygdx.game.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by lorenzo on 11/11/17.
 */

public class Background {

    Sprite backgrSpr;
    Texture backgrTxt;

    public Background(){
        backgrTxt = new Texture("background2D2.png");
        backgrSpr = new Sprite(backgrTxt);
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){
        backgrSpr.draw(batch);
    }

    public void dispose(){
        backgrSpr.getTexture().dispose();
        backgrTxt.dispose();
    }

}
