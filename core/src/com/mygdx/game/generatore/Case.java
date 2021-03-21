package com.mygdx.game.generatore;

/**
 * Created by lorenzo on 12/11/17.
 */

public class Case {

    private boolean isFull;

    public Case(boolean isFull){
        this.isFull = isFull;
    }

    public void fill(){
        isFull = true;
    }

    public void empty(){
        isFull = false;
    }

    public boolean isFull(){
        return isFull;
    }

    public boolean isEmpty(){
        if(isFull)
            return false;
        else return true;
    }

}
