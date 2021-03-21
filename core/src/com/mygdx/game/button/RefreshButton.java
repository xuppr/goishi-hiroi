package com.mygdx.game.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by lorenzo on 21/11/17.
 */

public class RefreshButton extends Sprite {

    Rectangle refreshRectangle;

    public RefreshButton(float x, float y){
        super(new Texture("refresh.png"));
        getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        setCenter(x,y);
        refreshRectangle = new Rectangle(0,0,getWidth(),getHeight());
        refreshRectangle.setCenter(x,y);
    }

    public Rectangle getRectangle(){
        return refreshRectangle;
    }


}
