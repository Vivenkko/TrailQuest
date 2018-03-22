package vivenkko.trailquest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vivenkko.trailquest.models.Event;

public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;
    private Context ctx;

    public MyEventRecyclerViewAdapter(Context context, List<Event> items ) {
        mValues = items;
        ctx = context;
    }

    @Override
    public MyEventRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new MyEventRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyEventRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.title.setText(mValues.get(position).getTitle());
        holder.description.setText(mValues.get(position).getDescription());
        holder.place.setText(mValues.get(position).getPlace());
        holder.country.setText(mValues.get(position).getCountry());
        holder.date.setText(mValues.get(position).getDate().toString());
        //holder.picture.setBackground(mValues.get(position).getPicture().);
        //holder.locations.setText(mValues.get(position).getLocations());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView description;
        public final TextView place;
        public final TextView country;
        public final TextView date;
        //public final ImageView picture;
        //public final TextView location;

        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.textViewEventDetailTitle);
            description = view.findViewById(R.id.textViewEventDetailDescription);
            place = view.findViewById(R.id.textViewEventPlace);
            country = view.findViewById(R.id.textViewEventCountry);
            date = view.findViewById(R.id.textViewEventDate);
            //picture = view.findViewById(R.id.imageViewTrailDetailHeader);
            //location = view.findViewById(R.id.textViewTrailDetailTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " ' '";
        }
    }

}
