package com.rizkyfadillah.browseproduct.products.di

import com.rizkyfadillah.browseproduct.products.ui.ProductsActivity
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by Rizky on 09/04/18.
 */
@ProductsScope
@Subcomponent(modules = arrayOf(ProductsModule::class))
interface ProductsComponent {

    fun inject(productsActivity: ProductsActivity)

}