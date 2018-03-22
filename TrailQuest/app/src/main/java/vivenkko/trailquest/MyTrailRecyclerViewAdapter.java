package vivenkko.trailquest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import vivenkko.trailquest.models.Trail;

public class MyTrailRecyclerViewAdapter extends RecyclerView.Adapter<MyTrailRecyclerViewAdapter.ViewHolder> {

    private final List<Trail> mValues;
    private Context ctx;

    public MyTrailRecyclerViewAdapter(Context context, List<Trail> items ) {
        mValues = items;
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

        holder.title.setText(mValues.get(position).getTitle());
        holder.description.setText(mValues.get(position).getDescription());
        holder.city.setText(mValues.get(position).getCity());
        holder.country.setText(mValues.get(position).getCountry());
        holder.rate.getNumStars();
        //holder.picture.setBackground(mValues.get(position).getPicture().);
        holder.distance.setText(mValues.get(position).getDistance().toString());
        holder.difficulty.setText(mValues.get(position).getDifficulty());
        //holder.locations.setText(mValues.get(position).getLocations());
        holder.author.setText(mValues.get(position).getAuthor().getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView description;
        public final TextView city;
        public final TextView country;
        public final RatingBar rate;
        //public final ImageView picture;
        public final TextView distance;
        public final TextView difficulty;
        //public final TextView locations;
        public final TextView author;

        public Trail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.textViewTrailDetailTitle);
            description = view.findViewById(R.id.textViewTrailDetailDescription);
            city = view.findViewById(R.id.textViewTrailDetailCity);
            country = view.findViewById(R.id.textViewTrailDetailTitle);
            rate = view.findViewById(R.id.ratingBarTrailDetail);
            //picture = view.findViewById(R.id.imageViewTrailDetailHeader);
            distance = view.findViewById(R.id.textViewTrailDetailDistance);
            difficulty = view.findViewById(R.id.textViewTrailDetailDistance);
            //locations = view.findViewById(R.id.textViewTrailDetailTitle);
            author = view.findViewById(R.id.textViewTrailDetailAuthor);
        }

        @Override
        public String toString() {
            return super.toString() + " ' '";
        }
    }
}
