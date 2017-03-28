package tech.salroid.demo.instabadge;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CustomTab extends AppCompatActivity {

    ImageView home, search, fav, user, love;
    String TAG = CustomTab.class.getSimpleName();
    RelativeLayout main_content;
    InstaBadgeManager instaBadgeManager,instaBadgeManagerab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        main_content = (RelativeLayout) findViewById(R.id.activity_custom_tab);
        home = (ImageView) findViewById(R.id.home);
        search = (ImageView) findViewById(R.id.search);
        fav = (ImageView) findViewById(R.id.fav);
        user = (ImageView) findViewById(R.id.perm);
        love = (ImageView) findViewById(R.id.my_love);

        instaBadgeManager = new InstaBadgeManager();
        instaBadgeManagerab = new InstaBadgeManager();


        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Coordinates anchorViewCoordinates = new Coordinates(love);
                Log.d(TAG, "onClick: "+anchorViewCoordinates.bottom+" "+anchorViewCoordinates.top);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);



        InstaBadge.Builder builder = new InstaBadge.Builder(this, fav, main_content);
        builder.setBadgeColor("#F44336");
        builder.setAuto_hideable(true);
        builder.setArrow_postion("down");
        builder.setText(10);
        builder.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp));

        instaBadgeManager.show(builder.build());


        InstaBadge.Builder builder1 = new InstaBadge.Builder(this, love, main_content);
        builder1.setBadgeColor("#F44336");
        builder1.setAuto_hideable(false);
        builder1.setArrow_postion("up");

        instaBadgeManagerab.show(builder1.build());
    }
}
