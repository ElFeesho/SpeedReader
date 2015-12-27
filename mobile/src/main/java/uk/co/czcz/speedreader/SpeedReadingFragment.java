package uk.co.czcz.speedreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SpeedReadingFragment extends Fragment implements NavigationController.CanUseNavigationController {
    public static String TAG = "speed_reading_fragment";
    private NavigationController navigationController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speed_reading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new SpeedReadingEditorPresenter((SpeedReadingEditorPresenter.View) view.findViewById(R.id.editor), new SpeedReadingEditorPresenter.SpeedReadingLauncherDelegate() {
            @Override
            public void launchLocally(String text) {
                navigationController.displayLocalSpeedReadingFragmentForText(text);
            }

            @Override
            public void launchWear(String text) {

            }
        });
    }

    @Override
    public void provideNavigationController(NavigationController controller) {
        navigationController = controller;
    }
}
