package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projet4a.ensim.deezerapplication.AlbumActivity;
import com.projet4a.ensim.deezerapplication.R;
import com.projet4a.ensim.deezerapplication.service.Author;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Cordon Paul on 07/11/2017.
 */
public class ArtistAdapteur extends RecyclerView.Adapter<ArtistAdapteur.ViewHolder>{
    private List<Author> mDataset;
    final Intent intent;
    private Context c;
    private static final String TAG = "ArtistAdapteur";
    public ArtistAdapteur(Context context, List<Author> data) {
        intent=new Intent(context,AlbumActivity.class);
        mDataset=data;
        c=context;

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView mTextView;
        public ImageView imageView;
        public View v;
        public ViewHolder(View v) {
            super(v);
            this.v=v;
            mTextView = v.findViewById(R.id.nameArtist);
            imageView = v.findViewById(R.id.imageArtist);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ArtistAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        final Intent intent=new Intent(parent.getContext(),AlbumActivity.class);
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_artist, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Author author=mDataset.get(position);
        Log.d(TAG, "url : " + author.getPicture_big());
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());
        Picasso.with(holder.v.getContext()).load(author.getPicture_big()).into(holder.imageView);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"click on "+author.getName());
                intent.putExtra("nomArtist",holder.mTextView.getText());
                c.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
