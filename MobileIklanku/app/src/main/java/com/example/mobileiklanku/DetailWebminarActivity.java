package com.example.mobileiklanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileiklanku.api.helper.ServiceGenerator;
import com.example.mobileiklanku.api.helper.URLAPI;
import com.example.mobileiklanku.api.models.WebinarModels;
import com.example.mobileiklanku.api.services.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mobileiklanku.WebinarFragment.ID_WEBINAR;

public class DetailWebminarActivity extends AppCompatActivity {

    // initialization parameters
    private static final String TAG = DetailWebminarActivity.class.getCanonicalName();
    private ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
    private int id_webinar;
    private URLAPI urlapi =new URLAPI();
    TextView titleDW, descDW, dateDW;
    ImageView imageDW;
    ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_webminar);
        titleDW = findViewById(R.id.titleWBDTTV);
        descDW = findViewById(R.id.descWBDTTV);
        dateDW = findViewById(R.id.dateWBDTTV);
        imageDW = findViewById(R.id.imageDW);
        progressWheel = findViewById(R.id.progress_wheel);
        menuVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id_webinar = extras.getInt(ID_WEBINAR);
        }
        getDetail(id_webinar);
    }

    public void menuVisibility(int a) {
        titleDW.setVisibility(a);
        descDW.setVisibility(a);
        dateDW.setVisibility(a);
    }

    public void getDetail(Integer id_webinar){
        apiInterface.getWebinarByID(id_webinar).enqueue(new Callback<List<WebinarModels>>() {
            @Override
            public void onResponse(Call<List<WebinarModels>> call, Response<List<WebinarModels>> response) {
                if (response.isSuccessful()) {
                    progressWheel.setVisibility(View.GONE);
                    menuVisibility(View.VISIBLE);
                    if (!response.body().isEmpty()){
                            WebinarModels webinarRes = response.body().get(0);
                            titleDW.setText(webinarRes.getJudul_webinar());
                            descDW.setText(webinarRes.getDeskripsi());
                            dateDW.setText(webinarRes.getDeadline());
                            Picasso.get().load(urlapi.getURI()+"/images/webinar/" + webinarRes.getPamflet_webinar())
                                .centerCrop()
                                .resize(500, 500)
                                .into(imageDW);
                    }else{
                        showSnackBar("Response empty");
                    }
                }else{
                    showSnackBar("Error Request Failed");
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
        Snackbar.make(getWindow().getDecorView().getRootView(), msg, Snackbar.LENGTH_LONG).show();
    }
}