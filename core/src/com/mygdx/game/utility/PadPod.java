package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Created by lorenzo on 11/11/17.
 */

public class PadPod {

    Texture padTxt;
    Texture podTxt;
    Texture testaTxt;
    static Array<Texture> padPod;

    public PadPod(){
        padTxt = new Texture("pad85D2.png");
        padTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        podTxt = new Texture("pod85D2.png");
        podTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        testaTxt = new Texture("testaBianca85D1.png");
        testaTxt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        padPod = new Array<Texture>();
        padPod.add(padTxt);
        padPod.add(podTxt);
    }

    public static Texture getByBool(boolean isPad){
        if(isPad)
            return padPod.get(0);
        else
            return padPod.get(1);
    }


}
