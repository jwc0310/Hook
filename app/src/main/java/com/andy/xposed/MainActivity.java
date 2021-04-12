package com.andy.xposed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        showIsXposedStart(false);
    }

    private void showIsXposedStart(boolean isXposedStart) {
        if (!isXposedStart) {
            textView.setText("模块未激活");
        } else {
            textView.setText("模块已经激活");
        }
    }
}