package com.example.serge.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.transition.Fade.IN;

public class MainActivity extends AppCompatActivity {
    SeekBar myTimeseekBar;
    TextView timerTextView;
    Boolean counterIsActive=false;
    Button controllerButton;
    CountDownTimer countDownTimer;
    RelativeLayout relativeLayout;


    /*public void read(){
        if (relativeLayout.getVisibility() == View.VISIBLE) {

            relativeLayout.setVisibility(View.INVISIBLE);
        }else{
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }*/

    public void resetTimer(){

        timerTextView.setText("0:30");
        myTimeseekBar.setProgress(30); //30 sec as before
        countDownTimer.cancel(); //cancelling the timer
        controllerButton.setText("Go!"); //resetting the timer to be Go!
        myTimeseekBar.setEnabled(true); //to bring the seekbar  again
        counterIsActive = false; //to get everything back

    }

    public void upDateTimer(int secondsLeft){
        int minutes=(int)(secondsLeft/60);
        int seconds  = secondsLeft - minutes*60; //to get the number of minutes left over

        String secondString = Integer.toString(seconds);


        if(seconds<=9){ //add an extra zero infront if the number is 9 and below

            secondString = "0" +secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" +secondString); //giving the textview the minutes and a seconds as Strings

    }
    public void controlButton(View view){

        if(counterIsActive==false) { // for the user no to press the button again

            counterIsActive = true;
            myTimeseekBar.setEnabled(false); // will stop the user seeking back and forth  when the code is running
            controllerButton.setText("Stop"); // to be stop when the user already typed it.

/////////////////////////////Already generated method//////////////////////////////////////////////////
            countDownTimer = new CountDownTimer(myTimeseekBar.getProgress() * 1000 + 100, 1000) { //use the countdown class, add argument fror time progress and put in // and add the 100 to make delay at sametime as till zero

                @Override
                public void onTick(long millisUnitFinished) {
                    //update the value of the time on each tick
                    upDateTimer((int) millisUnitFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();

                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn); //make a sound when finished
                    mplayer.start();
                }

            }.start();
        } else {

            resetTimer();

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        myTimeseekBar = (SeekBar) findViewById(R.id.myTimeseekBar);
        controllerButton = (Button) findViewById(R.id.controllerButton);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        myTimeseekBar.setMax(600); //10 min or 600 sec
        myTimeseekBar.setProgress(30); //set where it has to start from

        //timeControl

        myTimeseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ //added a seekbarChangeListener
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { //adding our own codes to an already existing method

                upDateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { //when the user starts tracking

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //when the user stops tracking

            }

        });
    }
}
