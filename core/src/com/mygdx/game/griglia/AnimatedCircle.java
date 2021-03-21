package com.mygdx.game.griglia;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.utility.Circle;
import com.mygdx.game.utility.ColorSet;

/**
 * Created by lorenzo on 07/12/17.
 */

public class AnimatedCircle {

    Circle corniceCircle;
    Circle vuotoCircle;
    Circle accessCircle;
    Circle testaCircle;

    Color colorTesta;
    Color colorAcess;
    Color colorVuoto;
    Color colorCornice;

    float fattoreDimensione = 0.5f;

    //>>>>>> xCorniceCircle>>>>>>
    float radCornice = 130*fattoreDimensione;
    //<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>> xVuotoCircle>>>>>>>>

    float tVuoto = 0;
    float radVuoto = 90*fattoreDimensione;
    float rVuoto = 0;
    boolean isPad = true;
    float delayVuoto = 0;
    float tDelayVuoto = 0;
    float coefVuoto = 4;

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>> xAccessCircle>>>>>>>

    float tAccess = 0;
    float radAccess = 130*fattoreDimensione;
    float rAccess = 0;
    boolean isAccess = false;
    float delayAccess = 0;
    float tDelayAccess = 0;
    float coefAccess = 3f;

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>> xTestaCircle>>>>>>>>

    float tTesta = 0;
    float radTesta = 160*fattoreDimensione;
    float rTesta = 0;
    boolean isTesta = false;
    float delayTesta = 0;
    float tDelayTesta = 0;
    float coefTesta = 2;

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public AnimatedCircle(float x, float y, OrthographicCamera camera, ColorSet setColori){

        colorTesta = setColori.getTestaEaccessibili().cpy();
        colorAcess = setColori.getTestaEaccessibili().cpy();
        colorCornice = setColori.getCornice();
        colorVuoto = setColori.getBackground();

        corniceCircle = new Circle(x,y,150*fattoreDimensione,colorCornice,camera);
        corniceCircle.setRadius(radCornice);

        vuotoCircle = new Circle(x,y,150*fattoreDimensione,colorVuoto,camera);
        vuotoCircle.setRadius(radVuoto);

        accessCircle = new Circle(x,y,150*fattoreDimensione, colorTesta,camera);
        accessCircle.setRadius(0);
        accessCircle.setColor(Color.CLEAR);

        testaCircle = new Circle(x,y,200*fattoreDimensione, colorTesta,camera);
        testaCircle.setRadius(0);
        testaCircle.setColor(Color.CLEAR);

    }

    public void update(float dt){

        animationVuoto(dt);
        animationAccess(dt);
        animationTesta(dt);

    }

    public void render(){
        testaCircle.draw();
        corniceCircle.draw();
        accessCircle.draw();
        vuotoCircle.draw();
    }

    //>>>>>>>>>> vuotoCircle >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void pad(){
        isPad = true;
        tDelayVuoto = delayVuoto;
        vuotoCircle.setColor(colorVuoto);
    }

    public void nonPad(){
        isPad = false;
        tDelayVuoto = delayVuoto;
    }

    private void animationVuoto(float dt){
        if(tDelayVuoto == 0){
            if(!isPad && tVuoto<1){
                vuotoCircle.setRadius(radVuoto-rVuoto);
                tVuoto+=dt*coefVuoto;
                rVuoto = tVuoto*radVuoto;
                if(tVuoto >1){
                    tVuoto = 1;
                    rVuoto = radVuoto;
                    vuotoCircle.setRadius(0);
                    vuotoCircle.setColor(Color.CLEAR);
                }
            }
            else if(isPad && tVuoto>0){
                vuotoCircle.setRadius(radVuoto - rVuoto);
                tVuoto-=dt*coefVuoto;
                rVuoto = tVuoto*radVuoto;
                if(tVuoto<0){
                    tVuoto = 0;
                    rVuoto = 0;
                    vuotoCircle.setRadius(radVuoto);
                }
            }
        }
        else {
            tDelayVuoto-=dt;
            if (tDelayVuoto<0)
                tDelayVuoto = 0;
        }

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>> accessCircle >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void accessibile(){
        isAccess = true;
        accessCircle.setColor(colorAcess);
        tDelayAccess = delayAccess;
    }

    public void nonAccess(){
        isAccess = false;
        tDelayAccess = delayAccess;
    }


    private void animationAccess(float dt){
        if(tDelayAccess == 0){
            if(isAccess && tAccess<1){
                accessCircle.setRadius(rAccess);
                tAccess+=coefAccess*dt;
                rAccess = tAccess*radAccess;
                if(tAccess>1){
                    tAccess = 1;
                    rAccess = radAccess;
                    accessCircle.setRadius(radAccess);
                }
            }
            else if(!isAccess && tAccess>0){
                accessCircle.setRadius(rAccess);
                tAccess-=coefAccess*dt;
                rAccess = tAccess*radAccess;
                colorAcess.set(colorAcess.r,colorAcess.g,colorAcess.b,0.6f*tAccess);
                accessCircle.setColor(colorAcess);
                if(tAccess<0){
                    tAccess = 0;
                    rAccess = 0;
                    accessCircle.setRadius(0);
                    accessCircle.setColor(Color.CLEAR);
                    colorAcess.set(colorAcess.r,colorAcess.g,colorAcess.b,1);
                }
            }
        }
        else {
            tDelayAccess-=dt;
            if(tDelayAccess<0)
                tDelayAccess = 0;
        }
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>>>>> testaCircle >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void testa(){
        isTesta = true;
        testaCircle.setColor(colorTesta);
        tDelayTesta = delayTesta;
    }

    public void nonTesta(){
        isTesta = false;
        tDelayTesta = delayTesta;
    }

    private void animationTesta(float dt){
        if(tDelayTesta == 0){
            if(isTesta && tTesta<1){
                testaCircle.setRadius(rTesta);
                tTesta+=coefTesta*dt;
                rTesta = tTesta*radTesta;
                if(tTesta>1){
                    tTesta = 1;
                    rTesta = radTesta;
                    testaCircle.setRadius(radTesta);
                }
            }
            else if(!isTesta && tTesta>0){
                testaCircle.setRadius(rTesta);
                tTesta-=coefTesta*dt;
                rTesta = tTesta*radTesta;
                if(tTesta<0){
                    tTesta = 0;
                    rTesta = 0;
                    testaCircle.setRadius(0);
                    testaCircle.setColor(Color.CLEAR);
                }
            }
        }
        else {
            tDelayTesta-=dt;
            if(tDelayTesta<0)
                tDelayTesta = 0;
        }

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    public void dispose(){
        corniceCircle.dispose();
        testaCircle.dispose();
        accessCircle.dispose();
        vuotoCircle.dispose();
    }

    //>>>>>>>>> x prova salto >>>>>>>>>>>>>>>
    public void setColoreCornice(Color color){
        colorCornice = color;
        corniceCircle.setColor(colorCornice);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




}
