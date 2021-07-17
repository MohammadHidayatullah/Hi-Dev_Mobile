package com.example.mobileiklanku;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobileiklanku.api.helper.ServiceGenerator;
import com.example.mobileiklanku.api.models.LokerModels;
import com.example.mobileiklanku.api.models.WebinarModels;
import com.example.mobileiklanku.api.services.ApiInterface;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        checkModelWebinar();
        checkModelLoker();

        if (savedInstanceState==null){
            bottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.webinar:
                        fragment = new WebinarFragment();
                        break;
                    case R.id.loker:
                        fragment = new LokerFragment();
                        break;
                }
                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }else {
                    Log.e(TAG, "Errir in creating fragment");
                }
            }
        });

    }
    private void checkModelWebinar(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<WebinarModels>> call = service.getWebinar();
        call.enqueue(new Callback<List<WebinarModels>>() {
            @Override
            public void onResponse(Call<List<WebinarModels>> call, Response<List<WebinarModels>> response) {
                System.out.println("response: "+response.body().get(0).getDeskripsi());
            }

            @Override
            public void onFailure(Call<List<WebinarModels>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void checkModelLoker(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<LokerModels>> call = service.getLoker();
        call.enqueue(new Callback<List<LokerModels>>() {
            @Override
            public void onResponse(Call<List<LokerModels>> call, Response<List<LokerModels>> response) {
                System.out.println("response: "+response.body().get(0).getDeskripsi());
            }

            @Override
            public void onFailure(Call<List<LokerModels>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }
}