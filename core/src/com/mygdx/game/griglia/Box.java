package com.mygdx.game.griglia;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.ColorSet;

/**
 * Created by lorenzo on 11/11/17.
 */

public class Box {

    Sprite boxSpr;

    //>>>>>>>>nuovo metodo per dissolvenza>>>>>>
    Sprite boxSprNormale;
    Sprite boxSprAccsble;
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    Texture boxTxt;
    Texture boxAccessibileTxt;
    Texture boxTestaTxt;

    Vector2 center;

    Quad quad;

    float size;
    boolean isFull;
    boolean txtAccessibile = false;

    int i;
    int j;

    float t = 0;
    float rate = 0.3f;

    public Box(float x, float y, float size, int i, int j){

        this.i = i;
        this.j = j;

        boxTxt = new Texture("boxRingD1.png");
        boxTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        boxAccessibileTxt = new Texture("boxRingA113D1.png");
        boxAccessibileTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        boxTestaTxt = new Texture("boxRingAcsbleD1.png");
        boxTestaTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        boxSpr = new Sprite(boxTxt);
        boxSpr.setCenter(x,y);
        boxSpr.setAlpha(0);

        center = new Vector2(x,y);
        this.size = size;
        isFull = false;

        //>>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>>
        boxSprNormale = new Sprite(boxTxt);
        boxSprNormale.setCenter(x,y);
        boxSprNormale.setAlpha(1);
        boxSprAccsble = new Sprite(boxAccessibileTxt);
        boxSprAccsble.setCenter(x,y);
        boxSprAccsble.setAlpha(0);
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    public void update(float dt){

        //>>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>>


        if(txtAccessibile && t<1){
            t+=dt/rate;
            if(t>1){
                t = 1;
            }
        }
        if(!txtAccessibile && t>0){
            t-=dt/rate;
            if(t<0)
                t=0;

        }

        boxSprAccsble.setAlpha(t);

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    public void render(SpriteBatch batch){

        //boxSpr.draw(batch);

        //>>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>>
        //boxSprNormale.draw(batch);
        boxSprAccsble.draw(batch);
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    public void addQuad(OrthographicCamera camera, ColorSet colorSet){
        quad = new Quad(center.x,center.y,i,j,camera,colorSet);
        isFull = true;
    }

    public void clearQuad(){
        quad = null;
    }

    public void dispose(){
        if(isFull){
            quad.getTexture().dispose();
            quad.dispose();
        }
    }

    public boolean isFull(){
        return isFull;
    }

    public Quad getQuad(){
        return quad;
    }

    //>>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>>

    public void txtAccessibile(){
        txtAccessibile = true;
        if(isFull)
            quad.accessibile();
    }

    public void txtNormale(){
        txtAccessibile = false;
        if(isFull)
            quad.nonAccessibile();
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>>>AnimatedCircle>>>>>>>>>>>>>>>>>>>>>
    public void testa(){
        if (isFull)
            quad.accessibile();
    }

    public void nonTesta(){
        if(isFull)
            quad.nonAccessibile();
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /*public void txtAccessibile(){
        boxSpr.setTexture(boxAccessibileTxt);
    }

    public void txtNormale(){
        boxSpr.setTexture(boxTxt);
    }*/

    public void txtTesta(){
        boxSpr.setTexture(boxTestaTxt);
    }

    public void boxAlphaFull(){
        //boxSpr.setAlpha(1);

        //>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>
        boxSprNormale.setAlpha(1);
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }


}
