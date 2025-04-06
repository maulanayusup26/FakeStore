package com.example.fakestore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.LogIn.data.model.CartEntity
import com.example.fakestore.R

class CheckoutAdapter : RecyclerView.Adapter<CheckoutAdapter.SummaryViewHolder>() {

    private var cartItems: List<CartEntity> = listOf()

    fun setData(newItems: List<CartEntity>) {
        cartItems = newItems
        notifyDataSetChanged()
    }

    inner class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvQty: TextView = itemView.findViewById(R.id.tvQty)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout, parent, false)
        return SummaryViewHolder(view)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val item = cartItems[position]
        holder.tvName.text = item.title
        holder.tvQty.text = "Qty: ${item.quantity}"
        holder.tvPrice.text = "Rp ${item.price.toInt() * item.quantity}"

        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.imgProduct)
    }
}
