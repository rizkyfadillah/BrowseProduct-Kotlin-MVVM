package com.rizkyfadillah.browseproduct.repository

import com.rizkyfadillah.browseproduct.api.ProductService
import com.rizkyfadillah.browseproduct.ProductResponse
import com.rizkyfadillah.browseproduct.model.UIModel
import com.rizkyfadillah.browseproduct.model.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
* Created by Rizky on 26/12/17.
*/

class ProductsRepository(val productService: ProductService) {

    fun getProducts(device: String, source: String, q: String, rows: Int, start: Int): Observable<UIModel<List<Product>>> {
        return productService.getAce(device, source, q, rows , start)
                .map {
                    val products = mapProducts().apply(it.data.products)
                    UIModel.success(products, "success")
                }
                .onErrorReturn { UIModel.error(it.message) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .startWith(UIModel.loading())
    }

    fun mapProducts(): Function<List<ProductResponse>, List<Product>> {
        return Function {
            val products = mutableListOf<Product>()
            for (productResponse in it) {
                products.add(Product(productResponse.id, productResponse.name, productResponse.image_url))
            }
            products
        }
    }


}