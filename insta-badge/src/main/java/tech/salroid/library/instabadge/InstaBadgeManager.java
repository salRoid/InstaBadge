package tech.salroid.library.instabadge;

import android.graphics.Point;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tech.salroid.instabadge.R;

/**
 * Created by salroid on 3/26/2017.
 */

public class InstaBadgeManager {

    private static final String TAG = InstaBadgeManager.class.getSimpleName();
    private LinearLayout bottom_dot;
    private InstaBadgeView.InstaBadgeViewClickListener instaBadgeViewClickListener;
    private InstaBadgeView instaBadgeView;

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

        bottom_dot = new LinearLayout(instaBadge.getContext());
        LinearLayout.LayoutParams dot_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dot_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_dot.setLayoutParams(dot_params);
        bottom_dot.setOrientation(LinearLayout.HORIZONTAL);
        bottom_dot.setBackgroundResource(R.drawable.circular_dot);

        instaBadgeView = new InstaBadgeView(instaBadge.getContext(), instaBadge);

        instaBadge.getRootView().addView(instaBadgeView);
        instaBadge.getRootView().addView(bottom_dot);

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
        if(instaBadge.getArrow_postion()==1)
        bottom_dot.setTranslationY(p.y-96-48);
        else
            bottom_dot.setTranslationY(p.y+96+48-4*bottom_dot.getLayoutParams().height);
        bottom_dot.setTranslationX(p.x+(instaBadge.getAnchorView().getWidth())
                + getXOffset(bottom_dot,instaBadge)-
                2*bottom_dot.getLayoutParams().width);
    }

    public  void setInstaBadgeClickListener(InstaBadgeView.InstaBadgeViewClickListener instaBadgeClickListener) {

        if (instaBadgeView!=null)
            instaBadgeView.setInstaBadgeClickListener(instaBadgeClickListener);

    }

    private static int getXOffset(View instaBadgeView , InstaBadge instaBadge) {
        int offset;
        offset = ((instaBadge.getAnchorView().getWidth() - instaBadgeView.getMeasuredWidth()) / 2);
        return offset;
    }

}
