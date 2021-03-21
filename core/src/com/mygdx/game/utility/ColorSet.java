package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by lorenzo on 06/12/17.
 */

public class ColorSet {

    Color background;
    Color testaEaccessibili;
    Color cornice;
    Color salto;

    public ColorSet(){
        /*background = new Color(1,122/255f,90/255f,1);
        testaEaccessibili = new Color(142/255f,210/255f,201/255f,1);
        cornice = new Color(0,170/255f,160/255f,1);
        salto = Color.BLUE;*/

        /*background = new Color(93/255f,76/255f,70/255f,1);
        testaEaccessibili = new Color(123/255f,141/255f,142/255f,1);
        cornice = new Color(242/255f,237/255f,216/255f,1);
        salto = Color.BLUE;*/

        background = new Color(111/255f,54/255f,98/255f,1);
        testaEaccessibili = new Color(255/255f,174/255f,93/255f,1);
        cornice = new Color(255/255f,113/255f,130/255f,1);
        salto = Color.BLUE;


    }

    public Color getBackground() {
        return background;
    }

    public Color getTestaEaccessibili() {
        return testaEaccessibili;
    }

    public Color getCornice() {
        return cornice;
    }

    public Color getSalto() {
        return salto;
    }
}
