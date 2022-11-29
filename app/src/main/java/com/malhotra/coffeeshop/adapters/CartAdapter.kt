package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.CheckoutRvBinding
import com.malhotra.coffeeshop.modelclass.CartList

class CartAdapter(val context: Context, val cartList : List<CartList>, val listener: OnDeleteListener)
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.checkout_rv, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartList = cartList[position]

        holder.binding.cartTitle.text = cartList.title
        holder.binding.cartImage.setImageResource(cartList.image)
        holder.binding.quantity.text = cartList.quantity.toString()
        holder.binding.cartItemPrice.text = cartList.price.toString()
        holder.binding.cartTotalPrice.text = String.format("%.2f", (cartList.price * cartList.quantity))

        holder.binding.plus.setOnClickListener {
            while (cartList.quantity < 9) {
                cartList.quantity ++
                holder.binding.quantity.text = cartList.quantity.toString()
                holder.binding.cartTotalPrice.text = String.format("%.2f", (cartList.price * cartList.quantity))
                listener.onQuantityChange(cartList.quantity, cartList.id!!)
                notifyItemChanged(position)
                break
            }
        }

        holder.binding.minus.setOnClickListener {
            while (cartList.quantity > 1) {
                cartList.quantity--
                holder.binding.quantity.text = cartList.quantity.toString()
                holder.binding.cartTotalPrice.text = String.format("%.2f", (cartList.price * cartList.quantity))
                listener.onQuantityChange(cartList.quantity, cartList.id!!)
                notifyItemChanged(position)
                break
            }
        }

        holder.binding.removeBtn.setOnClickListener {
            listener.onDeleteClick(cartList)
            Toast.makeText(context, "${cartList.title} removed from your Cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    class CartViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = CheckoutRvBinding.bind(view)
    }

}

interface OnDeleteListener {
    fun onDeleteClick(list: CartList)

    fun onQuantityChange(quantity : Int, id : Int)
}