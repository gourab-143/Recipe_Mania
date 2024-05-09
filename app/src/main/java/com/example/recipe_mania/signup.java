package com.example.recipe_mania;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    private  FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText nameedt,passedt,emailedt;
        TextView txtlogin;
        AppCompatButton signbtn;
        ProgressBar prgbar;

        txtlogin=findViewById(R.id.txtlogin);
        signbtn=findViewById(R.id.signbtn);
        nameedt=findViewById(R.id.nameedt);
        passedt=findViewById(R.id.passedt);
        emailedt=findViewById(R.id.emailedt);
        prgbar=findViewById(R.id.prgbar);

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilogin;
                ilogin = new Intent(signup.this, Login.class);
                startActivity(ilogin);
            }
        });

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prgbar.setVisibility(View.VISIBLE);
                String name = String.valueOf(nameedt.getText());
                String email = String.valueOf(emailedt.getText());
                String pass = String.valueOf(passedt.getText());
                
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(signup.this, "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(signup.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                prgbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(signup.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }


                        });
            }
        });
    }
}