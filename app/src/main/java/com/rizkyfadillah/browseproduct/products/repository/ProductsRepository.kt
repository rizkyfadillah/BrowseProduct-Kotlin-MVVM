package com.rizkyfadillah.browseproduct.products.repository

import com.rizkyfadillah.browseproduct.products.api.ProductService
import com.rizkyfadillah.browseproduct.products.api.ProductResponse
import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.products.model.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
* Created by Rizky on 26/12/17.
*/

class ProductsRepository @Inject constructor(private val productService: ProductService) {

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