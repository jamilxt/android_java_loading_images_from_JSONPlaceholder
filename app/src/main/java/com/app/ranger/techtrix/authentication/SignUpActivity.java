package com.app.ranger.techtrix.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.ranger.techtrix.MainActivity;
import com.app.ranger.techtrix.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText et_firstName, et_lastName, et_email, et_phone, et_password;
    Button btn_signUp,btn_loginP;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email= findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_loginP = findViewById(R.id.btn_loginPage);


        btn_loginP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });


        btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()){
                    et_email.setError("empty");
                    et_email.requestFocus();
                }else if (et_password.getText().toString().isEmpty()){
                    et_password.setError("empty");
                    et_password.requestFocus();
                } else {
                    mFirebaseAuth.createUserWithEmailAndPassword(et_email.getText().toString(),et_password.getText().toString()).addOnCompleteListener(SignUpActivity.this,
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Registration complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                Log.d("SignupActivity", "createUserWithEmail:success");
                            } else
                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            Log.w("SignupActivity", "createUserWithEmail:failure", task.getException());

                        }
                    });
                }
            }
        });




    }
}