package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.SummaryRvBinding
import com.malhotra.coffeeshop.modelclass.SummaryList

class SummaryAdapter(val context: Context, val list : List<SummaryList>)
    : RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        return SummaryViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.summary_rv, parent, false))
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val list = list[position]
        holder.binding.cartTitle.text = list.title
        holder.binding.cartImage.setImageResource(list.image)
        holder.binding.cartItemPrice.text = list.price.toString()
        holder.binding.quan.text = list.quantity.toString()

        holder.binding.cartTotalPrice.text = String.format("%.2f", list.quantity * list.price)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SummaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SummaryRvBinding.bind(view)
    }

}