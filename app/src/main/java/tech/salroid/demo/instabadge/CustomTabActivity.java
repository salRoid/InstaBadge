package tech.salroid.demo.instabadge;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import tech.salroid.library.instabadge.InstaBadge;
import tech.salroid.library.instabadge.InstaBadgeManager;
import tech.salroid.library.instabadge.InstaBadgeView;
import tech.salroid.library.instabadge.SingleBadge;

public class CustomTabActivity extends AppCompatActivity {

    private ImageView home, search, fav, user, love;
    private String TAG = CustomTabActivity.class.getSimpleName();
    private RelativeLayout main_content;
    private InstaBadgeManager instaBadgeManager;
    private RecyclerView recyclerView;
    private List<String> list;
    private InstaBadge.Builder builder;
    private Handler handler;

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
        love = (ImageView) findViewById(R.id.love);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        list.add("brother");
        list.add("girlfriend");
        list.add("long");
        list.add("distance");
        list.add("broke-up");
        list.add("close");
        list.add("work");
        list.add("destination");
        list.add("wo");
        list.add("varanasi");
        list.add("se");
        list.add("hai");


        TestRecyclerAdapter a = new TestRecyclerAdapter(this,list);
        recyclerView.setAdapter(a);

        instaBadgeManager = new InstaBadgeManager();


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


        SingleBadge singleBadge = new SingleBadge();
        singleBadge.setType("LIKE");
        singleBadge.setText("4");
        singleBadge.setDrawable(ContextCompat.getDrawable(this,R.drawable.ic_star_white_24dp));

        builder = new InstaBadge.Builder(this, fav, main_content);
        builder.setBadgeColor("#F44336");
        builder.setAuto_hideable(true);
        builder.setArrow_postion("down");
        builder.addBadge(singleBadge);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                instaBadgeManager.show(builder.build());
                showAgain();

            }
        },2000);

        instaBadgeManager.setInstaBadgeClickListener(new InstaBadgeView.InstaBadgeViewClickListener() {
            @Override
            public void instaBadgeClicked() {
                startActivity(new Intent(CustomTabActivity.this,MainActivity.class));
            }
        });


    }

    public void showAgain(){

        SingleBadge singleBadge2 = new SingleBadge();
        singleBadge2.setType("COMMENT");
        singleBadge2.setText("1");
        singleBadge2.setDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite_white_24dp));

        builder.addBadge(singleBadge2);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                instaBadgeManager.show(builder.build());

            }
        },2000);
    }
}
