package tech.salroid.demo.instabadge;

/**
 * Created by salroid on 3/26/2017.
 */

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


class CoordinatesFinder {


    static Point getCoordinates(final TextView tipView, ToolTip tooltip) {
        Point point = new Point();
        final Coordinates anchorViewCoordinates = new Coordinates(tooltip.getAnchorView());
        final Coordinates rootCoordinates = new Coordinates(tooltip.getRootView());

        tipView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        switch (tooltip.getPosition()) {
            case ToolTip.POSITION_ABOVE:
                point = getPositionAbove(tipView, tooltip,
                        anchorViewCoordinates, rootCoordinates);
                break;
            case ToolTip.POSITION_BELOW:
                point = getPositionBelow(tipView, tooltip,
                        anchorViewCoordinates, rootCoordinates);
                break;
            case ToolTip.POSITION_LEFT_TO:
                point = getPositionLeftTo(tipView, tooltip,
                        anchorViewCoordinates, rootCoordinates);
                break;
            case ToolTip.POSITION_RIGHT_TO:
                point = getPositionRightTo(tipView, tooltip,
                        anchorViewCoordinates, rootCoordinates);
                break;
        }

        // add user defined offset values
        point.x += UiUtils.isRtl() ? -tooltip.getOffsetX() : tooltip.getOffsetX();
        point.y += tooltip.getOffsetY();

        // coordinates retrieved are relative to 0,0 of the root layout
        // added view to root is subject to root padding
        // we need to subtract the top and left padding of root from coordinates. to adjust
        // top left tip coordinates
        point.x -= tooltip.getRootView().getPaddingLeft();
        point.y -= tooltip.getRootView().getPaddingTop();

        return point;

    }

    private static Point getPositionRightTo(TextView tipView, ToolTip toolTip, Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        Point point = new Point();
        point.x = anchorViewCoordinates.right;
        AdjustRightToOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        point.y = anchorViewCoordinates.top + getYCenteringOffset(tipView, toolTip);
        return point;
    }

    private static Point getPositionLeftTo(TextView tipView, ToolTip toolTip, Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        Point point = new Point();
        point.x = anchorViewCoordinates.left - tipView.getMeasuredWidth();
        AdjustLeftToOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        point.y = anchorViewCoordinates.top + getYCenteringOffset(tipView, toolTip);
        return point;
    }

    private static Point getPositionBelow(TextView tipView, ToolTip toolTip, Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        Point point = new Point();
        point.x = anchorViewCoordinates.left + getXOffset(tipView, toolTip);
        if (toolTip.alignedCenter()) {
            AdjustHorizontalCenteredOutOfBounds(tipView, toolTip.getRootView(), point, rootLocation);
        } else if (toolTip.alignedLeft()){
            AdjustHorizontalLeftAlignmentOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        } else if (toolTip.alignedRight()){
            AdjustHorizotalRightAlignmentOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        }
        point.y = anchorViewCoordinates.bottom;
        return point;
    }

    private static Point getPositionAbove(TextView tipView, ToolTip toolTip,
                                          Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        Point point = new Point();
        point.x = anchorViewCoordinates.left + getXOffset(tipView, toolTip);
        if (toolTip.alignedCenter()) {
            AdjustHorizontalCenteredOutOfBounds(tipView, toolTip.getRootView(), point, rootLocation);
        } else if (toolTip.alignedLeft()){
            AdjustHorizontalLeftAlignmentOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        } else if (toolTip.alignedRight()){
            AdjustHorizotalRightAlignmentOutOfBounds(tipView, toolTip.getRootView(), point, anchorViewCoordinates, rootLocation);
        }
        point.y = anchorViewCoordinates.top - tipView.getMeasuredHeight();
        return point;
    }

