import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.onchat_android.MainActivity;
import com.example.onchat_android.MenuPage;
import com.example.onchat_android.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MenuPageTest {

    @Rule
    public ActivityScenarioRule<MenuPage> activityRule = new ActivityScenarioRule<>(MenuPage.class);

    @Before
    public void setUp() {
    }

    @Test
    public void testButtonFacilitiesClick() {
        // Perform a click on the buttonFacilities button
        Espresso.onView(ViewMatchers.withId(R.id.button5)).perform(ViewActions.click());
    }

    @Test
    public void testButtonContactInfoClick() {
        // Perform a click on the buttonContactInfo button
        Espresso.onView(ViewMatchers.withId(R.id.button4)).perform(ViewActions.click());
    }

    @Test
    public void testButtonDiningRoomClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button6)).perform(ViewActions.click());
    }
    @Test
    public void testButtonRestaurantClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button7)).perform(ViewActions.click());
    }
    @Test
    public void testButtonLocationsClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button8)).perform(ViewActions.click());
    }
    @Test
    public void testButtonAgentClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button9)).perform(ViewActions.click());
    }
    @Test
    public void testButtonEmergencyClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button10)).perform(ViewActions.click());

    }
    @Test
    public void testButtonWifiClick() {
        // Perform a click on the buttonWifi button
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click());

    }

}
