package uk.co.czcz.speedreader;

public interface NavigationController {

    interface CanUseNavigationController
    {
        void provideNavigationController(NavigationController controller);
    }

    void displayInitialSpeedReadingEditorFragment();
    void displayLocalSpeedReadingFragmentForText(String text);

}
