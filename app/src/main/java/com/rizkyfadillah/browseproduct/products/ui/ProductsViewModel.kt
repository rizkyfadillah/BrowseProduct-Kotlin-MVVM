package com.rizkyfadillah.browseproduct.products.ui

import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.products.model.Product
import com.rizkyfadillah.browseproduct.products.repository.ProductsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
* Created by Rizky on 26/12/17.
*/

class ProductsViewModel @Inject constructor(private val productsRepository: ProductsRepository) {

    fun getProducts(query: String): Observable<UIModel<List<Product>>> {
        return productsRepository.getProducts("android", "test", query, 12, 1)
    }

}