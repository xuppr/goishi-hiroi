package com.mygdx.game.utility;

import com.badlogic.gdx.utils.Array;


/**
 * Created by lorenzo on 11/11/17.
 */

public class Matrix<T> {

    Array<Array<T>> matrix;
    int righe;
    int colonne;

    public Matrix(int righe, int colonne){
        this.righe = righe;
        this.colonne = colonne;
        matrix = new Array<Array<T>>();
        for (int i = 0; i< righe; i++){
            matrix.add(new Array<T>());
            matrix.peek().setSize(colonne);
        }
    }

    public void set(int i, int j, T t){
        matrix.get(i).set(j,t);
     }

    public T get(int i, int j){
        return matrix.get(i).get(j);
    }

    public int getRighe(){
        return righe;
    }

    public int getColonne(){
        return colonne;
    }
}
