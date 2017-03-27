package tech.salroid.demo.instabadge;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


class CoordinatesFinder {


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
        AdjustHorizontalCenteredOutOfBounds(instaBadgeView, instaBadge.getRootView(), point, rootCoordinates);
         point.y -= instaBadge.getRootView().getPaddingTop();

        return point;
    }

    private static Point getPositionAbove(View instaBadgeView, InstaBadge instaBadge, Coordinates anchorViewCoordinates, Coordinates rootCoordinates) {
        Point point = new Point();
        point.x = anchorViewCoordinates.left + getXOffset(instaBadgeView,instaBadge);
        point.y = anchorViewCoordinates.top - instaBadgeView.getMeasuredHeight();
        return point;

    }

    private static void AdjustHorizontalCenteredOutOfBounds(View instaBadgeView, ViewGroup rootView, Point point, Coordinates rootCoordinates) {
        ViewGroup.LayoutParams params = instaBadgeView.getLayoutParams();
        int rootWidth = rootView.getWidth() - rootView.getPaddingLeft() - rootView.getPaddingRight();
        if (instaBadgeView.getMeasuredWidth() > rootWidth) {
            point.x = rootCoordinates.left + rootView.getPaddingLeft();
            params.width = rootWidth;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            instaBadgeView.setLayoutParams(params);
            measureViewWithFixedWidth(instaBadgeView, rootWidth);
        }
    }

    private static void measureViewWithFixedWidth(View instaBadgeView, int rootWidth) {
        instaBadgeView.measure(View.MeasureSpec.makeMeasureSpec(rootWidth,
                View.MeasureSpec.EXACTLY), ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private static int getXOffset(View instaBadgeView , InstaBadge instaBadge) {
        int offset;
        offset = ((instaBadge.getAnchorView().getWidth() - instaBadgeView.getMeasuredWidth()) / 2);
        return offset;
    }


}
