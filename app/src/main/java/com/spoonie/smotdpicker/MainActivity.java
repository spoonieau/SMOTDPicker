package com.spoonie.smotdpicker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    //
    String aboutInfo = "Safety Message for the day picker\n" +
            "A quick tool to help people think of a\n" +
            "safey message when put on the spot during\n" +
            "workshop prestarts.\n" +
            "Written by Rick Spooner\n" +
            "\n" +
            "GitHub\n" +
            "https://github.com/spoonieau\n" +
            "ChangeLog\n" +
            "Version 1.0 OrginalRelease";

    //Sting Array to store all the Safety Messages.
    String [] safetyMessages = {"Keep up good housekeeping.",
            "Use Correct Tool's for the job.",
            "Do Take 5's before each Task.",
            "Drive to conditions.",
            "Set up a 3 meter exclusion zone when loading/unloading",
            "Were correct PPE for the task at hand.",
            "Make sure PPE is serviceable.",
            "Wash hands after using the restroom.",
            "Keep walk and service way's clear at all times.",
            "Use sun protection when outside.",
            "Keep hydrated while doing strenuous activities.",
            "Dont run in yard or workshop",
            "Watch your vehicles speeds around the yard.",
            "make sure all bottles are labled correctly.",
            "Chemicals and substances stored in there correct place."};

    //Get and set length of array for the max value.
    int arrayHigh = safetyMessages.length;

    AnimationDrawable gameWheelAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start the game wheel sound effect.
                pSoundEffect();

                //Start the game wheel animation.
                gameWheelAni();
            }
        });

    }
    //Load action bar about icon.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Set what button in action bar dose
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Show about box.
            case R.id.action_About:
                aboutBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Ramdomly pick a message from the string Array and display in a alert box
    public void sMessage ()
    {
        Random getRandomPos = new Random();
        int randomInt = getRandomPos.nextInt(arrayHigh);

        AlertDialog.Builder sMessage = new AlertDialog.Builder(MainActivity.this);
        sMessage.setMessage(safetyMessages[randomInt])
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = sMessage.create();
        alert.setTitle("Safety Message For the day.");
        alert.show();
    }

    //About box method
    public void aboutBox()
    {
        AlertDialog.Builder sMessage = new AlertDialog.Builder(MainActivity.this);
        sMessage.setMessage(aboutInfo)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = sMessage.create();
        alert.setTitle("About");
        alert.show();
    }

    //Play sound effect.
    public void pSoundEffect()
    {
        MediaPlayer gWheelSound = MediaPlayer.create(this, R.raw.gamewheel);
        gWheelSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            //When sound effect is finshied run method sMessage.
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Disply safety message.
                sMessage();

                //Stop game wheel animation when sound stops.
                gameWheelAnimation.stop();
            }
        });
        //Start sound.
        gWheelSound.start();
    }
    //Play the game wheel animmation.
    public void gameWheelAni ()
    {
        ImageView ivGame = (ImageView) findViewById(R.id.ivGameWheel);
        ivGame.setBackgroundResource(R.drawable.gamewheelani);
        gameWheelAnimation = (AnimationDrawable) ivGame.getBackground();
        gameWheelAnimation.start();
    }

}


