package hr.tvz.master.klara.guideme.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hr.tvz.master.klara.guideme.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle(R.string.activity_account_title);
    }
}
