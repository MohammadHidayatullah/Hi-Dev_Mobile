package com.example.mobileiklanku;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileiklanku.api.helper.ServiceGenerator;
import com.example.mobileiklanku.api.helper.URLAPI;
import com.example.mobileiklanku.api.models.LokerModels;
import com.example.mobileiklanku.api.services.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mobileiklanku.LokerFragment.ID_LOKER;

public class DetailLokerActivity extends AppCompatActivity {

    // paramaters
    private static final String TAG = DetailLokerActivity.class.getCanonicalName();
    private ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
    private int id_loker;
    private URLAPI urlapi = new URLAPI();
    TextView titleDLK, descDLK, dateDLK;
    ImageView imageDLK;
    ProgressWheel progressWheel;
    String link;
    Button daftarBTN, backBTN;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loker);
        titleDLK = findViewById(R.id.titleLKDTTV);
        descDLK = findViewById(R.id.descLKDTTV);
        dateDLK = findViewById(R.id.dateLKDTTV);
        imageDLK = findViewById(R.id.imageDLK);
        progressWheel = findViewById(R.id.progress_wheel);
        menuVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id_loker = extras.getInt(ID_LOKER);
        }
        daftarBTN = findViewById(R.id.daftarDLKTN);
        daftarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = link;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://"+url));
                startActivity(i);
            }
        });

        backBTN = findViewById(R.id.backDLKTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailLokerActivity.this, MainActivity.class));
                finish();
            }
        });
        getDetail(id_loker);
    }

    public void menuVisibility(int a) {
        titleDLK.setVisibility(a);
        descDLK.setVisibility(a);
        dateDLK.setVisibility(a);
    }

    public void getDetail(Integer id_loker){
        apiInterface.getLokerByID(id_loker).enqueue(new Callback<List<LokerModels>>() {
            @Override
            public void onResponse(Call<List<LokerModels>> call, Response<List<LokerModels>> response) {
                if (response.isSuccessful()){
                    progressWheel.setVisibility(View.GONE);
                    menuVisibility(View.VISIBLE);
                    if (!response.body().isEmpty()){
                        LokerModels lokerRes = response.body().get(0);
                        titleDLK.setText(lokerRes.getJudul_loker());
                        descDLK.setText(lokerRes.getDeskripsi());
                        dateDLK.setText(lokerRes.getDeadline());
                        link = lokerRes.getLink();
                        Picasso.get().load(urlapi.getURI()+"/images/loker/" + lokerRes.getPamflet_loker())
                                .centerCrop()
                                .resize(500, 500)
                                .into(imageDLK);
                    }else {
                        showSnackBar("Data Kosong");
                    }
                }else {
                    showSnackBar("Error Request Failed");
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
        Snackbar.make(getWindow().getDecorView().getRootView(), msg, Snackbar.LENGTH_LONG).show();
    }
}
