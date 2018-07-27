package com.rizkyfadillah.popularmovies

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.rizkyfadillah.popularmovies.products.model.Product
import com.rizkyfadillah.popularmovies.common.model.UIModel
import com.rizkyfadillah.popularmovies.products.repository.ProductsRepository
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    lateinit var productsViewModel: ProductsViewModel
//    lateinit var productsRepository: ProductsRepository

    @Before
    fun setup() {
//        productsRepository = mock()
//        productsViewModel = ProductsViewModel(productsRepository)
    }

    @Test
    fun test() {
        val productsRepository = mock<ProductsRepository>()

        val iphones = listOf(Product("", "iPhone X", ""))
        val observableResult: Observable<UIModel<List<Product>>> = Observable.just(UIModel.success(iphones, "success"))
        val observableProductcs = productsRepository.getProducts("android", "test", "iphone", 12, 1)

        whenever(observableProductcs).thenReturn(observableResult)

        observableProductcs.subscribe {
            assertEquals(it.message, "success")
        }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}
