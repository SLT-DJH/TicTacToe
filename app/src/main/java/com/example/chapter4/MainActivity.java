package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int turn = 0;

    int gameActive = 0;

    public void regame(View view){
        Button restart = (Button) findViewById(R.id.button3);
        TextView winnerwinner = (TextView) findViewById(R.id.textView2);
        restart.setVisibility(View.INVISIBLE);
        winnerwinner.setVisibility(View.INVISIBLE);
        LinearLayout mainsmallView1 = (LinearLayout) findViewById(R.id.mainsmallView1);
        for(int i = 0; i < mainsmallView1.getChildCount(); i++){
            ImageView counter = (ImageView) mainsmallView1.getChildAt(i);
            counter.setImageDrawable(null);
        }
        LinearLayout mainsmallView2 = (LinearLayout) findViewById(R.id.mainsmallView2);
        for(int i = 0; i < mainsmallView2.getChildCount(); i++){
            ImageView counter = (ImageView) mainsmallView2.getChildAt(i);
            counter.setImageDrawable(null);
        }
        LinearLayout mainsmallView3 = (LinearLayout) findViewById(R.id.mainsmallView3);
        for(int i = 0; i < mainsmallView3.getChildCount(); i++){
            ImageView counter = (ImageView) mainsmallView3.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i = 0; i < 9; i++){
            gameState[i] = 2;
        }

        turn = 0;

        gameActive = 0;
    }

    public void spotcheck(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameActive == 0){
            if(gameState[tappedCounter] == 2){
                gameState[tappedCounter] = turn;

                counter.setTranslationY(-1500);

                if(turn == 0){
                    counter.setImageResource(R.drawable.red_coin);
                    turn++;
                } else {
                    counter.setImageResource(R.drawable.black_coin);
                    turn = 0;
                }

                counter.animate().translationYBy(1500).setDuration(300);

                for(int[] winningPosition : winningPositions) {
                    if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){

                        String winner = "";

                        if (turn == 1){
                            winner = "red team";
                        } else {
                            winner =  "black team";
                        }

                        gameActive = 1;

                        Button restart = (Button) findViewById(R.id.button3);
                        TextView winnerwinner = (TextView) findViewById(R.id.textView2);
                        winnerwinner.setText(winner + " has won!");
                        restart.setVisibility(View.VISIBLE);
                        winnerwinner.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                Toast.makeText(this, "Please keep the rule", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please restart the game", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
