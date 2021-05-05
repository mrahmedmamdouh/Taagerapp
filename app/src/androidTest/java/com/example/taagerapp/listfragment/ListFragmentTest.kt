package com.example.taagerapp.listfragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.example.taagerapp.R
import com.example.taagerapp.ui.MainActivity
import com.example.taagerapp.ui.listfragment.ListFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    @get: Rule
    val fragmentTestRule: FragmentTestRule<MainActivity, ListFragment> = FragmentTestRule(
        MainActivity::class.java,
        ListFragment::class.java
    )

    @Test
    fun test_listFragment_inView() {
        launch(MainActivity::class.java)
        sleep(2000)
        if (getChildCount() > 0) {
            Espresso.onView(withId(R.id.list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(90))
        }
    }

    private fun getChildCount(): Int {
        val recyclerView: RecyclerView = fragmentTestRule.activity.findViewById(R.id.list)
        return recyclerView.adapter!!.itemCount
    }

}