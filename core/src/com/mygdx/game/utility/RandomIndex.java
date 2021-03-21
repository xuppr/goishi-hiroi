package com.mygdx.game.utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

import java.util.Random;

/**
 * Created by lorenzo on 12/11/17.
 */

public class RandomIndex {

    Random rnd;
    int index;

    public RandomIndex(){
        rnd = new Random();
    }

    public int getAndCut(IntArray intArray){
        index = rnd.nextInt(intArray.size);
        int value = intArray.get(index);
        intArray.removeIndex(index);
        return value;
    }

    public Vector2 getAndCut(Array<Vector2> array){
        index = rnd.nextInt(array.size);
        Vector2 value = array.get(index);
        array.removeIndex(index);
        return value;
    }

    public Tupla get(Array<Tupla> tuplaArray){
        index = rnd.nextInt(tuplaArray.size);
        Tupla value = tuplaArray.get(index);
        return value;
    }

    public Tupla getAndCutT(Array<Tupla> tuplaArray){
        index = rnd.nextInt(tuplaArray.size);
        Tupla value = tuplaArray.get(index);
        tuplaArray.removeIndex(index);
        return value;
    }

    public Array<Tupla> randomArrayT(Array<Tupla> array0, Array<Tupla> array1){
        int randomInt = rnd.nextInt(2);
        if(array0.size == 0)
            return array1;
        if (array1.size == 0)
            return array0;
        else if(randomInt == 0)
            return array0;
        else
            return array1;
    }

    public Tupla tuplaFrom2Arrays(Array<Tupla> array0, Array<Tupla> array1){
        Array<Tupla> tuplaArray = randomArrayT(array0,array1);
        if(array0.size==0)
            return get(array1);
        else if(array1.size==0)
            return get(array0);
        else {
            return get(tuplaArray);
        }
    }
}
