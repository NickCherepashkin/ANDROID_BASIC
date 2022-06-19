package com.drozdova.coursera.less_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ExplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.ANSWER, "555");
        setResult(RESULT_OK, intent);
    }
}