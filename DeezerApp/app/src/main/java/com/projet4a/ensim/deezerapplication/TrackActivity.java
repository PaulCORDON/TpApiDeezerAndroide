package com.projet4a.ensim.deezerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.projet4a.ensim.deezerapplication.service.DataSearchAlbum;
import com.projet4a.ensim.deezerapplication.service.DataSearchListAlbum;
import com.projet4a.ensim.deezerapplication.service.DeezerService;
import com.projet4a.ensim.deezerapplication.service.Track;
import com.squareup.picasso.Picasso;

import adapter.AlbumAdapteur;
import adapter.TrackAdapteur;

public class TrackActivity extends AppCompatActivity {
    private static final String TAG = "TrackActivity";


    private String CoverAlbum;
    private int idAlbum;
    private ImageView albumCover;
    TrackAdapteur adapteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
            albumCover = (ImageView) this.findViewById(R.id.imageAlbum);
            Intent intent = getIntent();
            idAlbum=intent.getIntExtra("idAlbum",0);
            CoverAlbum=intent.getStringExtra("couvertureAlbum");
            final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            Picasso.with(this).load(CoverAlbum).into(albumCover);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)

            Response.Listener<DataSearchAlbum.Tracks> responseListener = new Response.Listener<DataSearchAlbum.Tracks>() {
                @Override
                public void onResponse(DataSearchAlbum.Tracks response) {
                    Log.d(TAG, response.toString());
                    adapteur = new TrackAdapteur(TrackActivity.this, response.getData());
                    mRecyclerView.setAdapter(adapteur);
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "VolleyError");
                }
            };
            DeezerService.INSTANCE.searchTrack(idAlbum, responseListener, errorListener, this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG+" OnStop","normalment il y a plus de musique");
        adapteur.getmP().reset();


    }



}
