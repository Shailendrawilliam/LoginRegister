package williamcom.www.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            finish();

            startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            //} else {
        }
        //
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.Buttonregister);
        editTextEmail = (EditText) findViewById(R.id.EditTextEmail);

        editTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        textViewSignin = (TextView) findViewById(R.id.TextViewSignin);
        buttonRegister.setOnClickListener(this);

        textViewSignin.setOnClickListener(this);

    }


    private void registerUser()

    {
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


        progressDialog.setMessage("Registering User......");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();

                    Toast.makeText(RegisterActivity.this, "Successfully Register", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                } else {
                    Toast.makeText(RegisterActivity.this, "Could not registered ,Please try again!.. ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onClick(View view) {


        if (buttonRegister == view) {
            registerUser();
        }


        if (view == textViewSignin) {


            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }
    }




}


