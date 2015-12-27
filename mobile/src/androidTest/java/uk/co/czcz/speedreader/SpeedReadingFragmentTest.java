package uk.co.czcz.speedreader;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class SpeedReadingFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public SpeedReadingFragmentTest() {
        super(MainActivity.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Solo.Config config = new Solo.Config();
        config.timeout_small = (int) TimeUnit.SECONDS.toMillis(1);
        solo = new Solo(getInstrumentation(), getActivity());
        assertTrue(solo.waitForFragmentByTag(SpeedReadingFragment.TAG));
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    @Test
    public void whenTheSpeedReadingFragmentIsDisplayed_aSpeedReadEditorViewIsOnScreen() {
        assertTrue(solo.waitForView(SpeedReadingEditorView.class));
    }

    @Test
    public void whenTheEditorLaunchesALocalSpeedReadingSession_ifThereIsNoEditorText_theEditorTextErrorStringIsSet()
    {
        solo.clickOnText("Read Locally");
        assertTrue(solo.waitForText("You can only speed read when you have entered text."));
    }
}
