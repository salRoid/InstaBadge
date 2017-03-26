package tech.salroid.demo.instabadge;

import android.view.View;

/**
 * Created by salroid on 3/26/2017.
 */

public class Coordinates {
    int left;
    int top;
    int right;
    int bottom;

    public Coordinates(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        left = location[0];
        right = left + view.getWidth();
        top = location[1];
        bottom = top + view.getHeight();
    }
}
