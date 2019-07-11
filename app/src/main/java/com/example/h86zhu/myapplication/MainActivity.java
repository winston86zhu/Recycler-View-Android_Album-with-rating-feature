package com.example.h86zhu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IView{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public Model model;
    public RecyclerView recList;
    public TopBar tbar;
    public ArrayList<Card> card_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        card_list = new ArrayList<>();

        this.model = new Model(this);
        this.model.addObserver(this);
        tbar = new TopBar(this, model);



        recList.setLayoutManager(llm);
        Card_Adpater ca = new Card_Adpater(this,model);
        recList.setAdapter(ca);

        try {
            model.pre_load();
            tbar.loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }



/*

        //dispatch to disfferent classes

        /*model = new Model(this);
        try {
            model.pre_load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.single_card);
        FloatingActionButton fl_button = (FloatingActionButton) findViewById(R.id.float_button);

        image_gal.add((CardView) findViewById(R.id.cd_view_1));
        image_gal.add((CardView) findViewById(R.id.cd_view_2));*/

       /* RatingBar rb = findViewById(R.id.b1);
        rb.getNumStars();

        CardView cv = (CardView) findViewById(R.id.cd_view_1);
        cv.getChildAt(1);*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateView() {
        ((Card_Adpater)this.recList.getAdapter()).refresh_image();
    }
}
