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

import com.example.mobileiklanku.adaptor.WebinarAdaptor;
import com.example.mobileiklanku.api.helper.ServiceGenerator;
import com.example.mobileiklanku.api.models.WebinarModels;
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
 * Use the {@link WebinarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebinarFragment extends Fragment implements WebinarAdaptor.ListennerAdaper {

    // initialization parameters
    private static final String TAG = WebinarFragment.class.getCanonicalName();
    public static final String ID_WEBINAR = "webinar_id";
    private List<WebinarModels> webinarModels;
    private ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
    RecyclerView webinarRv;
    WebinarAdaptor webinarAdaptor;
    ProgressWheel progressWheel;
    TextView notifWebinar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WebinarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebinarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebinarFragment newInstance(String param1, String param2) {
        WebinarFragment fragment = new WebinarFragment();
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
        webinarModels = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_webinar, container, false);
        webinarRv = view.findViewById(R.id.webinarRV);
        progressWheel = view.findViewById(R.id.progress_wheel);
        notifWebinar = view.findViewById(R.id.notifWebminar);
        setupRV();
        getDataWebinar();
        return view;
    }

    public void setupRV() {
        webinarAdaptor = new WebinarAdaptor(webinarModels, this);
        webinarRv.setAdapter(webinarAdaptor);
        webinarRv.setLayoutManager(new LinearLayoutManager(getContext()));
        webinarModels.clear();
        webinarAdaptor.notifyDataSetChanged();
    }

    private void getDataWebinar() {
        webinarModels.clear();
        apiInterface.getWebinar().enqueue(new Callback<List<WebinarModels>>() {
            @Override
            public void onResponse(Call<List<WebinarModels>> call, Response<List<WebinarModels>> response) {
                if (response.isSuccessful()){
                    progressWheel.setVisibility(View.GONE);
                    if (!response.body().isEmpty()){
                        webinarRv.setVisibility(View.VISIBLE);
                        for (int i = 0; i < response.body().size(); i++) {
                            WebinarModels webinarRes = response.body().get(i);
                            webinarModels.add(webinarRes);
                            webinarAdaptor.notifyDataSetChanged();
                        }
                    }else{
                        notifWebinar.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WebinarModels>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                showSnackBar(t.getMessage());
            }
        });
    }
    private void showSnackBar(String msg){
        Snackbar.make(getActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClickItem(int id, WebinarModels webinarModels) {
        Intent intent = new Intent(getActivity(), DetailWebminarActivity.class);
        intent.putExtra(ID_WEBINAR, webinarModels.getId());
        startActivity(intent);
    }
}