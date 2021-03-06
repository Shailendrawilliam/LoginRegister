package williamcom.www.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();

            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));

        }
        progressDialog = new ProgressDialog(this);
        buttonSignin = (Button) findViewById(R.id.ButtonSignIn);
        editTextEmail = (EditText) findViewById(R.id.EditTextEmail);

        editTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        textViewSignup = (TextView) findViewById(R.id.TextViewSignUp);
        buttonSignin.setOnClickListener(this);

        textViewSignup.setOnClickListener(this);
    }

    private  void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();



        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {


            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();

            return;


        }
        progressDialog.setMessage("Login in progress... ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,
                password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    finish();

                    startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Could not logged in ,Please try again!.. ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view==buttonSignin) {
            userLogin();
        }
        if(view==textViewSignup){

            finish();
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}
