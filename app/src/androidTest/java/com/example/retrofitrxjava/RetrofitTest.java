package com.example.retrofitrxjava;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by jonathanhavstad on 10/5/16.
 */
@RunWith(AndroidJUnit4.class)
public class RetrofitTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(mActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void testRetrofitFinished() throws Exception {
        onView(withText("Hello World!")).perform(click());
        assertTrue(mActivityTestRule.getActivity().isCompleted());
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(mActivityTestRule.getActivity().getCountingIdlingResource());
    }
}
