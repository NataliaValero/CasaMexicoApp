package com.example.casamexicoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casamexicoapp.databinding.CartItemBinding
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.Formatter

class CartItemAdapter (var cartItems: List<CartItem>,
    var listener: CartItemAdapterListener? =null,
    var isCartReyclerView: Boolean = true) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem) = with(binding) {

            productNameTv.setText(cartItem.name)
            addItemView.itemQuantityTv.setText(cartItem.productQuantity.toString())
            priceTv.setText(Formatter.getFormattedCurrency(cartItem.total))

            Glide.with(root.context)
                .load(cartItem.imageUrl)
                .override(90,90)
                .into(imageCartItemIv)


            // visibilidad se muestra si es para el recycle view del cart
            /*Remueve los iconos de plus, minus and remove cuando no es para el recycle view de cart*/
            removeIcon.isVisible = isCartReyclerView

            if(!isCartReyclerView) {
                addItemView.minusTv.setText("")
                addItemView.plusTv.setText("")
            }

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


