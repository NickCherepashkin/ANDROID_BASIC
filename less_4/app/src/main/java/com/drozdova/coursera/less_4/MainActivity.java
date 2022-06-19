package com.drozdova.coursera.less_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final static String TAG = MainActivity.class.getSimpleName();
    final static String KEY = "message_key";
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.edit_text);
        if(savedInstanceState != null) {
            String oldMessage = savedInstanceState.getString(KEY);
            if (!TextUtils.isEmpty(oldMessage)) {
                mEdit.setText(oldMessage);
            }
            Log.d(TAG, "onCreate:[" + oldMessage + "]");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String text = mEdit.getText().toString();
        outState.putString(KEY, text);

        Log.d(TAG,"onSaveInstanceState:[" + text + "]");
    }

    public void startIntent(View view) {
        String message = "Hello intent";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");

        String title = "Поделиться";

        Intent chooser = Intent.createChooser(intent, title);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    final static int OUR_REQUEST_CODE = 42;
    final static String ANSWER = "100";

    public void startExplicitIntent(View view) {
        Intent intent = new Intent(this, ExplicitIntentActivity.class);
        startActivityForResult(intent, OUR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OUR_REQUEST_CODE) {
            String answer = null;
            if (resultCode == RESULT_OK && data != null) {
                answer = data.getStringExtra(ANSWER);
            }
            Log.d(TAG,"onActivityResult:[" + answer + "]");
        }
    }
}