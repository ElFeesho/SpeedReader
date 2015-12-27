package uk.co.czcz.speedreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationController = new NavigationController() {
            @Override
            public void displayInitialSpeedReadingEditorFragment() {
                displayInitialSpeedReadingFragment();
            }

            @Override
            public void displayLocalSpeedReadingFragmentForText(String text) {
                displayLocalSpeedReadingFragment(text);
            }
        };

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null)
        {
            navigationController.displayInitialSpeedReadingEditorFragment();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof NavigationController.CanUseNavigationController)
        {
            ((NavigationController.CanUseNavigationController) fragment).provideNavigationController(navigationController);
        }
    }

    private void displayInitialSpeedReadingFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SpeedReadingFragment(), SpeedReadingFragment.TAG).commit();
    }

    private void displayLocalSpeedReadingFragment(String text) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LocalSpeedReadingFragment(), LocalSpeedReadingFragment.TAG).commit();
    }
}
