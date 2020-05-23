package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */

    TextView advScore;
    int advancedScore = 0;

    CountDownTimer timer;
    CountDownTimer readyTimer;

    Button[] buttonList = new Button[9];

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        readyTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Whack-A-Mole 2.0","Ready CountDown!"+ millisUntilFinished/1000);
                Toast.makeText(Main2Activity.this,"Get Ready in " + millisUntilFinished/1000 + "seconds", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.v("Whack-A-Mole 2.0","Ready Countdown Complete");
                Toast.makeText(Main2Activity.this, "GO!",Toast.LENGTH_SHORT).show();
                timer.start();
            }
        };
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        timer = new CountDownTimer(Long.MAX_VALUE,1000) {
            @Override
            public void onTick(long l) {
                setNewMole();
                Log.v("Whack-A-Mole 2.0", "New Mole Location!");
            }

            @Override
            public void onFinish() {

            }
        };
    }
    private static final int[] BUTTON_IDS = {
        R.id.button4, R.id.button5, R.id.button6,R.id.button7, R.id.button8,
            R.id.button9, R.id.button10, R.id.button11,R.id.button12
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        advScore = findViewById(R.id.textView);

        readyTimer();
        placeMoleTimer();


        for(int i = 0; i < 9; i++){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            buttonList[i] = findViewById(BUTTON_IDS[i]);
            final int finalI = i;
            buttonList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doCheck(buttonList[finalI]);
                    setNewMole();
                }
            });
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        readyTimer.start();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText() == "*"){
            advancedScore ++;
            Log.v("Whack-A-Mole 2.0","Hit, score added!");
        }
        else{
            advancedScore --;
            Log.v("Whack-A-Mole 2.0","Missed, point deducted!");
        }
        advScore.setText(String.valueOf(advancedScore));
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for (Button i : buttonList){
            i.setText("O");
        }
        buttonList[randomLocation].setText("*");

    }
}

