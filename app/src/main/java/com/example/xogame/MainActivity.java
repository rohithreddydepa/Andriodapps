package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //x is 0 y is 1
    boolean player=false;
    int winner=2;
    int state[]=new int[]{2,2,2,2,2,2,2,2,2};
    int win[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int check=Integer.parseInt(counter.getTag().toString());
        if(state[check-1]==2 && winner==2) {
            if (player) {
                counter.setImageResource(R.drawable.o);
                state[check - 1] = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                state[check - 1] = 0;
            }
            player = !player;
            counter.animate().alpha(1f).setDuration(1000);
            for (int[] tmp : win) {
                System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]);
                if(state[tmp[0]]==state[tmp[1]] && state[tmp[1]]==state[tmp[2]] && state[tmp[0]]!=2){
                    winner=state[tmp[0]];
                    if (winner == 0) {
                        LinearLayout layout=(LinearLayout) findViewById(R.id.playagain);
                        layout.setVisibility(View.VISIBLE);
                        TextView winnermsg=(TextView) findViewById(R.id.name);
                        winnermsg.setText("X won");}
                    else{
                        LinearLayout layout=(LinearLayout) findViewById(R.id.playagain);
                        layout.setVisibility(View.VISIBLE);
                        TextView winnermsg=(TextView) findViewById(R.id.name);
                        winnermsg.setText("O won");;
                    }
                    break;
                }
            }
            boolean flage=true;
            for(int i=0;i<state.length;i++){
                if(state[i]==2){
                    flage=false;
                }
            }
            if(flage && winner==2){
                playagain(view);
            }

        }
    }
    public void playagain(View view){
        LinearLayout layout=(LinearLayout) findViewById(R.id.playagain);
        layout.setVisibility(View.INVISIBLE);
         player=false;
         winner=2;

         for(int i=0;i<state.length;i++){
             state[i]=2;
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridlayout);
        System.out.println("check");
         for(int i=0;i<gridLayout.getChildCount();i++){
             ( (ImageView)gridLayout.getChildAt(i)).setImageResource(0);
         }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}