package tech.salroid.demo.instabadge;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

/**
 * Created by salroid on 3/26/2017.
 */

public class InstaBadgeManager {

    public static final String TAG = InstaBadgeManager.class.getSimpleName();

    public View show(InstaBadge instaBadge) {

        View instaBadgeView = create(instaBadge);

        if (instaBadgeView == null) {
            return null;
        }
        instaBadgeView.setVisibility(View.VISIBLE);

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

        InstaBadgeView instaBadgeView = new InstaBadgeView(instaBadge.getContext(), instaBadge);

        instaBadge.getRootView().addView(instaBadgeView);

        Point p = CoordinatesFinder.getCoordinates(instaBadgeView,instaBadge);

        moveBadgeToCorrectPosition(instaBadgeView, p);

        final Coordinates anchorViewCoordinates = new Coordinates(instaBadge.getAnchorView());
        Log.d(TAG, "create: anchor" +anchorViewCoordinates.left+ " "+anchorViewCoordinates.top);

        int anchorViewId = instaBadge.getAnchorView().getId();
        instaBadgeView.setTag(anchorViewId);

        return instaBadgeView;
    }

    private void moveBadgeToCorrectPosition(InstaBadgeView instaBadgeView, Point p) {
        Coordinates coordinates = new Coordinates(instaBadgeView);
        int translationX = p.x - coordinates.left;
        int translationY = p.y - coordinates.top;
        instaBadgeView.setTranslationX(!UiUtils.isRtl() ? translationX : -translationX);
        instaBadgeView.setTranslationY(translationY);
    }


}
