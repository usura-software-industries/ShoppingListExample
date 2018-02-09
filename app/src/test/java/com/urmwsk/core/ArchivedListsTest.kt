package com.urmwsk.core

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListContract
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListPresenter
import com.urmwsk.feature.shoppinglist.archived.view.ArchivedShoppingListItem
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ArchivedListsTest {

    @Mock
    lateinit var view: ArchivedShoppingListContract.View
    @Mock
    lateinit var provider: ShoppingListProvider
    lateinit var presenter: ArchivedShoppingListPresenter

    private val testData: List<ShoppingListModel> = arrayListOf(ShoppingListModel("testId", true, "testTitle", 10))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testDataRead() {
        presenter = ArchivedShoppingListPresenter(provider)
        whenever(provider.getArchivedLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)
        val expectedResult = arrayListOf(ArchivedShoppingListItem(testData[0]))
        verify(view).setList(expectedResult)
    }

    @Test
    fun testListSelected() {
        presenter = ArchivedShoppingListPresenter(provider)
        whenever(provider.getArchivedLists()).thenReturn(Single.just(testData))
        presenter.bindView(view)

        presenter.itemSelected(0)
        verify(view).showDetails(testData[0].id)
    }
}