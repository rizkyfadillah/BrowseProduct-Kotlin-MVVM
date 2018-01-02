package com.rizkyfadillah.browseproduct.products

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.model.Product
import com.squareup.picasso.Picasso

/**
* Created by Rizky on 30/12/17.
*/

class ProductAdapter(private val productModelList: List<Product>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var context: Context? = null

    override fun getItemCount(): Int {
        return productModelList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productModelList[position]

        holder.bind(product)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageview)
        var tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)

        fun bind(product: Product) {
            Picasso.with(context)
                    .load(product.imageUrl)
                    .into(imageView)
            tvProductName.text = product.name
        }
    }

}