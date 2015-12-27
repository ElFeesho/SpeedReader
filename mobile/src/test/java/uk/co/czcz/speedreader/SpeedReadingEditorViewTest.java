package uk.co.czcz.speedreader;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SpeedReadingEditorViewTest {

    @Test
    public void whenASpeedReadingEditorViewIsEmpty_launchingASpeedReadingSessionIsNotPossible() {

        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = new FakeSpeedReadingEditorView();
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingLocally();

        assertThat(spySpeedReadingLauncherDelegate.launchLocally_called, is(false));
    }

    @Test
    public void whenASpeedReadingEditorViewIsEmpty_launchingASpeedReadingSessionOnWearIsNotPossible() {

        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = new FakeSpeedReadingEditorView();
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingOnWear();

        assertThat(spySpeedReadingLauncherDelegate.launchWear_called, is(false));
    }

    @Test
    public void whenASpeedReadingEditorViewContainsText_ASpeedReadingSessionCanBeLaunchedLocallyWithTheEnteredText() {

        String expectedText = "Expected Text";
        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = FakeSpeedReadingEditorView.withEnteredText(expectedText);
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingLocally();

        assertThat(spySpeedReadingLauncherDelegate.launchLocally_called, is(true));
        assertThat(spySpeedReadingLauncherDelegate.launchLocally_text, is(expectedText));
    }

    @Test
    public void whenASpeedReadingEditorViewContainsText_ASpeedReadingSessionCanBeLaunchedOnWearWithTheEnteredText() {

        String expectedText = "Expected Text";
        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = FakeSpeedReadingEditorView.withEnteredText(expectedText);
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingOnWear();

        assertThat(spySpeedReadingLauncherDelegate.launchWear_called, is(true));
        assertThat(spySpeedReadingLauncherDelegate.launchWear_text, is(expectedText));
    }

    public static class SpeedReadingEditorPresenter {

        private final View view;
        private final SpeedReadingLauncherDelegate speedReadingLauncherDelegate;

        public interface View {
            interface Listener {
                void requestLaunchLocally(String text);

                void requestLaunchWear(String text);
            }

            void addListener(Listener listener);
        }

        public interface SpeedReadingLauncherDelegate {
            void launchLocally(String text);

            void launchWear(String text);
        }

        public SpeedReadingEditorPresenter(View view, final SpeedReadingLauncherDelegate speedReadingLauncherDelegate) {
            this.view = view;
            this.speedReadingLauncherDelegate = speedReadingLauncherDelegate;

            view.addListener(new View.Listener() {
                @Override
                public void requestLaunchLocally(String text) {
                    if (text.length() > 0) {
                        speedReadingLauncherDelegate.launchLocally(text);
                    }
                }

                @Override
                public void requestLaunchWear(String text) {
                    if (text.length() > 0) {
                        speedReadingLauncherDelegate.launchWear(text);
                    }
                }
            });
        }
    }

    private static class FakeSpeedReadingEditorView implements SpeedReadingEditorPresenter.View {
        private String editorText = "";
        private Listener listener;

        public void fake_launchSpeedReadingLocally() {
            listener.requestLaunchLocally(editorText);
        }

        public static FakeSpeedReadingEditorView withEnteredText(String editorText) {
            FakeSpeedReadingEditorView fakeSpeedReadingEditorView = new FakeSpeedReadingEditorView();
            fakeSpeedReadingEditorView.editorText = editorText;
            return fakeSpeedReadingEditorView;
        }

        @Override
        public void addListener(Listener listener) {
            this.listener = listener;
        }

        public void fake_launchSpeedReadingOnWear() {
            listener.requestLaunchWear(editorText);
        }
    }

    private class SpySpeedReadingLauncherDelegate implements SpeedReadingEditorPresenter.SpeedReadingLauncherDelegate {
        public boolean launchLocally_called;
        public String launchLocally_text;
        public boolean launchWear_called;
        public String launchWear_text;

        @Override
        public void launchLocally(String text) {
            launchLocally_called = true;
            launchLocally_text = text;
        }

        @Override
        public void launchWear(String text) {
            launchWear_called = true;
            launchWear_text = text;
        }
    }
}
