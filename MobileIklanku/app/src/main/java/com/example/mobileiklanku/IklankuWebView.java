package com.example.mobileiklanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class IklankuWebView <WebactivityTab1> extends AppCompatActivity {
    //Membuat Variabel Webview dengan nama web
    WebView web;
    //Membuat Variabel url dengan tipe data String (huruf / kata)
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iklanku_web_view);

        //masukan url web kalian di bawah ini
        url = "https://wsjti.id/iklanku/public/home";

        //mencari id dengan nama web yang sudah kita buat sebelumnya
        web = (WebView) findViewById(R.id.webview);

        //membuat javascript pada web dapat dikenali oleh webview
        web.getSettings().setJavaScriptEnabled(true);

        //melakukan load pada url di atas
        web.loadUrl(url);

        //membuat webview apabila ada tombol share ke WA ataupun Telegram langsung membuka         aplikasi bawaan di smartphone
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView web, String url) {
                if (url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }
}