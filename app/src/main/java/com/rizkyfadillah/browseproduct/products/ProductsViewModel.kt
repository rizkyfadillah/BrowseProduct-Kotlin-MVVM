package com.rizkyfadillah.browseproduct.products

import com.rizkyfadillah.browseproduct.model.UIModel
import com.rizkyfadillah.browseproduct.model.Product
import com.rizkyfadillah.browseproduct.repository.ProductsRepository
import io.reactivex.Observable

/**
* Created by Rizky on 26/12/17.
*/

class ProductsViewModel(val productsRepository: ProductsRepository) {

    fun getProducts(query: String): Observable<UIModel<List<Product>>> {
        return productsRepository.getProducts("android", "test", query, 12, 1)
    }

}