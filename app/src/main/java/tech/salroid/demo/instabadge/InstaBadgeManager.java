package tech.salroid.demo.instabadge;

import android.graphics.Point;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by salroid on 3/26/2017.
 */

public class InstaBadgeManager {

    public static final String TAG = InstaBadgeManager.class.getSimpleName();
    private LinearLayout bottom_dot;

    public View show(InstaBadge instaBadge) {

        View instaBadgeView = create(instaBadge);

        if (instaBadgeView == null) {
            return null;
        }

        AnimationUtils.popup(instaBadgeView, 1000).start();

        return instaBadgeView;
    }

    private View create(InstaBadge instaBadge) {

        if (instaBadge.getAnchorView() == null) {
            Log.e(TAG, "Unable to create a view, anchor view is null");
            return null;
        }

        if (instaBadge.getRootView() == null) {
            Log.e(TAG, "Unable to create a view, root layout is null");
            return null;
        }

       /* bottom_dot = new LinearLayout(instaBadge.getContext());
        LinearLayout.LayoutParams dot_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dot_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_dot.setLayoutParams(dot_params);
        bottom_dot.setOrientation(LinearLayout.HORIZONTAL);
        bottom_dot.setBackgroundResource(R.drawable.circular_dot);*/


        InstaBadgeView instaBadgeView = new InstaBadgeView(instaBadge.getContext(), instaBadge);


        instaBadge.getRootView().addView(instaBadgeView);
        //instaBadge.getRootView().addView(bottom_dot);

        Point p = CoordinatesFinder.getCoordinates(instaBadgeView,instaBadge);

        moveBadgeToCorrectPosition(instaBadgeView, p ,bottom_dot,instaBadge);

        int anchorViewId = instaBadge.getAnchorView().getId();
        instaBadgeView.setTag(anchorViewId);



        return instaBadgeView;
    }



    private void moveBadgeToCorrectPosition(InstaBadgeView instaBadgeView, Point p, View bottom_dot,InstaBadge instaBadge) {
        Coordinates coordinates = new Coordinates(instaBadgeView);
        int translationX = p.x - coordinates.left;
        int translationY = p.y  - coordinates.top;
        instaBadgeView.setTranslationX(translationX);
        instaBadgeView.setTranslationY(translationY);
        //bottom_dot.setTranslationY(p.y-2*96);
    }


}
