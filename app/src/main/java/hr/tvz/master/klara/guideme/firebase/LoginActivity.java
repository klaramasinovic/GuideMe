package hr.tvz.master.klara.guideme.firebase;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

import hr.tvz.master.klara.guideme.MainActivity;
import hr.tvz.master.klara.guideme.R;
import hr.tvz.master.klara.guideme.TourActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_INFO = "hr.tvz.master.klara.guideme.firebase.LoginActivity.USER_INFO";
    private static final String TAG = "LoginActivity";


    private EditText eMailField;
    private EditText passwordField;
    private Button loginButton;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.activity_login_title);

        mAuth = FirebaseAuth.getInstance();

        eMailField = findViewById(R.id.login_editText_email);
        passwordField = findViewById(R.id.login_editText_password);
        loginButton= findViewById(R.id.login_button);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //that means user has logged in
                if(firebaseAuth.getCurrentUser() != null){
                    Intent sendLoggedUserDataToMainActivity= new Intent(LoginActivity.this,TourActivity.class);
                    sendLoggedUserDataToMainActivity.putExtra(USER_INFO,firebaseAuth.getCurrentUser().getEmail().toString());
                    startActivity(sendLoggedUserDataToMainActivity);
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        String email = eMailField.getText().toString();
        String password = passwordField.getText().toString();

        //if one of the fields is empty
        //make toast
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, R.string.empty_fields, Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //task successfull user logged inn
                    //if task is not successfull
                    //make toast
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.login_problem, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
