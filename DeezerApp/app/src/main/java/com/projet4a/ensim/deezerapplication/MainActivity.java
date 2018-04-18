package com.projet4a.ensim.deezerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.projet4a.ensim.deezerapplication.service.DataSearchAuthor;
import com.projet4a.ensim.deezerapplication.service.DeezerService;

import adapter.ArtistAdapteur;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        Response.Listener<DataSearchAuthor> responseListener = new Response.Listener<DataSearchAuthor>() {
            @Override
            public void onResponse(DataSearchAuthor response) {
                Log.d(TAG,response.toString());
                ArtistAdapteur adapteur=new ArtistAdapteur(MainActivity.this,response.getData());
                mRecyclerView.setAdapter(adapteur);
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"VolleyError");
            }
        };
        DeezerService.INSTANCE.searchAuthor("muse", responseListener, errorListener,this);
    }
}
