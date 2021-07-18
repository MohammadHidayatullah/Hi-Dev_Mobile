package com.example.mobileiklanku;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileiklanku.adaptor.LokerAdaptor;
import com.example.mobileiklanku.api.helper.ServiceGenerator;
import com.example.mobileiklanku.api.models.LokerModels;
import com.example.mobileiklanku.api.services.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LokerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LokerFragment extends Fragment implements LokerAdaptor.ListenerAdapter {

    // parameter
    private static final String TAG = LokerFragment.class.getCanonicalName();
    public static final String ID_LOKER = "loker_id";
    private List<LokerModels> lokerModels;
    private ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
    RecyclerView lokerRV;
    LokerAdaptor lokerAdaptor;
    ProgressWheel progressWheel;
    TextView notifLoker;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LokerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LokerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LokerFragment newInstance(String param1, String param2) {
        LokerFragment fragment = new LokerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        lokerModels = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loker, container, false);
        lokerRV = view.findViewById(R.id.lokerrRV);
        progressWheel = view.findViewById(R.id.progress_wheel);
        notifLoker = view.findViewById(R.id.notifLoker);
        setupRV();
        getDataLoker();
        return view;

    }

    public void setupRV(){
        lokerAdaptor = new LokerAdaptor(lokerModels, this);
        lokerRV.setAdapter(lokerAdaptor);
        lokerRV.setLayoutManager(new LinearLayoutManager(getContext()));
        lokerModels.clear();
        lokerAdaptor.notifyDataSetChanged();
    }

    public void getDataLoker(){
        lokerModels.clear();
        apiInterface.getLoker().enqueue(new Callback<List<LokerModels>>() {
            @Override
            public void onResponse(Call<List<LokerModels>> call, Response<List<LokerModels>> response) {
                if (response.isSuccessful()){
                    progressWheel.setVisibility(View.GONE);
                    if (!response.body().isEmpty()){
                        lokerRV.setVisibility(View.VISIBLE);
                        for (int i = 0; i < response.body().size(); i++){
                            LokerModels lokerRes = response.body().get(i);
                            lokerModels.add(lokerRes);
                            lokerAdaptor.notifyDataSetChanged();
                        }
                    }else{
                        notifLoker.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LokerModels>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                showSnackBar(t.getMessage());
            }
        });
    }

    private void showSnackBar(String msg){
        Snackbar.make(getActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClickItem(int id, LokerModels lokerModels) {
        Intent intent = new Intent(getActivity(), DetailLokerActivity.class);
        intent.putExtra(ID_LOKER, lokerModels.getId());
        startActivity(intent);
    }
}