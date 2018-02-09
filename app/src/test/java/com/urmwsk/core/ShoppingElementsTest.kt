package com.urmwsk.core

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsContract
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsPresenter
import com.urmwsk.feature.shoppinglist.details.view.ShoppingListElementItem
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ShoppingElementsTest {
    @Mock
    lateinit var view: ShoppingListDetailsContract.View
    @Mock
    lateinit var provider: ShoppingListProvider
    lateinit var presenter: ShoppingListDetailsPresenter

    private val testShoppingListId = "testId"
    private val testList = ShoppingListModel(testShoppingListId, false, "testTitle", 10)
    private val testNonPurchasedElement = ShoppingListElementModel("nonP", testShoppingListId, false, "nonPurchased", 1)
    private val testPurchasedElement = ShoppingListElementModel("p", testShoppingListId, true, "purchased", 0)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSetEditable() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, false)

        val expectedResult = arrayListOf(ShoppingListElementItem(testData[0], false, presenter.deleteElementClick),
                ShoppingListElementItem(testData[1], false, presenter.deleteElementClick))
        verify(view).setList(expectedResult)
        verify(view).setAddVisible(false)
        verify(view).setItemsClickable(false)
    }

    @Test
    fun testDataRead() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, true)

        val expectedResult = arrayListOf(ShoppingListElementItem(testData[0], true, presenter.deleteElementClick),
                ShoppingListElementItem(testData[1], true, presenter.deleteElementClick))
        verify(view).setList(expectedResult)
    }

    @Test
    fun testAddingElement() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, true)

        val testTitle = "third"

        val titleCapture = argumentCaptor<ShoppingListElementModel>()
        presenter.addShoppingElement(testTitle)
        verify(provider).insertShoppingElement(titleCapture.capture())
        Assert.assertEquals(testTitle, titleCapture.firstValue.title)
        Assert.assertEquals(testTitle, presenter.items[0].element.title)
    }

    //also tests sorting
    @Test
    fun testSetItemPurchased() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, true)

        presenter.itemSelected(0)

        val expectedResult = arrayListOf(
                ShoppingListElementItem(ShoppingListElementModel("nonP", testShoppingListId, true, "nonPurchased", 1), true, presenter.deleteElementClick),
                ShoppingListElementItem(ShoppingListElementModel("p", testShoppingListId, true, "purchased", 0), true, presenter.deleteElementClick))

        verify(view, times(2)).setList(expectedResult)
    }

    //also tests sorting
    @Test
    fun testSetItemNonPurchased() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, true)

        presenter.itemSelected(1)

        val expectedResult = arrayListOf(
                ShoppingListElementItem(ShoppingListElementModel("nonP", testShoppingListId, false, "nonPurchased", 1), true, presenter.deleteElementClick),
                ShoppingListElementItem(ShoppingListElementModel("p", testShoppingListId, false, "purchased", 0), true, presenter.deleteElementClick))

        verify(view, times(2)).setList(expectedResult)
    }

    @Test
    fun deleteItem() {
        presenter = ShoppingListDetailsPresenter(provider)
        val testData = arrayListOf(testNonPurchasedElement, testPurchasedElement)
        whenever(provider.getListById(testShoppingListId)).thenReturn(Single.just(testList))
        whenever(provider.getShoppingElement(testShoppingListId)).thenReturn(Single.just(testData))
        presenter.bindView(view)
        presenter.setData(testShoppingListId, true)

        presenter.deleteElementClick.invoke(presenter.items[0])
        verify(provider).deleteShoppingElement(testNonPurchasedElement)

        val expectedResult = arrayListOf(
                ShoppingListElementItem(ShoppingListElementModel("p", testShoppingListId, false, "purchased", 0), true, presenter.deleteElementClick))

        verify(view, times(2)).setList(expectedResult)
    }
}