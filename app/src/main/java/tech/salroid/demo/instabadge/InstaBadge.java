package tech.salroid.demo.instabadge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by salroid on 2/18/2017.
 */

public class InstaBadge extends LinearLayout {

    public InstaBadge(Context context) {
        super(context);
        init(context);
    }

    public InstaBadge(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InstaBadge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setOrientation(VERTICAL);
        setLayoutParams(params);

        LinearLayout outer_container = new LinearLayout(context);
        outer_container.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2f));
        outer_container.setOrientation(LinearLayout.HORIZONTAL);
        outer_container.setGravity(Gravity.CENTER_HORIZONTAL);

        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (13 * scale + 0.5f);
        outer_container.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

        ImageView badge_icon = new ImageView(context);
        dpAsPixels = (int) (16 * scale + 0.5f);
        LayoutParams badge_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        dpAsPixels = (int) (2 * scale + 0.5f);
        badge_params.setMargins(0, 0, dpAsPixels, 0);
        badge_params.gravity = Gravity.CENTER;
        badge_icon.setLayoutParams(badge_params);
        badge_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));

        TextView badge_text = new TextView(context);
        LayoutParams text_params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        text_params.setMargins(dpAsPixels, 0, 0, 0);

        /*DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        float pixelSize = (int) 10 * dm.scaledDensity;
        badge_text.setTextSize(pixelSize);*/

        badge_text.setText("6");
        badge_text.setGravity(Gravity.CENTER);
        badge_text.setTextColor(Color.WHITE);
        badge_text.setLayoutParams(text_params);

        outer_container.setBackgroundResource(R.drawable.background);
        outer_container.addView(badge_icon);
        outer_container.addView(badge_text);


        LinearLayout bottom_arrow = new LinearLayout(context);
        dpAsPixels = (int) (20 * scale + 0.5f);
        LayoutParams arrow_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        arrow_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_arrow.setLayoutParams(arrow_params);
        bottom_arrow.setOrientation(LinearLayout.HORIZONTAL);

        bottom_arrow.setBackgroundResource(R.drawable.triangle);
        addView(outer_container);
        addView(bottom_arrow);

        GradientDrawable gd = (GradientDrawable) outer_container.getBackground();
        gd.setColor(Color.parseColor("#26A69A"));

        LayerDrawable layers = (LayerDrawable) bottom_arrow.getBackground();
        RotateDrawable rotate = (RotateDrawable) layers.findDrawableByLayerId(R.id.grad);
        GradientDrawable drawable = (GradientDrawable) rotate.getDrawable();
        drawable.setColor(Color.parseColor("#26A69A"));;

    }

}
