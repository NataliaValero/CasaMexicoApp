package com.example.casamexicoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.casamexicoapp.R
import com.example.casamexicoapp.databinding.ProductItemBinding
import com.example.casamexicoapp.model.Product

class ProductsAdapter (var products: List<Product>): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) = with(binding) {
            productNameTv.setText(product.name)
            productPriceTv.setText(product.price.toString())

            Glide.with(root.context)
                .load("https://freepngimg.com/thumb/puppy/33648-3-golden-retriever-puppy-transparent-image.png")
                .into(imageProduct)
        }
    }


    // Create a view holder for each in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun getItemCount(): Int {
        return products.size
    }


    // Bind data to each item in ther RecyclerView
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(products.get(position))


    }

    // Actualizar lista

    fun setList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}