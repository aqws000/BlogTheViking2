package com.example.hayden.blogtest;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mRegisterBtn;

    private FirebaseAuth mAuth;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mProgress = new ProgressDialog(this);

        mNameField = (EditText) findViewById(R.id.nameField);
        mEmailField =(EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText) findViewById(R.id.passwordField);
        mRegisterBtn = (Button) findViewById(R.id.registrationBtn);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

    }

    private void startRegister() {

        String name =mNameField.getText().toString().trim();
        String email =mEmailField.getText().toString().trim();
        String password =mPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            //All of these have to not be empty in order to proceed with registration

            mProgress.
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                    }
                }
            })


        }

    }
}
