package com.mygdx.game.generatore;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.utility.Matrix;
import com.mygdx.game.utility.RandomIndex;
import com.mygdx.game.utility.Tupla;

/**
 * Created by lorenzo on 12/11/17.
 */

public class GeneratoreGoishi {

    Array<Tupla> indici;

    Matrix<Case> matrixCase;
    RandomIndex random;
    int numRighe;
    int numColonne;

    public GeneratoreGoishi(int numRighe, int numColonne){

        this.numRighe = numRighe;
        this.numColonne = numColonne;

        random = new RandomIndex();

        indici = new Array<Tupla>();
        matrixCase = new Matrix<Case>(numRighe,numColonne);

        for (int i = 0; i<numRighe; i++){
            for(int j = 0; j< numColonne; j++){
                matrixCase.set(i,j,new Case(false));
                indici.add(new Tupla(i,j));
            }
        }
    }

    public Array<Tupla> generaGoishi(int numPad){

        Array<Tupla> percorso = new Array<Tupla>();

        Tupla padPrimo = random.getAndCutT(indici);
        percorso.add(padPrimo);

        //riempie l'elemento della matrice in posizione ij relativa al primo pad generato
        matrixCase.get(padPrimo.getI(),padPrimo.getJ()).fill();

        //direzione intesa come direzione di arrivo (direzione è 1 dal basso e cresce in senso antiorario)
        int direzione = 0;

        Array<Tupla> accessibiliRiga;
        Array<Tupla> accessibiliColonna;

        Tupla padPre = new Tupla(padPrimo.getI(),padPrimo.getJ());
        Tupla padPost;

        for (int i = 0; i<numPad-1;i++){

            accessibiliRiga = accessibiliSuRiga(percorso.peek(),direzione);
            accessibiliColonna = accessibiliSuColonna(percorso.peek(),direzione);

            padPost = random.tuplaFrom2Arrays(accessibiliRiga,accessibiliColonna);
            percorso.add(padPost);
            //>>>>>debug>>>>>>>
            //System.out.println("(" + padPre.getI() + " , " + padPre.getJ() + ")" + " ; " + "(" + padPost.getI() + " , " + padPost.getJ() + ")");
            //<<<<<<<<<<<<<<<<<
            matrixCase.get(padPre.getI(),padPre.getJ()).fill();
            matrixCase.get(padPost.getI(),padPost.getJ()).fill();
            direzione = direzioneEriempiSalto(padPre,padPost);
            padPre.setI(padPost.getI());
            padPre.setJ(padPost.getJ());

        }

        return percorso;

    }

    private Array<Tupla> accessibiliSuRiga(Tupla ijPos, int direzione){

        int i = ijPos.getI();
        int j = ijPos.getJ();
        Array<Tupla> accessibiliRiga = new Array<Tupla>();

        if(direzione == 2){
            for (int k = j; 0<=k; k-- ){
                if(!matrixCase.get(i,k).isFull())
                    accessibiliRiga.add(new Tupla(i,k));
            }
        }

        else if(direzione == 4){
            for(int k = j; k<numColonne; k++){
                if(!matrixCase.get(i,k).isFull())
                    accessibiliRiga.add(new Tupla(i,k));
            }
        }

        else{
            for(int k = 0; k<numColonne;k++){
                if(!matrixCase.get(i,k).isFull())
                    accessibiliRiga.add(new Tupla(i,k));
            }
        }

        /*//>>>>>>>>>>>>> metodo precedente>>>>>>>>>>>>>>>>>>>>>>
        for(int k = 0; k < numColonne;k++){
            if(!matrixCase.get(i,k).isFull())
                accessibiliRiga.add(new Tupla(i,k));
        }

        if(direzione == 2){
            for (int k = 0; k < accessibiliRiga.size;k++){
                if(accessibiliRiga.peek().getI() == i && accessibiliRiga.peek().getJ() == j)
                    break;
                accessibiliRiga.pop();
            }
        }
        
        else if(direzione == 4){
            for (int k = 0; k < accessibiliRiga.size;k++){
                if(accessibiliRiga.get(0).getJ() == j)
                    break;
                accessibiliRiga.removeIndex(0);
            }
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        return accessibiliRiga;
    }

    private Array<Tupla> accessibiliSuColonna(Tupla ijPos,int direzione){

        int i = ijPos.getI();
        int j = ijPos.getJ();
        Array<Tupla> accessibiliColonna = new Array<Tupla>();

        if(direzione == 1){
            for(int k = i; 0<=k; k--){
                if(!matrixCase.get(k,j).isFull())
                    accessibiliColonna.add(new Tupla(k,j));
            }
        }

        else if(direzione == 3){
            for(int k = i; k < numRighe; k++){
                if(!matrixCase.get(k,j).isFull())
                    accessibiliColonna.add(new Tupla(k,j));
            }
        }

        else {
            for(int k = 0; k<numRighe; k++){
                if(!matrixCase.get(k,j).isFull())
                    accessibiliColonna.add(new Tupla(k,j));
            }
        }


        /*//>>>>>>>>>>>>>>metodo precedente>>>>>>>>>>>>>>>>>>>>>>>
        for(int k = 0; k < numRighe;k++){
            if(!matrixCase.get(k,j).isFull())
                accessibiliColonna.add(new Tupla(k,j));
        }

        if(direzione == 1){
            for (int k = 0; k < accessibiliColonna.size;k++){
                if(accessibiliColonna.peek().getI() == i && accessibiliColonna.peek().getJ() == j)
                    break;
                accessibiliColonna.pop();
            }
        }

        else if(direzione == 3){
            for (int k = 0; k < accessibiliColonna.size;k++){
                if(accessibiliColonna.get(0).getI() == i)
                    break;
                accessibiliColonna.removeIndex(0);
            }
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        return accessibiliColonna;
    }

    // riempie gli elementi della matrice all'interno di un salto tra un pad precedente e un nuovo pad
    private int direzioneEriempiSalto(Tupla padPre, Tupla padPost){

        int direzione;

        if(padPre.getI() == padPost.getI()){

            if(padPre.getJ()>padPost.getJ()){
                direzione = 2;
                for(int k = 0; k <= padPre.getJ()-padPost.getJ(); k++){
                    matrixCase.get(padPre.getI(),padPre.getJ()-k).fill();
                }
            }
            else{
                direzione = 4;
                for(int k = 0; k<= padPost.getJ()-padPre.getJ();k++){
                    matrixCase.get(padPre.getI(),padPre.getJ()+k).fill();
                }
            }
        }

        else{

            if(padPre.getI()>padPost.getI()){
                direzione = 1;
                for(int k = 0; k <= padPre.getI()-padPost.getI(); k++){
                    matrixCase.get(padPre.getI()-k,padPre.getJ()).fill();
                }
            }
            else{
                direzione = 3;
                for(int k = 0; k<= padPost.getI()-padPre.getI();k++){
                    matrixCase.get(padPre.getI()+k,padPre.getJ()).fill();
                }
            }
        }

        return direzione;
    }

}
