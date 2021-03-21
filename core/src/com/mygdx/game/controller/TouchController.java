package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.mygdx.game.button.RefreshButton;
import com.mygdx.game.griglia.Box;
import com.mygdx.game.griglia.Quad;
import com.mygdx.game.utility.Matrix;

/**
 * Created by lorenzo on 12/11/17.
 */

public class TouchController {

    Matrix<Box> matrixBox;

    Array<Quad> quadArray;
    Array<Quad> quadAccessibili;
    Array<Quad> pilaQuad;
    IntArray iDaRimuovere;
    Quad testa;
    Quad quadPre;
    Rectangle touchRectangle;
    Vector3 touchPos;

    RefreshButton refreshBtn;


    public TouchController(Array<Quad> boxArray, Matrix<Box> matrixBox, RefreshButton refreshBtn){
        this.matrixBox = matrixBox;
        this.quadArray = boxArray;
        quadAccessibili = new Array<Quad>();
        quadAccessibili.addAll(quadArray);
        touchRectangle = new Rectangle();
        touchPos = new Vector3();
        touchRectangle.setSize(8);
        iDaRimuovere = new IntArray();
        pilaQuad = new Array<Quad>();
        this.refreshBtn = refreshBtn;

    }

    public void handleInput(OrthographicCamera camera){

        if(Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            touchRectangle.setCenter(touchPos.x, touchPos.y);
            if(touchRectangle.overlaps(refreshBtn.getRectangle())){
                if(pilaQuad.size>0)
                    pilaQuad.peek().nonTesta();
                pilaQuad.clear();
                for(int k = 0; k < quadArray.size;k++){
                    if(!quadArray.get(k).isPad()){
                        quadArray.get(k).change();
                    }
                }
            }
            for (int i = 0; i < quadAccessibili.size; i++) {
                if (quadAccessibili.get(i).getQuadRectangle().overlaps(touchRectangle)) {
                    //quadAccessibili.get(i).change();
                    pilaQuad.add(quadAccessibili.get(i));
                    pilaQuad.peek().change();
                    break;
                }else if (pilaQuad.size > 0 && touchRectangle.overlaps(pilaQuad.peek().getQuadRectangle())) {
                    pilaQuad.get(pilaQuad.size-1).change();
                    pilaQuad.get(pilaQuad.size-1).nonTesta();
                    pilaQuad.pop();
                }
            }
            if(quadAccessibili.size<1){
                 if (pilaQuad.size > 0 && touchRectangle.overlaps(pilaQuad.peek().getQuadRectangle())) {
                    pilaQuad.peek().change();
                    pilaQuad.peek().nonTesta();
                    pilaQuad.pop();
                }
            }

            quadAccessibili.clear();
            pulisciMatrixBox();

            if (pilaQuad.size > 0) {

                testa = pilaQuad.peek();
                matrixBox.get(testa.getI(),testa.getJ()).txtTesta();

                //>>>>>>nuovo da testare>>>>>>>>>>
                testa.testa();
                for(int n=0 ; n<pilaQuad.size-1;n++){
                    pilaQuad.get(n).nonTesta();
                }
                //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


                // controllo a destra
                for (int j = 1; j < matrixBox.getColonne() - testa.getJ(); j++) {
                    if (matrixBox.get(testa.getI(), testa.getJ() + j).isFull()) {
                        if(matrixBox.get(testa.getI(), testa.getJ() + j).getQuad().isPad()){
                            quadAccessibili.add(matrixBox.get(testa.getI(), testa.getJ() + j).getQuad());
                            break;
                        }
                    }
                }
                // controllo a sinistra
                for (int j = 1; j <= testa.getJ(); j++) {
                    if (matrixBox.get(testa.getI(), testa.getJ() - j).isFull()) {
                        if(matrixBox.get(testa.getI(), testa.getJ() - j).getQuad().isPad()){
                            quadAccessibili.add(matrixBox.get(testa.getI(), testa.getJ() - j).getQuad());
                            break;
                        }
                    }
                }
                // controllo giù
                for (int i = 1; i < matrixBox.getRighe() - testa.getI(); i++) {
                    if (matrixBox.get(testa.getI() + i, testa.getJ()).isFull()) {
                        if(matrixBox.get(testa.getI() + i, testa.getJ()).getQuad().isPad()){
                            quadAccessibili.add(matrixBox.get(testa.getI() + i, testa.getJ()).getQuad());
                            break;
                        }
                    }
                }
                // controllo su
                for (int i = 1; i <= testa.getI(); i++) {
                    if (matrixBox.get(testa.getI() - i, testa.getJ()).isFull()) {
                        if(matrixBox.get(testa.getI() - i, testa.getJ()).getQuad().isPad()){
                            quadAccessibili.add(matrixBox.get(testa.getI() - i, testa.getJ()).getQuad());
                            break;
                        }
                    }
                }

                if (pilaQuad.size > 1) {
                    int dirI;
                    int dirJ;
                    quadPre = pilaQuad.get(pilaQuad.size-2);
                    dirI = pilaQuad.peek().getI() - quadPre.getI();
                    dirJ = pilaQuad.peek().getJ() - quadPre.getJ();

                    for (int k = 0; k < quadAccessibili.size; k++) {
                            if(dirI<0){
                                if (quadAccessibili.get(k).getI() >= quadPre.getI() && quadAccessibili.get(k).getJ() == quadPre.getJ()) {
                                    iDaRimuovere.add(k);
                                }
                            }
                            if(dirI>0){
                                if (quadAccessibili.get(k).getI() <= quadPre.getI() && quadAccessibili.get(k).getJ() == quadPre.getJ()) {
                                    iDaRimuovere.add(k);
                                }
                            }
                            if(dirJ<0){
                                if (quadAccessibili.get(k).getJ() >= quadPre.getJ() && quadAccessibili.get(k).getI() == quadPre.getI()) {
                                    iDaRimuovere.add(k);
                                }
                            }
                            if(dirJ>0){
                                if (quadAccessibili.get(k).getJ() <= quadPre.getJ() && quadAccessibili.get(k).getI() == quadPre.getI()) {
                                    iDaRimuovere.add(k);
                                }
                            }
                    }

                    int decremento = 0;
                    for (int k = 0; k < iDaRimuovere.size; k++){
                        quadAccessibili.removeIndex(iDaRimuovere.get(k) - decremento);
                        decremento++;
                    }
                    iDaRimuovere.clear();

                }

                for (int k = 0; k < quadAccessibili.size; k++) {

                    // >>>>>>>>>>>>debug>>>>>>>>>>>>>>>>
                    System.out.println(quadAccessibili.get(k).getI() + " , " + quadAccessibili.get(k).getJ());
                    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                    matrixBox.get(quadAccessibili.get(k).getI(), quadAccessibili.get(k).getJ()).txtAccessibile();

                }

            }
            else
                quadAccessibili.addAll(quadArray);
        }



    }

    private void pulisciMatrixBox(){
        for(int i =0;i < matrixBox.getRighe(); i++){
            for (int j = 0; j < matrixBox.getColonne(); j++){
                matrixBox.get(i,j).txtNormale();
            }
        }
    }
}
