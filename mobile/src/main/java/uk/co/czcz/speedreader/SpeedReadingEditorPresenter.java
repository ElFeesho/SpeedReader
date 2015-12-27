package uk.co.czcz.speedreader;

public class SpeedReadingEditorPresenter {

    public interface View {

        interface Listener {
            void requestLaunchLocally(String text);

            void requestLaunchWear(String text);
        }

        void displayNoTextError();

        void addListener(View.Listener listener);
    }

    public interface SpeedReadingLauncherDelegate {
        void launchLocally(String text);

        void launchWear(String text);
    }

    public SpeedReadingEditorPresenter(final View view, final SpeedReadingLauncherDelegate speedReadingLauncherDelegate) {

        view.addListener(new View.Listener() {
            @Override
            public void requestLaunchLocally(String text) {
                if (text.length() > 0) {
                    speedReadingLauncherDelegate.launchLocally(text);
                } else {
                    view.displayNoTextError();
                }
            }

            @Override
            public void requestLaunchWear(String text) {
                if (text.length() > 0) {
                    speedReadingLauncherDelegate.launchWear(text);
                } else {
                    view.displayNoTextError();
                }
            }
        });
    }
}
