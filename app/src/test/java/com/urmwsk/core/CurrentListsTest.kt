package com.urmwsk.core

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListContract
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListPresenter
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class CurrentListsTest {

    @Mock
    lateinit var view: CurrentShoppingListContract.View
    @Mock
    lateinit var provider: ShoppingListProvider
    lateinit var presenter: CurrentShoppingListPresenter

    private val testData: List<ShoppingListModel> = arrayListOf(ShoppingListModel("testId", false, "testTitle", 10))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testDataRead() {
        presenter = CurrentShoppingListPresenter(provider)
        whenever(provider.getActiveLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)
        val expectedResult = arrayListOf(ShoppingListItem(testData[0], presenter.archiveItemClick))
        verify(view).setList(expectedResult)
    }

    @Test
    fun testAddShoppingList() {
        presenter = CurrentShoppingListPresenter(provider)
        val single: Single<List<ShoppingListModel>> = Single.create { emitter ->
            emitter.onError(Exception())
        }
        whenever(provider.getActiveLists()).thenReturn(single)
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
        presenter = CurrentShoppingListPresenter(provider)
        whenever(provider.getActiveLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)

        presenter.itemSelected(0)
        verify(view).showDetails(testData[0].id)
    }

    @Test
    fun testArchiveList() {
        presenter = CurrentShoppingListPresenter(provider)
        whenever(provider.getActiveLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)

        presenter.archiveList(presenter.items[0])
        verify(provider).archiveList(testData[0])
    }
}