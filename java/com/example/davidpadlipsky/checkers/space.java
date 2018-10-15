package com.example.davidpadlipsky.checkers;

/**
 * Created by davidpadlipsky on 9/12/17.
 */

public class space {
    private int xVal;
    private int yVal;

    space(int y, int x){
        xVal = x;
        yVal = y;
    }

    space(){
        xVal = -1;
        yVal = -1;
    }

    public int getxVal() {
        return xVal;
    }

    public int getyVal()  {
        return yVal;
    }
}
