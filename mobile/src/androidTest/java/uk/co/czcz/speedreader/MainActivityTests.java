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
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp(); // Interesting how the junit4 methods (@Before) for doing setup can screw you over!
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Solo.Config config = new Solo.Config();
        config.timeout_small = (int) TimeUnit.SECONDS.toMillis(1);
        solo = new Solo(getInstrumentation(), getActivity());
        solo.unlockScreen();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void whenTheMainActivityIsStarted_theSpeedReadFragmentIsDisplayed() {
        assertTrue(solo.waitForFragmentByTag(SpeedReadingFragment.TAG));
    }

}
