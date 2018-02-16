package com.urmwsk.core.test

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.urmwsk.core.R
import com.urmwsk.core.android.App
import com.urmwsk.core.commons.InnerClickAction
import com.urmwsk.core.commons.RecyclerViewMatcher
import com.urmwsk.core.stub.DaggerStubAppComponent
import com.urmwsk.core.stub.StubAppModule
import com.urmwsk.core.stub.StubShoppingListProvider
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsActivity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingElementsEditTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(ShoppingListDetailsActivity::class.java, true, false)

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App

        val testComponent = DaggerStubAppComponent.builder()
                .stubAppModule(StubAppModule(app))
                .build()
        App.appComponent = testComponent

        val intent = Intent()
        intent.putExtra("idKey", StubShoppingListProvider.testItem1.id)
        intent.putExtra("editKey", true)

        activityRule.launchActivity(intent)
    }

    @Test
    fun testSetTitle() {
        onView(withText(StubShoppingListProvider.testItem1.title)).check(matches(isDisplayed()))
    }

    @Test
    fun testAddShoppingListElement() {
        val testText = "testText"

        onView(ViewMatchers.withId(R.id.addShoppingListElement)).perform(ViewActions.click())
        onView(ViewMatchers.withHint(R.string.shopping_list_add_item_hint)).perform(ViewActions.typeText(testText))
        onView(ViewMatchers.withId(R.id.add)).perform(ViewActions.click())
        onView(RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(0)).check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(testText))))
    }

    @Test
    fun testMarkingAsPurchased() {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(0)).perform(ViewActions.click())
        onView((RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(0))).check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(StubShoppingListProvider.testElement2.title))))
    }

    @Test
    fun testMarkingAsNonPurchased() {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(1)).perform(ViewActions.click())
        onView((RecyclerViewMatcher.withRecyclerView(R.id.shoppingList).atPosition(0))).check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(StubShoppingListProvider.testElement2.title))))
    }

    @Test
    fun testDeleteElement() {
        onView(withId(R.id.shoppingList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, InnerClickAction.clickChildViewWithId(R.id.deleteShoppingElement)))
        assertEquals(activityRule.activity.findViewById<RecyclerView>(R.id.shoppingList).adapter.itemCount, 1)
    }

}
