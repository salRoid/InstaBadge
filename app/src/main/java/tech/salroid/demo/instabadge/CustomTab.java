package tech.salroid.demo.instabadge;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CustomTab extends AppCompatActivity {

    ImageView home,search,fav,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);

            home = (ImageView) findViewById(R.id.home);
            search =(ImageView) findViewById(R.id.search);
            fav = (ImageView) findViewById(R.id.fav);
            user = (ImageView) findViewById(R.id.perm);

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
}
