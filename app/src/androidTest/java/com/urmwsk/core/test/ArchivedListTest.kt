package com.urmwsk.core.test

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.urmwsk.core.R
import com.urmwsk.core.android.App
import com.urmwsk.core.commons.RecyclerViewMatcher
import com.urmwsk.core.stub.DaggerStubAppComponent
import com.urmwsk.core.stub.StubAppModule
import com.urmwsk.core.stub.StubShoppingListProvider
import com.urmwsk.feature.shoppinglist.MainActivity
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArchivedListTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App

        val testComponent = DaggerStubAppComponent.builder()
                .stubAppModule(StubAppModule(app))
                .build()
        App.appComponent = testComponent
        activityRule.launchActivity(Intent())
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testGoToDetails() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync({
            activityRule.activity.bottomNavigation.selectedItemId = R.id.navigation_shopping_archive
            activityRule.activity.supportFragmentManager.executePendingTransactions()
        })

        onView(RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(0)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                IntentMatchers.hasComponent(ShoppingListDetailsActivity::class.java.name),
                IntentMatchers.hasExtra("idKey", StubShoppingListProvider.testItem2.id),
                IntentMatchers.hasExtra("editKey", false)))
    }
}
