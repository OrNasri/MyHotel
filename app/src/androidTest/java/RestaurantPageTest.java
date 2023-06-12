import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.onchat_android.MenuPage;
import com.example.onchat_android.R;
import com.example.onchat_android.restaurant.RestaurantPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RestaurantPageTest {

    @Rule
    public ActivityScenarioRule<RestaurantPage> activityRule = new ActivityScenarioRule<>(RestaurantPage.class);

    @Before
    public void setUp() {
    }

    @Test
    public void testFloatingActionButtonClick_bagShop() {
        // click on the bagShop floating action button
        Espresso.onView(ViewMatchers.withId(R.id.bagShop)).perform(ViewActions.click());
    }

    @Test
    public void testFloatingActionButtonClick_backToMenuRestaurant() {
        // click on the backToMenuRestaurant floating action button
        Espresso.onView(ViewMatchers.withId(R.id.backToMenuRestaurant)).perform(ViewActions.click());
    }

}
