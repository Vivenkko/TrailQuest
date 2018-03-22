package vivenkko.trailquest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.trailquest.models.Trail;
import vivenkko.trailquest.retrofit.TrailQuestController;
import vivenkko.trailquest.retrofit.TrailQuestServiceGenerator;


public class TrailFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TrailFragment() {
    }

    public static TrailFragment newInstance(int columnCount) {
        TrailFragment fragment = new TrailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trail_list, container, false);

        String token = getArguments().getString("token");

        TrailQuestController api = TrailQuestServiceGenerator.createService(TrailQuestController.class);

//        if (/*estoy en la pestaña 1*/ 1 != 0) {
            Call<List<Trail>> call = api.listTrails(token);

            call.enqueue(new Callback<List<Trail>>() {
                @Override
                public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                    if (response.isSuccessful()){
                        List<Trail> trails = response.body();
                        if (view instanceof RecyclerView) {
                            Context context = view.getContext();
                            RecyclerView recyclerView = (RecyclerView) view;
                            if (mColumnCount <= 1) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            } else {
                                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                            }
                            recyclerView.setAdapter(new MyTrailRecyclerViewAdapter(getActivity(), trails));
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Trail>> call, Throwable t) {
                    Log.d("problema", "error");
                }
            });
            return view;
//        } else {
//            Call<List<Trail>> call = api.favorites(token);
//
//            call.enqueue(new Callback<List<Trail>>() {
//                @Override
//                public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
//                    if (response.isSuccessful()){
//                        List<Trail> trails = response.body();
//                        if (view instanceof RecyclerView) {
//                            Context context = view.getContext();
//                            RecyclerView recyclerView = (RecyclerView) view;
//                            if (mColumnCount <= 1) {
//                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                            } else {
//                                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//                            }
//                            recyclerView.setAdapter(new MyTrailRecyclerViewAdapter(getActivity(), trails));
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Trail>> call, Throwable t) {
//                    Log.d("problema", "error");
//                }
//            });
//            return view;
//        }
    }

}
