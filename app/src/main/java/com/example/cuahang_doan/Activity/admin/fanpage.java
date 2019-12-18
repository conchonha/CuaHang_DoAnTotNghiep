package com.example.cuahang_doan.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cuahang_doan.R;

public class fanpage extends AppCompatActivity {
    private Toolbar toolbar_Fanpage;
    private WebView webviewFanpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanpage);
        setactionbar();
        setwebview();
    }

    private void setwebview() {
        webviewFanpage=findViewById(R.id.webviewFanpage);
        webviewFanpage.loadUrl("https://www.facebook.com/profile.php?id=100007219005457");
        webviewFanpage.setWebViewClient(new WebViewClient());
    }

    private void setactionbar() {
        toolbar_Fanpage=findViewById(R.id.toolbar_Fanpage);
        setSupportActionBar(toolbar_Fanpage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_Fanpage.setNavigationIcon(R.drawable.back);
        toolbar_Fanpage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
