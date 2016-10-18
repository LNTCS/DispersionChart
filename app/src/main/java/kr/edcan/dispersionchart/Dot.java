package kr.edcan.dispersionchart;

import android.graphics.Color;

/**
 * Created by LNTCS on 2016-10-18.
 */

public class Dot {
    float x = 0f;
    float y = 0f;
    int color = 0;

    public Dot(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Dot(float y, float x, int color) {
        this.color = color;
        this.y = y;
        this.x = x;
    }

    public Dot(float y, float x, String colorHex) {
        this.color = Color.parseColor(colorHex);
        this.y = y;
        this.x = x;
    }

    public Dot(Dot dot){
        this.x = dot.x;
        this.y = dot.y;
        this.color = dot.color;
    }
}
