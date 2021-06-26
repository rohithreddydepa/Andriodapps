package com.example.clonetext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText data;
    TextView number;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=(EditText) findViewById(R.id.data);
        number=(TextView)findViewById(R.id.number);
        aSwitch=(Switch)findViewById(R.id.switch1);
    }
    public void copy(View view){
        String str=data.getText().toString();
        StringBuffer tmp;
        try {
            if (aSwitch.isChecked()) {
                tmp = new StringBuffer();
                for (int i = 0; i < Integer.parseInt(number.getText().toString()); i++) {
                    tmp.append(Integer.toString(i + 1) + ". ");
                    tmp.append(str);
                    tmp.append(System.getProperty("line.separator"));
                }
            } else {
                tmp = new StringBuffer(data.getText().toString());
                for (int i = 0; i < Integer.parseInt(number.getText().toString()); i++) {
                    tmp.append(str + " ");
                }
            }
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("message", tmp.toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"to big",Toast.LENGTH_LONG).show();
        }
    }
}