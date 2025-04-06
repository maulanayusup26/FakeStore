package com.example.fakestore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.LogIn.data.model.CartEntity
import com.example.fakestore.databinding.ItemCartBinding

class CartAdapter(
    private val items: MutableList<CartEntity>,
    private val onDelete: (CartEntity) -> Unit,
    private val onUpdate: (CartEntity) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvCartTitle.text = item.title
            tvCartPrice.text = "$${item.price}"
            tvQuantity.text = "Qty: ${item.quantity}"
            Glide.with(imgCart.context).load(item.image).into(imgCart)

            btnDelete.setOnClickListener { onDelete(item) }

            btnPlus.setOnClickListener {
                item.quantity += 1
                onUpdate(item)
            }

            btnMinus.setOnClickListener {
                if (item.quantity > 1) {
                    item.quantity -= 1
                    onUpdate(item)
                } else {
                    onDelete(item) // auto delete kalau qty = 0
                }
            }
        }

    }

    fun updateData(newItems: List<CartEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
