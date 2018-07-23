package williamcom.www.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;


public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonLogout;
    private TextView textViewUserEmail;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser User=firebaseAuth.getCurrentUser();


        textViewUserEmail = (TextView) findViewById(R.id.textviewUserEmail);
        textViewUserEmail.setText("Welcome  in Home Page   "+User.getEmail());


        buttonLogout = (Button) findViewById(R.id.buttonlogout);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
