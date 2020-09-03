package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String winner;
    static int activeplayer = 0;
    // 0 = yellow, 1 = red
    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //2 means unplayed

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void Drop_in(View view) {

        ImageView counter1 = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter1.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activeplayer;
            counter1.setTranslationY(-1000f);

            if (activeplayer == 0) {
                counter1.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter1.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            counter1.animate()
                    .translationYBy(1000f)
                    .setDuration(300);

            for (int[]winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameIsActive = false;

                    winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }



                    TextView Winningtext = (TextView) findViewById(R.id.Winningtext);
                    Winningtext.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainlayout);

                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                        if (gameIsOver) {
                            TextView Winningtext = (TextView) findViewById(R.id.Winningtext);
                            Winningtext.setText("It's a Draw!");

                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainlayout);

                            layout.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }

    public void playAgain(View view) {

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainlayout);

        layout.setVisibility(View.INVISIBLE);

        activeplayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }
        GridLayout grid = (GridLayout) findViewById(R.id.Grid);


        for (int i = 0; i< grid.getChildCount(); i++) {

            ((ImageView) grid.getChildAt(i)).setImageResource(0);

        }
        gameIsActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}