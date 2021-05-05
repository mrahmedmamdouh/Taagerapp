package com.example.taagerapp.detailsfragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.example.taagerapp.R
import com.example.taagerapp.ui.MainActivity
import com.example.taagerapp.ui.listfragment.ListFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest{

    @get: Rule
    val fragmentTestRule: FragmentTestRule<MainActivity, ListFragment> = FragmentTestRule(
        MainActivity::class.java,
        ListFragment::class.java
    )

    @Test
    fun test_DetailsFrament_inView() {
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(2000)
        if (getChildCount() > 0) {
            onView(withId(R.id.list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(90)).perform(click())
        }
        onView(withId(R.id.name)).check(matches(withText("name 89")))
    }

    @Test
    fun test_back_to_ListFragment(){
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(2000)
        if (getChildCount() > 0) {
            onView(withId(R.id.list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(90)).perform(click())
        }
        pressBack()
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    private fun getChildCount(): Int {
        val recyclerView: RecyclerView = fragmentTestRule.activity.findViewById(R.id.list)
        return recyclerView.adapter!!.itemCount
    }

}