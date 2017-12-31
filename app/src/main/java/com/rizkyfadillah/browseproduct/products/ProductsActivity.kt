package com.rizkyfadillah.browseproduct.products

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rizkyfadillah.browseproduct.api.ProductService
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.model.UIModel
import com.rizkyfadillah.browseproduct.model.Product
import com.rizkyfadillah.browseproduct.repository.ProductsRepository
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class ProductsActivity : AppCompatActivity() {

    private val TAG = ProductsActivity::class.simpleName

    private val products = mutableListOf<Product>()

    private var productAdapter = ProductAdapter(products)

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aceService = ProductService.getAceService()
        val aceRepository = ProductsRepository(aceService)
        val productsViewModel = ProductsViewModel(aceRepository)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val edittextSearch: EditText = findViewById(R.id.et_search)

        compositeDisposable.add(
                RxTextView.textChanges(edittextSearch)
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .switchMap {
                            productsViewModel.getProducts(it.toString())
                        }
                        .subscribe({
                            showProducts(it)
                        })
        )
    }

    private fun showProducts(result: UIModel<List<Product>>) {
        if (result.uiState == UIModel.UIState.LOADING) {
            // show loading progress bar
            Log.d(TAG, "loading")
        } else if (result.uiState == UIModel.UIState.ERROR) {
            // show error
            showError(result.message)
        } else if (result.uiState == UIModel.UIState.SUCCESS) {
            // show result
            Log.d(TAG, "success")
            products.clear()
            result.data?.let { products.addAll(it) }
            productAdapter.notifyDataSetChanged()
        }
    }

    private fun showError(errorMessage: String?) {
        Log.d(TAG, "error: " + errorMessage)
    }

    override fun onStop() {
        compositeDisposable.dispose()
        super.onStop()
    }

}
