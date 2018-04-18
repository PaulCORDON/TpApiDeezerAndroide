package com.projet4a.ensim.deezerapplication.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

/**
 * Created by Cordon Paul on 07/11/2017.
 */
public enum DeezerService {
    INSTANCE;

    private static final String TAG = "DeezerService";

    public void searchAuthor(String artist, Response.Listener respListener,Response.ErrorListener errorListener,Context context){
        final String url="https://api.deezer.com/search/artist?q="+artist;
        Log.d(TAG,url);
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<DataSearchAuthor> request =
                new GsonRequest<>(url,DataSearchAuthor.class,null,respListener,errorListener);
        queue.add(request);
    }

    public void searchAlbum(String name, Response.Listener respListener,Response.ErrorListener errorListener,Context context){
        final String url="http://api.deezer.com/search/album?q="+name;
        Log.d(TAG,url);
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<DataSearchListAlbum> request =
                new GsonRequest<>(url,DataSearchListAlbum.class,null,respListener,errorListener);
        queue.add(request);
    }

    public void searchTrack(int id, Response.Listener respListener,Response.ErrorListener errorListener,Context context){
        final String url="https://api.deezer.com/album/"+id+"/tracks";
        Log.d(TAG,url);
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<DataSearchAlbum.Tracks> request =
                new GsonRequest<>(url,DataSearchAlbum.Tracks.class,null,respListener,errorListener);
        queue.add(request);

    }


}
