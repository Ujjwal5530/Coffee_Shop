package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.HistoryRvBinding
import com.malhotra.coffeeshop.fragments.OrderFragmentDirections
import com.malhotra.coffeeshop.modelclass.HistoryList

class OrderAdapter(val context: Context, var historyList: List<HistoryList>)
    :RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(){

    fun filterList(list : List<HistoryList>){
        historyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.history_rv, parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val historyList = historyList[position]

        holder.binding.orderTotal.text = String.format("%.2f", historyList.total)
        holder.binding.itemNo.text = historyList.items.toString()
        holder.binding.date.text = historyList.date
        holder.binding.orderNo.text = historyList.id.toString()

        holder.binding.orderBtn.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderToSummaryFragment(historyList)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return historyList.size
    }

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = HistoryRvBinding.bind(view)
    }
}