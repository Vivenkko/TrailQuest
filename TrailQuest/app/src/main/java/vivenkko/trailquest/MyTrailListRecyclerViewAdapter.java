package vivenkko.trailquest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vivenkko.trailquest.fragments.OnListFragmentInteractionListener;
import vivenkko.trailquest.models.Trail;


public class MyTrailListRecyclerViewAdapter extends RecyclerView.Adapter<MyTrailListRecyclerViewAdapter.ViewHolder> {

    private final List<Trail> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context ctx;

    public MyTrailListRecyclerViewAdapter(Context context,List<Trail> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_trail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.title.setText(holder.mItem.getTitle());
        //holder.description.setText(mValues.get(position).getDescription());
        holder.city.setText(holder.mItem.getCity());
        holder.country.setText(holder.mItem.getCountry());
//        holder.rate.setNumStars(holder.mItem.getRate().intValue());
        //holder.picture.setBackground(mValues.get(position).getPicture().);
        holder.distance.setText(String.valueOf(holder.mItem.getDistance()));
        holder.difficulty.setText(holder.mItem.getDifficulty());
        //holder.locations.setText(mValues.get(position).getLocations());
        //holder.author.setText(mValues.get(position).getAuthor().getDisplayName());
        Picasso.with(ctx).load(holder.mItem.getPicture()).into(holder.picture);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        //public final TextView description;
        public final TextView city;
        public final TextView country;
        public final RatingBar rate;
        public final ImageView picture;
        public final TextView distance;
        public final TextView difficulty;
        //public final TextView locations;
        //public final TextView author;
        public Trail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.textViewTrailTitle);
            //description = view.findViewById(R.id.textViewTrailDescription);
            city = view.findViewById(R.id.textViewTrailCity);
            country = view.findViewById(R.id.textViewTrailCountry);
            rate = view.findViewById(R.id.ratingBarTrail);
            picture = view.findViewById(R.id.imageViewTrailBackground);
            distance = view.findViewById(R.id.textViewTrailDistance);
            difficulty = view.findViewById(R.id.textViewTrailDifficulty);
            //locations = view.findViewById(R.id.textViewTrailDetailTitle);
            //author = view.findViewById(R.id.textViewTrailDetailAuthor);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
