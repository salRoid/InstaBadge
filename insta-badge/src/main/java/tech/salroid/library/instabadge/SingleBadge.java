package tech.salroid.library.instabadge;

import android.graphics.drawable.Drawable;

/**
 * Created by R Ankit on 14-05-2017.
 */

public class SingleBadge {

    private String type;
    private String text;
    private Drawable drawable;

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getText() {
        return text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        SingleBadge sms = (SingleBadge) obj;
        return type.equals(sms.type);
    }
}
