import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.onchat_android.MainActivity;
import com.example.onchat_android.R;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSuccessfulLogin() {
        // Enter the email and password
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextPersonName))
                .perform(ViewActions.typeText("ornasri5@gmail.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextPassword2))
                .perform(ViewActions.typeText("12345678+100"), ViewActions.closeSoftKeyboard());

        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        // Verify if the login is successful by checking if the MenuPage activity is launched

    }

    @Test
    public void testFailedLogin() {
        // Enter incorrect email and password
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextPersonName))
                .perform(ViewActions.typeText("invalid@gmail.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextPassword2))
                .perform(ViewActions.typeText("invalidpassword"), ViewActions.closeSoftKeyboard());

        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        // Verify if an error toast message is displayed
    }
}
