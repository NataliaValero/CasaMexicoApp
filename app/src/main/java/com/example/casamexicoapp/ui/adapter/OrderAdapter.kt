package com.example.casamexicoapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.casamexicoapp.databinding.OrderItemBinding
import com.example.casamexicoapp.model.Cart


class OrderAdapter(var orderItems: List<Cart>, val listener: onClickListener) : RecyclerView.Adapter<OrderAdapter.OrderItemHolder>() {


    inner class OrderItemHolder(private val binding: OrderItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cart) = with(binding) {
            // id number
            orderIdTv.setText("Order #${item.getFormattedId()}")
            // fecha formateada
            orderDatetv.setText(item.getFormattedDate())
            // cantidad de items
            orderCountTv.setText("${item.cartItemList.size} count")
            // total de la orden
            orderTotalTv.setText(item.getFormattedTotal())

            background.setOnClickListener{
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemHolder =
        OrderItemHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))



    override fun getItemCount(): Int {
        return orderItems.size
    }

    override fun onBindViewHolder(holder: OrderItemHolder, position: Int) {
        holder.bind(orderItems.get(position))
    }

    // Actualizar lista
    fun setOrderItemsList(newList: List<Cart>) {
        orderItems = newList
        notifyDataSetChanged()
    }


    interface onClickListener {
        fun onItemClick(item: Cart)
    }
}