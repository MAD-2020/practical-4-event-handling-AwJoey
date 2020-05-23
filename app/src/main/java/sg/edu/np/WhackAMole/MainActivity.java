package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    Button button1;
    Button button2;
    Button button3;
    TextView score;
    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        score = findViewById(R.id.textView3);
        Score = 0;
        score.setText(String.valueOf(Score));

        Log.v("Whack-A-Mole 1.0", "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v("Whack-A-Mole 1.0", "Starting GUI!");
        button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d("Whack-A-Mole 1.0", "Button Left Clicked!");
               doCheck(button1);
           }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Whack-A-Mole 1.0", "Button Middle Clicked!");
                doCheck(button2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Whack-A-Mole 1.0", "Button Right Clicked!");
                doCheck(button3);
            }
        });



    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v("Whack-A-Mole 1.0", "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v("Whack-A-Mole 1.0", "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
        Triggers nextLevelQuery().
        */
        if (checkButton.getText() == "*"){
            Log.d("Whack-A-Mole 1.0", "Hit, score added!");
            Score ++;
        }
        else{
            Log.d("Whack-A-Mole 1.0", "Missed, score deducted!");
            Score --;
        }
        score.setText(String.valueOf(Score));

        if (Score % 10 == 0 && Score > 0) {
            nextLevelQuery();
        }
        else{
            setNewMole();
        }

    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/

        Log.d("Whack-A-Mole 1.0", "Advanced option given to user!");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning! Insane Whack-A-Mole incoming!")
                .setMessage("Would you like to advance to advanced mode?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Whack-A-Mole 1.0","User accepts!");
                        Log.d("Whack-A-Mole 1.0","Current User Score:" + Score); //Score is always 0
                        nextLevel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        Log.d("Whack-A-Mole 1.0","User declines!");
                        setNewMole();
                    }
                });
        alert.show();


    }

    private void nextLevel(){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        /* Launch advanced page */
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if (randomLocation == 1){
            button1.setText("*");
            button2.setText("O");
            button3.setText("O");
        }
        else if (randomLocation == 2){
            button1.setText("O");
            button2.setText("*");
            button3.setText("O");
        }
        else{
            button1.setText("O");
            button2.setText("O");
            button3.setText("*");
        }
    }
}