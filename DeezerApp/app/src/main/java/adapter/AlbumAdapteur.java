package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projet4a.ensim.deezerapplication.AlbumActivity;
import com.projet4a.ensim.deezerapplication.R;
import com.projet4a.ensim.deezerapplication.TrackActivity;
import com.projet4a.ensim.deezerapplication.service.Album;
import com.projet4a.ensim.deezerapplication.service.Author;
import com.projet4a.ensim.deezerapplication.service.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Cordon Paul on 07/11/2017.
 */
public class AlbumAdapteur extends RecyclerView.Adapter<AlbumAdapteur.ViewHolder>{
    private List<Album> mDataset;
    final Intent intent;
    private Context c;
    private static final String TAG = "AlbumAdapteur";

    public AlbumAdapteur(Context context, List<Album> data) {
        intent=new Intent(context, TrackActivity.class);
        mDataset=data;
        c=context;
    }

    @NonNull
    @Override
    public AlbumAdapteur.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_album, parent, false);

            AlbumAdapteur.ViewHolder vh = new AlbumAdapteur.ViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapteur.ViewHolder holder, int position) {
        final Album album=mDataset.get(position);
        Log.d(TAG, "url : " + album.getCover_big());
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getTitle());
        Picasso.with(holder.v.getContext()).load(album.getCover_big()).into(holder.imageView);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"click on "+album.getTitle());
                intent.putExtra("idAlbum",album.getId());
                intent.putExtra("couvertureAlbum",album.getCover_medium());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView imageView;
        public View v;
        public ViewHolder(View itemView) {
            super(itemView);
            this.v=itemView;
            mTextView = v.findViewById(R.id.nameAlbum);
            imageView = v.findViewById(R.id.imageAlbum);
        }
    }
}
