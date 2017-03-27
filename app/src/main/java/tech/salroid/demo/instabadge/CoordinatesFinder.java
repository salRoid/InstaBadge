package tech.salroid.demo.instabadge;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


class CoordinatesFinder {

    static String TAG = CoordinatesFinder.class.getSimpleName();

    static Point getCoordinates(final View instaBadgeView, InstaBadge instaBadge) {

        Point point = new Point();
        final Coordinates anchorViewCoordinates = new Coordinates(instaBadge.getAnchorView());
        final Coordinates rootCoordinates = new Coordinates(instaBadge.getRootView());

        instaBadgeView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        point = getPositionAbove(instaBadgeView, instaBadge,
                anchorViewCoordinates, rootCoordinates);

        point.x += instaBadge.getOffsetX();
        point.y += instaBadge.getOffsetY();

         point.x -= instaBadge.getRootView().getPaddingLeft();
         point.y -= instaBadge.getRootView().getPaddingTop();

         // manual co - ordinates
        point.x = 550;
        point.y = 1350;


        return point;
    }

    private static Point getPositionAbove(View instaBadgeView, InstaBadge instaBadge, Coordinates anchorViewCoordinates, Coordinates rootCoordinates) {
        Point point = new Point();
        point.x = anchorViewCoordinates.left + getXOffset(instaBadge);
        point.y = anchorViewCoordinates.top - instaBadgeView.getMeasuredHeight();
        return point;

    }

    private static int getXOffset(InstaBadge instaBadge) {
        int offset;
        offset = ((instaBadge.getAnchorView().getWidth()) / 2);
        return offset;
    }


}
