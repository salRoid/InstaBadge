package tech.salroid.demo.instabadge;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by salroid on 28-03-2017.
 */

public class DummyArrow {

    Context context;
    private int dpAsPixels;
    private float scale;
    private LinearLayout bottom_arrow;
    int value;

    public DummyArrow(Context context){
        this.context = context;

        bottom_arrow = new LinearLayout(context);
        scale = context.getResources().getDisplayMetrics().density;
        dpAsPixels = (int) (20 * scale + 0.5f);
        LinearLayout.LayoutParams arrow_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        arrow_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_arrow.setLayoutParams(arrow_params);
        bottom_arrow.setOrientation(LinearLayout.HORIZONTAL);

        bottom_arrow.setBackgroundResource(R.drawable.upper_traingle);

        value = bottom_arrow.getLayoutParams().height;

    }
}
