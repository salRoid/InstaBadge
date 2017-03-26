package tech.salroid.demo.instabadge;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by salroid on 3/26/2017.
 */

public class InstaBadge {

    private Context context;
    private View anchorView;
    private ViewGroup rootView;
    private int mOffsetX;
    private int mOffsetY;
    private String badgeColor;
    private String arrow_postion;
    private boolean auto_hideable;

    public InstaBadge(Builder builder) {
        context = builder.context;
        anchorView = builder.anchorView;
        rootView = builder.rootView;
        mOffsetX = builder.mOffsetX;
        mOffsetY = builder.mOffsetY;
        badgeColor = builder.badgeColor;
        arrow_postion = builder.arrow_postion;
        auto_hideable = builder.auto_hideable;
    }

    public Context getContext() {
        return context;
    }

    public View getAnchorView() {
        return anchorView;
    }

    public ViewGroup getRootView() {
        return rootView;
    }

    public int getmOffsetX() {
        return mOffsetX;
    }

    public int getmOffsetY() {
        return mOffsetY;
    }

    public String getBadgeColor() {
        return badgeColor;
    }

    public String getArrow_postion() {
        return arrow_postion;
    }

    public boolean auto_hide() {
        return auto_hideable;
    }


    public static class Builder {

        private Context context;
        private View anchorView;
        private ViewGroup rootView;
        private int mOffsetX;
        private int mOffsetY;
        private String badgeColor;
        private String arrow_postion;
        private boolean auto_hideable;

        public Builder(Context context, View anchorView, ViewGroup rootView) {
            this.context = context;
            this.anchorView = anchorView;
            this.rootView = rootView;
            mOffsetX = 0;
            mOffsetY = 0;
            badgeColor = "#000";
            arrow_postion = "down";
            auto_hideable = false;
        }

        public Builder setmOffsetX(int OffsetX) {
            mOffsetX = OffsetX;
            return this;
        }

        public Builder setmOffsetY(int OffsetY) {
            mOffsetY = OffsetY;
            return this;
        }

        public Builder setBadgeColor(String bg_color) {
            badgeColor = bg_color;
            return this;
        }

        public Builder setArrow_postion(String postion) {
            arrow_postion = postion;
            return this;
        }

        public Builder setAuto_hideable(boolean value) {
            auto_hideable = value;
            return this;
        }

        public InstaBadge build() {
            return new InstaBadge(this);
        }
    }
}
