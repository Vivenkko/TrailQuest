package vivenkko.trailquest.fragments;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.trailquest.R;
import vivenkko.trailquest.models.Trail;
import vivenkko.trailquest.MyTrailListRecyclerViewAdapter;
import vivenkko.trailquest.retrofit.TrailQuestController;
import vivenkko.trailquest.retrofit.TrailQuestServiceGenerator;


import java.util.List;


public class TrailFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Trail> trailList;
    RecyclerView recyclerView;



    public TrailFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
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
        View view = inflater.inflate(R.layout.fragment_trail_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            Bundle extras = getActivity().getIntent().getExtras();
            TrailQuestController api = TrailQuestServiceGenerator.createService(TrailQuestController.class);
            String token = extras.getString("token");

            if (mColumnCount == 1) {

                Call<List<Trail>> call = api.listTrails("Bearer "+token);

                call.enqueue(new Callback<List<Trail>>() {
                    @Override
                    public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                        trailList = response.body();
                        if(response.isSuccessful()){
                            recyclerView.setAdapter(new MyTrailListRecyclerViewAdapter(getActivity(),trailList, mListener));
                        } else{

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Trail>> call, Throwable t) {

                    }
                });
            } else if (mColumnCount == 2) {

                Call<List<Trail>> call = api.listMyTrails("Bearer "+token);

                call.enqueue(new Callback<List<Trail>>() {
                    @Override
                    public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                        trailList = response.body();
                        if(response.isSuccessful()){
                            recyclerView.setAdapter(new MyTrailListRecyclerViewAdapter(getActivity(),trailList, mListener));
                        } else{

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Trail>> call, Throwable t) {

                    }
                });
            } else if (mColumnCount == 3) {

                Call<List<Trail>> call = api.favorites("Bearer "+token);

                call.enqueue(new Callback<List<Trail>>() {
                    @Override
                    public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                        trailList = response.body();
                        if(response.isSuccessful()){
                            recyclerView.setAdapter(new MyTrailListRecyclerViewAdapter(getActivity(),trailList, mListener));
                        } else{

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Trail>> call, Throwable t) {

                    }
                });
            }

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
