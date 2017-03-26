package tech.salroid.demo.instabadge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CustomTab extends AppCompatActivity {

    ImageView home,search,fav,user;
    String TAG = CustomTab.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);



            RelativeLayout main_content = (RelativeLayout) findViewById(R.id.activity_custom_tab);
            home = (ImageView) findViewById(R.id.home);
            search =(ImageView) findViewById(R.id.search);
            fav = (ImageView) findViewById(R.id.fav);
            user = (ImageView) findViewById(R.id.perm);

            InstaBadgeManager instaBadgeManager = new InstaBadgeManager();
            InstaBadge.Builder builder = new InstaBadge.Builder(this,fav,main_content);
            builder.setBadgeColor("#F44336");
            builder.setAuto_hideable(false);

            instaBadgeManager.show(builder.build());

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Coordinates anchorViewCoordinates = new Coordinates(home);

                    Log.d(TAG, "onClick home: "+anchorViewCoordinates.left+" "+anchorViewCoordinates.top);
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

                    final Coordinates anchorViewCoordinates = new Coordinates(fav);

                    Log.d(TAG, "onClick fav: "+anchorViewCoordinates.left+" "+anchorViewCoordinates.top);
                }
            });

            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

    }
}
