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

    public static class SpeedReadingEditorPresenter {

        private final View view;
        private final SpeedReadingLauncherDelegate speedReadingLauncherDelegate;

        public interface View
        {
        }

        public interface SpeedReadingLauncherDelegate
        {

        }

        public SpeedReadingEditorPresenter(View view, SpeedReadingLauncherDelegate speedReadingLauncherDelegate) {
            this.view = view;
            this.speedReadingLauncherDelegate = speedReadingLauncherDelegate;
        }
    }

    private static class FakeSpeedReadingEditorView implements SpeedReadingEditorPresenter.View {
        public void fake_launchSpeedReadingLocally() {

        }
    }

    private class SpySpeedReadingLauncherDelegate implements SpeedReadingEditorPresenter.SpeedReadingLauncherDelegate {
        public boolean launchLocally_called;
    }
}
