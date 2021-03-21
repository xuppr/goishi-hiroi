package com.mygdx.game.griglia;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.utility.ColorSet;
import com.mygdx.game.utility.PadPod;

/**
 * Created by lorenzo on 11/11/17.
 */

public class Quad extends Sprite{

    Texture quadTxt;
    Sprite quadSprPad;
    Sprite quadSprPod;
    Rectangle quadRectangle;
    float size;

    Texture testaTxt;
    Sprite testaSpr;

    boolean isPad;

    int i;
    int j;

    AnimatedCircle circle;
    Array<Connettore> connettoriArray;

    public Quad(float x, float y, int i, int j, OrthographicCamera camera, ColorSet colorSet){
        this.i = i;
        this.j = j;
        isPad =true;
        quadTxt = PadPod.getByBool(true);
        quadSprPad = new Sprite(quadTxt);
        quadSprPad.setCenter(x,y);
        set(quadSprPad);

        quadSprPod = new Sprite(PadPod.getByBool(false));
        quadSprPod.setCenter(x,y);

        testaTxt = new Texture("testa85D1.png");
        testaTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        testaSpr = new Sprite(testaTxt);
        testaSpr.setCenter(x,y);

        size = getHeight();
        quadRectangle = new Rectangle(0,0,size,size);
        quadRectangle.setCenter(x,y);

        //>>>>>>>>>>>>>> animatedCircle >>>>>>>>>>>>>>>>>
        circle = new AnimatedCircle(x,y,camera,colorSet);
        connettoriArray = new Array<Connettore>();
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    }

    //*****************************

    public void update(float dt){

        circle.update(dt);

    }

    public void render(){

        circle.render();

    }

    public void dispose(){
        circle.dispose();
    }

    //****************************

    public void change(){
        if(isPad){
            set(quadSprPod);
            isPad = false;
            circle.nonPad();
        }
        else{

            set(quadSprPad);
            isPad = true;
            circle.pad();
        }
    }

    public void pad(){
        setTexture(PadPod.getByBool(true));
        isPad = true;
    }

    public void testa(){
        set(testaSpr);
        circle.testa();
    }

    public void nonTesta(){
        set(quadSprPod);
        circle.nonTesta();
    }

    public void accessibile(){
        circle.accessibile();
    }

    public void nonAccessibile(){
        circle.nonAccess();
    }


    public Rectangle getQuadRectangle(){
        return quadRectangle;
    }

    public int getI(){
        return  i;
    }

    public  int getJ(){
        return  j;
    }

    public boolean isPad(){
        return isPad;
    }

    public void addConnettore(Connettore connettore){
        connettoriArray.add(connettore);
    }

    //>>>>>>>> x prova salto>>>>>>>>>>>
    public void setColoreCornice(Color color){
        circle.setColoreCornice(color);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

}
