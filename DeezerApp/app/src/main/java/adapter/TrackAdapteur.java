package adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projet4a.ensim.deezerapplication.R;
import com.projet4a.ensim.deezerapplication.TrackActivity;
import com.projet4a.ensim.deezerapplication.service.Track;


import java.io.IOException;
import java.util.List;

/**
 * Created by Cordon Paul on 07/11/2017.
 */
public class TrackAdapteur extends RecyclerView.Adapter<TrackAdapteur.ViewHolder>{
    private List<Track> mDataset;
    final Intent intent;
    private Context c;
    private static final String TAG = "TrackAdapteur";

    public static MediaPlayer getmP() {
        return mP;
    }

    private static MediaPlayer mP= new MediaPlayer();

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ProgressBar progressBar;
        public ImageView btnPlay;
        public ImageView btnPause;

        public View v;
        public ViewHolder(View itemView) {
            super(itemView);
            this.v=itemView;
            mTextView = v.findViewById(R.id.nameTrack);
            progressBar = v.findViewById(R.id.progressBar);
            btnPlay = v.findViewById(R.id.BtnPlay);
            btnPause = v.findViewById(R.id.BtnPause);

        }
    }
    public TrackAdapteur(Context context, List<Track> data) {
        intent=new Intent(context, TrackActivity.class);
        mDataset=data;
        c=context;
    }

    @NonNull
    @Override
    public TrackAdapteur.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_track, parent, false);

        TrackAdapteur.ViewHolder vh = new TrackAdapteur.ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull final TrackAdapteur.ViewHolder holder, int position) {
        final Track track=mDataset.get(position);
        Log.d(TAG, "url : " + track.getTitle());
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getTitle());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"click on play"+track.getPreview());

                if(mP.isPlaying()){
                    holder.btnPlay.setVisibility(View.VISIBLE);
                    holder.btnPause.setVisibility(View.INVISIBLE);

                    mP.stop();
                    mP.reset();

                }
                else{
                    try {
                        holder.btnPlay.setVisibility(View.INVISIBLE);
                        holder.btnPause.setVisibility(View.VISIBLE);
                        
                        mP.setDataSource(c, Uri.parse(track.getPreview()));
                        mP.prepare();
                        mP.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
