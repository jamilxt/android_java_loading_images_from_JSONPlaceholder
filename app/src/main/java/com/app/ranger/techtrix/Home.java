package com.app.ranger.techtrix;

import android.app.Application;
import android.content.Intent;

import com.app.ranger.techtrix.authentication.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Application {


    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    @Override
    public void onCreate() {
        super.onCreate();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        if (mFirebaseUser != null){

            Intent feed = new Intent(Home.this,MainActivity.class);

            feed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(feed);
        }else {
            Intent signup = new Intent(Home.this, SignUpActivity.class);

            signup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(signup);
        }



    }
}
