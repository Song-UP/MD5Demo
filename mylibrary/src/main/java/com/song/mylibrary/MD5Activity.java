package com.song.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.song.mylibrary.util.MD5Helper;


public class MD5Activity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.but);
        textView = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text == null || text == ""){
                    Toast.makeText(MD5Activity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText(MD5Helper.MD5(text));
            }
        });
    }
}
