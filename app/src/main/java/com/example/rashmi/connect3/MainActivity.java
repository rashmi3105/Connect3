package com.example.rashmi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 is yellow and 1 is red , 2 is empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive == true) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellowcoin);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.redcoin);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] i : winningPos) {

                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2)
                {       ;
                //someone won
                gameActive = false;
                String winner = "";
                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner + " has won!!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }
    }

}
   public void playAgain(View view)
   {
       Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
       TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
       playAgainButton.setVisibility(View.INVISIBLE);
       winnerTextView.setVisibility(View.INVISIBLE);
       GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
       for(int i=0;i<gridLayout.getChildCount();i++)
       {
           ImageView count = (ImageView) gridLayout.getChildAt(i);
           count.setImageDrawable(null);
       }
       for(int i=0;i<gameState.length;i++)
       {
           gameState[i] = 2;
       }
       activePlayer = 0;
        gameActive = true;
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
