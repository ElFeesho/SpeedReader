package uk.co.czcz.speedreader;

import android.support.v4.app.FragmentManager;

class FragmentNavigationController implements NavigationController {

    private final FragmentManager fragmentManager;

    public FragmentNavigationController(FragmentManager supportFragmentManager) {
        fragmentManager = supportFragmentManager;
    }

    @Override
    public void displayInitialSpeedReadingEditorFragment() {
        fragmentManager.beginTransaction().add(R.id.fragment_container, new SpeedReadingFragment(), SpeedReadingFragment.TAG).commit();
    }

    @Override
    public void displayLocalSpeedReadingFragmentForText(String text) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new LocalSpeedReadingFragment(), LocalSpeedReadingFragment.TAG).commit();
    }
}
