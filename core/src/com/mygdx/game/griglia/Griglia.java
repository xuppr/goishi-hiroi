package com.mygdx.game.griglia;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.mygdx.game.generatore.GeneratoreGoishi;
import com.mygdx.game.utility.ColorSet;
import com.mygdx.game.utility.Matrix;
import com.mygdx.game.utility.RandomIndex;
import com.mygdx.game.utility.Tupla;

/**
 * Created by lorenzo on 11/11/17.
 */

public class Griglia {

    Vector2 center;
    Vector2 traslatore;
    Matrix<Box> matrixBox;

    Array<Quad> quadForRender;

    Array<Tupla> ijIndex;

    IntArray indexI;
    IntArray indexJ;

    int numRighe;
    int numColonne;
    float boxSize;

    int numPad;

    RandomIndex randomIndex;

    GeneratoreGoishi goishiGen;

    OrthographicCamera camera;
    ColorSet colorSet;

    public Griglia(float centerX, float centerY, int numRighe, int numColonne, float boxSize, int numPad, OrthographicCamera camera, ColorSet colorSet){

        indexI = new IntArray();
        indexJ = new IntArray();
        randomIndex = new RandomIndex();
        ijIndex = new Array<Tupla>();

        this.quadForRender = new Array<Quad>();
        this.numRighe = numRighe;
        this.numColonne = numColonne;
        this.matrixBox = new Matrix<Box>(numRighe,numColonne);
        this.boxSize = boxSize;
        this.center = new Vector2(((numColonne-1)*boxSize)/(float)2,((numRighe-1)*boxSize)/(float)2);
        this.traslatore = new Vector2(centerX - this.center.x,centerY - this.center.y );
        this.center.set(centerX,centerY);
        this.numPad = numPad;
        this.camera = camera;
        this.colorSet = colorSet;
        costruisciGriglia();


        //>>>>>>>>>>>>>>con generatore goishi>>>>>>>>>>>>>>>
        goishiGen =new GeneratoreGoishi(numRighe,numColonne);
        assegnaQuadGoishi();
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    private void costruisciGriglia(){
        for(int i = 0; i <this.numRighe; i++){
            for (int j = 0; j <this.numColonne; j++){
                matrixBox.set(i,j,new Box(traslatore.x + j*boxSize,traslatore.y + i*boxSize,boxSize,i,j));
                ijIndex.add(new Tupla(i,j));
            }
        }
    }

    //>>>>>>>>>>>>>>generatore con goishi>>>>>>>>>>>>>>>>>>
    private void assegnaQuadGoishi(){
        Tupla indexIJ;
        Array<Tupla> percorso = goishiGen.generaGoishi(numPad);

        /* >>>>>>>>>>>>>debug>>>>>>>>>>>>>>>>>>>>>
        for (int i = 0; i<percorso.size;i++){
            System.out.println(percorso.get(i).getI() + " , " + percorso.get(i).getJ());
        }
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        for(int k = 0; k < numPad ; k++){
            indexIJ = percorso.pop();
            matrixBox.get(indexIJ.getI(),indexIJ.getJ()).addQuad(camera,colorSet);
            matrixBox.get(indexIJ.getI(),indexIJ.getJ()).boxAlphaFull();
            quadForRender.add(matrixBox.get(indexIJ.getI(),indexIJ.getJ()).getQuad());
            // >>>>>>>>>>>>>prova salto>>>>>>>>>>>>>>>>>>>>>
            if(numPad>1){
                /*if(k == numPad/2 || k == numPad/2 + 1)
                    matrixBox.get(indexIJ.getI(),indexIJ.getJ()).getQuad().setColoreCornice(Color.WHITE);*/
                if(k == 0)
                    matrixBox.get(indexIJ.getI(),indexIJ.getJ()).getQuad().setColoreCornice(Color.WHITE);
                else if(k == numPad - 1)
                    matrixBox.get(indexIJ.getI(),indexIJ.getJ()).getQuad().setColoreCornice(Color.DARK_GRAY);
            }
            //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        }
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




    public void update(float dt){

        //>>>>>>>>>metodo nuovo per dissolvenza>>>>>>>>>>
        for(int i = 0; i <this.numRighe; i++){
            for (int j = 0; j <this.numColonne; j++){
                matrixBox.get(i,j).update(dt);
            }
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


        for (int i = 0; i< quadForRender.size; i++){
            quadForRender.get(i).update(dt);
        }

    }

    public void render(SpriteBatch batch){
        for(int i = 0; i <this.numRighe; i++){
            for (int j = 0; j <this.numColonne; j++){
                matrixBox.get(i,j).render(batch);
            }
        }
        for (int i = 0; i< quadForRender.size; i++){
            quadForRender.get(i).draw(batch);
        }
    }

    public void render2(){
        for (int i = 0; i< quadForRender.size; i++){
            quadForRender.get(i).render();
        }
    }

    public void dispose(){
        for(int i = 0; i <this.numRighe; i++){
            for (int j = 0; j <this.numColonne; j++){
                matrixBox.get(i,j).dispose();
            }
        }
    }

    public Array<Quad> getQuadForRender(){
        return quadForRender;
    }

    public Matrix<Box> getMatrixBox(){
        return matrixBox;
    }


}
