package tech.salroid.demo.instabadge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by salroid on 2/18/2017.
 */

public class InstaBadge extends LinearLayout {

    private Context context;
    private int dpAsPixels;
    private LinearLayout outer_container;
    private float scale;
    private ImageView badge_icon;
    private TextView badge_text;
    private LinearLayout bottom_arrow;


    //default badge color
    private String BADGE_COLOR = "#000000";
    private String ORIENTATION = "down";
    private boolean is_auto_hide = false;


    public InstaBadge(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public InstaBadge(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.InstaBadge, 0, 0);
        initAttributes(typedArray);
        typedArray.recycle();

        init();
    }

    public InstaBadge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.InstaBadge, 0, 0);
        initAttributes(typedArray);
        typedArray.recycle();

        init();
    }

    private void initAttributes(TypedArray typedArray) {

        String text_color = typedArray.getString(R.styleable.InstaBadge_setColor);
        String my_orientaion = typedArray.getString(R.styleable.InstaBadge_setLayoutOrientation);
        boolean auto_hide = typedArray.getBoolean(R.styleable.InstaBadge_autohide,false);

        if (text_color!=null && text_color.length()>0)
            BADGE_COLOR = text_color;

        if (my_orientaion!=null && my_orientaion.length()>0)
            ORIENTATION = my_orientaion;

            is_auto_hide = auto_hide;
    }


    private void init() {

        setupMainLayout();
        setupBadgeImage();
        setupBadgeText();
        addImageAndText();

        if (ORIENTATION.equals("up"))
        setupUpperArrow();
        else if (ORIENTATION.equals("down"))
        setupBottomArrow();

        if (is_auto_hide)
            hidelayout();


        showbadge(); //for green
        setupBadgeBackgroundColors(); //for green

    }

    private void showbadge() {
        setVisibility(VISIBLE);
    }  //forgreen

    private void hidelayout() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibility(GONE);
            }
        }, 3000); //forgreen

    }

    //manually set color of the badge
    public void setBadgeColor(String color){
        BADGE_COLOR = color;
    }

    //manually set the orientation of badge

    public void setLayoutOrientaion(String orientaion){
        ORIENTATION = orientaion;
    }

    private void setupBadgeBackgroundColors() {

        GradientDrawable gd = (GradientDrawable) outer_container.getBackground();
        gd.setColor(Color.parseColor(BADGE_COLOR));

        LayerDrawable layers = (LayerDrawable) bottom_arrow.getBackground();
        RotateDrawable rotate = (RotateDrawable) layers.findDrawableByLayerId(R.id.grad);
        GradientDrawable drawable = (GradientDrawable) rotate.getDrawable();
        drawable.setColor(Color.parseColor(BADGE_COLOR));

    }

    private void setupBottomArrow() {


        bottom_arrow = new LinearLayout(context);
        dpAsPixels = (int) (20 * scale + 0.5f);
        LayoutParams arrow_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        arrow_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_arrow.setLayoutParams(arrow_params);
        bottom_arrow.setOrientation(LinearLayout.HORIZONTAL);

        bottom_arrow.setBackgroundResource(R.drawable.triangle);
        addView(outer_container);
        addView(bottom_arrow);

    }

    private void setupUpperArrow() {


        bottom_arrow = new LinearLayout(context);
        dpAsPixels = (int) (20 * scale + 0.5f);
        LayoutParams arrow_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        arrow_params.gravity = Gravity.CENTER_HORIZONTAL;
        bottom_arrow.setLayoutParams(arrow_params);
        bottom_arrow.setOrientation(LinearLayout.HORIZONTAL);

        bottom_arrow.setBackgroundResource(R.drawable.upper_traingle);
        addView(bottom_arrow);
        addView(outer_container);


    }

    private void addImageAndText() {

        outer_container.setBackgroundResource(R.drawable.background);
        outer_container.addView(badge_icon);
        outer_container.addView(badge_text);
    }

    private void setupBadgeText() {

        badge_text = new TextView(context);
        LayoutParams text_params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        text_params.setMargins(dpAsPixels, 0, 0, 0);

        badge_text.setText("6");
        badge_text.setGravity(Gravity.CENTER);
        badge_text.setTextColor(Color.WHITE);
        badge_text.setLayoutParams(text_params);

    }

    private void setupBadgeImage() {

        badge_icon = new ImageView(context);
        dpAsPixels = (int) (16 * scale + 0.5f);
        LayoutParams badge_params = new LinearLayout.LayoutParams(dpAsPixels, dpAsPixels, 1f);
        dpAsPixels = (int) (2 * scale + 0.5f);
        badge_params.setMargins(0, 0, dpAsPixels, 0);
        badge_params.gravity = Gravity.CENTER;
        badge_icon.setLayoutParams(badge_params);
        badge_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));

    }

    private void setupMainLayout() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        setOrientation(VERTICAL);
        setLayoutParams(params);

        outer_container = new LinearLayout(context);
        outer_container.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 2f));
        outer_container.setOrientation(LinearLayout.HORIZONTAL);
        outer_container.setGravity(Gravity.CENTER_HORIZONTAL);

        scale = getResources().getDisplayMetrics().density;
        dpAsPixels = (int) (13 * scale + 0.5f);
        outer_container.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

    }



}
