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

    @Test
    public void whenASpeedReadingEditorViewRequestsASpeedReadingSessionWhenEditorTextIsEmpty_theEditorIsGivenAnOpportunityToDisplayAnError() {
        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = FakeSpeedReadingEditorView.withEnteredText("");
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingLocally();

        assertThat(fakeSpeedReadingEditorView.displayNoTextError_called, is(true));
    }

    @Test
    public void whenASpeedReadingEditorViewRequestsASpeedReadingSession_onWear_WhenEditorTextIsEmpty_theEditorIsGivenAnOpportunityToDisplayAnError() {
        FakeSpeedReadingEditorView fakeSpeedReadingEditorView = FakeSpeedReadingEditorView.withEnteredText("");
        SpySpeedReadingLauncherDelegate spySpeedReadingLauncherDelegate = new SpySpeedReadingLauncherDelegate();
        new SpeedReadingEditorPresenter(fakeSpeedReadingEditorView, spySpeedReadingLauncherDelegate);

        fakeSpeedReadingEditorView.fake_launchSpeedReadingOnWear();

        assertThat(fakeSpeedReadingEditorView.displayNoTextError_called, is(true));
    }

    private static class FakeSpeedReadingEditorView implements SpeedReadingEditorPresenter.View {
        private String editorText = "";
        private Listener listener;
        public boolean displayNoTextError_called;

        public void fake_launchSpeedReadingLocally() {
            listener.requestLaunchLocally(editorText);
        }

        public static FakeSpeedReadingEditorView withEnteredText(String editorText) {
            FakeSpeedReadingEditorView fakeSpeedReadingEditorView = new FakeSpeedReadingEditorView();
            fakeSpeedReadingEditorView.editorText = editorText;
            return fakeSpeedReadingEditorView;
        }

        @Override
        public void displayNoTextError() {
            displayNoTextError_called = true;
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