    private static void AdjustRightToOutOfBounds(TextView tipView, ViewGroup root, Point point, Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        ViewGroup.LayoutParams params = tipView.getLayoutParams();
        int availableSpace = rootLocation.right - root.getPaddingRight() - anchorViewCoordinates.right;
        if (point.x + tipView.getMeasuredWidth() > rootLocation.right - root.getPaddingRight()){
            params.width = availableSpace;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tipView.setLayoutParams(params);
            measureViewWithFixedWidth(tipView, params.width);
        }
    }

    private static void AdjustLeftToOutOfBounds(TextView tipView, ViewGroup root, Point point, Coordinates anchorViewCoordinates, Coordinates rootLocation) {
        ViewGroup.LayoutParams params = tipView.getLayoutParams();
        int rootLeft = rootLocation.left + root.getPaddingLeft();
        if (point.x < rootLeft){
            int availableSpace = anchorViewCoordinates.left - rootLeft;
            point.x = rootLeft;
            params.width = availableSpace;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tipView.setLayoutParams(params);
            measureViewWithFixedWidth(tipView, params.width);
        }
    }

    private static void AdjustHorizotalRightAlignmentOutOfBounds(TextView tipView, ViewGroup root,
                                                                 Point point, Coordinates anchorViewCoordinates,
                                                                 Coordinates rootLocation) {
        ViewGroup.LayoutParams params = tipView.getLayoutParams();
        int rootLeft = rootLocation.left + root.getPaddingLeft();
        if (point.x < rootLeft){
            int availableSpace = anchorViewCoordinates.right - rootLeft;
            point.x = rootLeft;
            params.width = availableSpace;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tipView.setLayoutParams(params);
            measureViewWithFixedWidth(tipView, params.width);
        }
    }

    private static void AdjustHorizontalLeftAlignmentOutOfBounds(TextView tipView, ViewGroup root,
                                                                 Point point, Coordinates anchorViewCoordinates,
                                                                 Coordinates rootLocation) {
        ViewGroup.LayoutParams params = tipView.getLayoutParams();
        int rootRight = rootLocation.right - root.getPaddingRight();
        if (point.x + tipView.getMeasuredWidth() > rootRight){
            params.width = rootRight - anchorViewCoordinates.left;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tipView.setLayoutParams(params);
            measureViewWithFixedWidth(tipView, params.width);
        }
    }

    private static void AdjustHorizontalCenteredOutOfBounds(TextView tipView, ViewGroup root,
                                                            Point point, Coordinates rootLocation) {
        ViewGroup.LayoutParams params = tipView.getLayoutParams();
        int rootWidth = root.getWidth() - root.getPaddingLeft() - root.getPaddingRight();
        if (tipView.getMeasuredWidth() > rootWidth) {
            point.x = rootLocation.left + root.getPaddingLeft();
            params.width = rootWidth;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tipView.setLayoutParams(params);
            measureViewWithFixedWidth(tipView, rootWidth);
        }
    }


    private static void measureViewWithFixedWidth(TextView tipView, int width) {
        tipView.measure(View.MeasureSpec.makeMeasureSpec(width,
                View.MeasureSpec.EXACTLY), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * calculate the amount of movement need to be taken inorder to align tip
     * on X axis according to "align" parameter
     * @return int
     */
    private static int getXOffset(View tipView, ToolTip toolTip) {
        int offset;

        switch (toolTip.getAlign()) {
            case ToolTip.ALIGN_CENTER:
                offset = ((toolTip.getAnchorView().getWidth() - tipView.getMeasuredWidth()) / 2);
                break;
            case ToolTip.ALIGN_LEFT:
                offset = 0;
                break;
            case ToolTip.ALIGN_RIGHT:
                offset = toolTip.getAnchorView().getWidth() - tipView.getMeasuredWidth();
                break;
            default:
                offset = 0;
                break;
        }

        return offset;
    }

    /**
     * calculate the amount of movement need to be taken inorder to center tip
     * on Y axis
     * @return int
     */
    private static int getYCenteringOffset(View tipView, ToolTip toolTip) {
        return (toolTip.getAnchorView().getHeight() - tipView.getMeasuredHeight()) / 2;
    }

}
