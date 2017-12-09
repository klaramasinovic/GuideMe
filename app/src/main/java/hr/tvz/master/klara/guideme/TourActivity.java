package hr.tvz.master.klara.guideme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hr.tvz.master.klara.guideme.firebase.LoginActivity;

public class TourActivity extends AppCompatActivity {
    private TextView statusTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        setTitle(R.string.activity_tour_title);

        Intent receiveLoggedUserData= getIntent();
        String userInfo = receiveLoggedUserData.getStringExtra(LoginActivity.USER_INFO);
        statusTextView=findViewById(R.id.textView_status);
        statusTextView.setText(userInfo);
    }
}
