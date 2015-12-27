package uk.co.czcz.speedreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null)
        {
            // Initial run of this activity, so lets display a fragment
            getSupportFragmentManager().beginTransaction().add(new SpeedReadingFragment(), SpeedReadingFragment.TAG).commit();
        }
    }
}
