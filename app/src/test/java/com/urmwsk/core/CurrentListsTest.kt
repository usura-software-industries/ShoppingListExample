package com.urmwsk.core

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.feature.shoppinglist.current.ShoppingListContract
import com.urmwsk.feature.shoppinglist.current.ShoppingListPresenter
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class CurrentListsTest {

    @Mock
    lateinit var view: ShoppingListContract.View
    @Mock
    lateinit var provider: ShoppingListProvider
    lateinit var presenter: ShoppingListPresenter

    private val testData: List<ShoppingListModel> = arrayListOf(ShoppingListModel("testId", false, "testTitle", 10))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testDataRead() {
        presenter = ShoppingListPresenter(provider)
        whenever(provider.getShoppingLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)
        val expectedResult = arrayListOf(ShoppingListItem(testData[0], presenter.archiveItemClick))
        verify(view).setList(expectedResult)
    }

    @Test
    fun testAddShoppingList() {
        presenter = ShoppingListPresenter(provider)
        val single: Single<List<ShoppingListModel>> = Single.create { emitter ->
            emitter.onError(Exception())
        }
        whenever(provider.getShoppingLists()).thenReturn(single)
        presenter.bindView(view)

        val testTitle = "testTitle"

        val titleCapture = argumentCaptor<ShoppingListModel>()
        presenter.addNewShoppingList(testTitle)
        verify(provider).insertShoppingList(titleCapture.capture())
        assertEquals(testTitle, titleCapture.firstValue.title)
        assertEquals(presenter.items[0].shoppingList.title, titleCapture.firstValue.title)

    }

    @Test
    fun testListSelected() {
        presenter = ShoppingListPresenter(provider)
        whenever(provider.getShoppingLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)

        presenter.itemSelected(0)
        verify(view).showDetails(testData[0].id)
    }

    @Test
    fun testArchiveList() {
        presenter = ShoppingListPresenter(provider)
        whenever(provider.getShoppingLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)

        presenter.archiveList(presenter.items[0])
        verify(provider).archiveList(testData[0])
    }
}