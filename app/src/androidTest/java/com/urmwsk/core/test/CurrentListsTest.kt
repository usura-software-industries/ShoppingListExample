package com.urmwsk.core.test

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.urmwsk.core.R
import com.urmwsk.core.android.App
import com.urmwsk.core.android.App.Companion.appComponent
import com.urmwsk.core.commons.InnerClickAction
import com.urmwsk.core.commons.RecyclerViewMatcher.Companion.withRecyclerView
import com.urmwsk.core.stub.DaggerStubAppComponent
import com.urmwsk.core.stub.StubAppModule
import com.urmwsk.core.stub.StubShoppingListProvider
import com.urmwsk.feature.shoppinglist.MainActivity
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsActivity
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CurrentListsTest {

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
        appComponent = testComponent
        activityRule.launchActivity(Intent())
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testAddShoppingList() {
        val testText = "testText"

        onView(withId(R.id.addShoppingList)).perform(click())
        onView(withHint(R.string.shopping_list_default_title)).perform(typeText(testText))
        onView(withId(R.id.add)).perform(click())
        onView(withRecyclerView(R.id.shoppingList).atPosition(0)).check(matches(hasDescendant(withText(testText))))
    }

    @Test
    fun testGoToDetails() {
        onView(withRecyclerView(R.id.shoppingList).atPosition(0)).perform(click())
        intended(allOf(
                hasComponent(ShoppingListDetailsActivity::class.java.name),
                hasExtra("idKey", StubShoppingListProvider.testItem1.id),
                hasExtra("editKey", true)))
    }

    @Test
    fun testArchiveClick() {
        onView(withId(R.id.shoppingList)).perform(RecyclerViewActions.actionOnItemAtPosition<ShoppingListItem.VH>(0, InnerClickAction.clickChildViewWithId(R.id.shoppingListArchive)))
        assertEquals(activityRule.activity.findViewById<RecyclerView>(R.id.shoppingList).adapter.itemCount, 0)
    }

}