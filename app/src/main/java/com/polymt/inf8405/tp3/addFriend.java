package com.polymt.inf8405.tp3;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Wassim on 25/04/2017.
 */

public class addFriend extends AppCompatActivity {

    Button buttonAddFriend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend);

        getSupportActionBar().setTitle("Add friend");
        butListener();
    }

    public void butListener() {

        buttonAddFriend = (Button) findViewById(R.id.buttonAddFriend);

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //EditText text = (EditText)findViewById(R.id.editTextEmail);


                //TODO backend

                AlertDialog.Builder build = new AlertDialog.Builder(addFriend.this);
                build.setTitle("Friend added !");
                build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                //build.setCancelable(true);
                build.show();


//                Intent i = new Intent(getBaseContext(), GroupActivity.class);
//                startActivity(i);

            }
        });

    }

}