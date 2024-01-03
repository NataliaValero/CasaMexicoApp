package com.example.casamexicoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casamexicoapp.databinding.CartItemBinding
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.PriceFormatter

class CartItemAdapter (var cartItems: List<CartItem>,
    var listener: CartItemAdapterListener? =null) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem) = with(binding) {

            productNameTv.setText(cartItem.name)
            addItemView.itemQuantityTv.setText(cartItem.productQuantity.toString())
            priceTv.setText(PriceFormatter.getCurrencyTotal(cartItem.total))

            Glide.with(root.context)
                .load(cartItem.imageUrl)
                .override(90,90)
                .into(imageCartItemIv)



            // Configurar listener para el boton plus
            addItemView.plusTv.setOnClickListener {

                listener?.onQuantityUpdated(cartItem, true)
                notifyDataSetChanged()
            }

            // Configurar listener para el boton minus
            addItemView.minusTv.setOnClickListener {

                if (cartItem.productQuantity > 1) {

                listener?.onQuantityUpdated(cartItem, false)

                notifyDataSetChanged()

                }
            }

            // Configurar remove listener

            removeIcon.setOnClickListener{
                listener?.onRemoveClick(cartItem)
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder =
        CartItemViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(cartItems.get(position))
    }

    // Actualizar lista
    fun setCartItemsList(newList: List<CartItem>) {
        cartItems = newList
        notifyDataSetChanged()
    }


    interface CartItemAdapterListener {

        fun onRemoveClick(cartItem: CartItem)
        fun onQuantityUpdated(cartItem: CartItem, isAddition : Boolean)
    }


}


