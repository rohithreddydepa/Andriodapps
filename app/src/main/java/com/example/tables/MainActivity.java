package com.example.tables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView tables;
    public  void set(int n){
        int timesTable = n;
        ArrayList<String> timesTableContent = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            timesTableContent.add(Integer.toString(n)+" X "+Integer.toString(i)+" = "+Integer.toString(i* timesTable));
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,timesTableContent);
        tables.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar number =(SeekBar) findViewById(R.id.number);
        tables=(ListView) findViewById(R.id.table);
        TextView numberid=findViewById(R.id.numbername);
        number.setMax(20);
        number.setProgress(10);
        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int table=1;
                if(progress<1){
                    number.setProgress(1);
                }
                else {
                    table=progress;
                }
                set(table);
                numberid.setText(Integer.toString(table));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}