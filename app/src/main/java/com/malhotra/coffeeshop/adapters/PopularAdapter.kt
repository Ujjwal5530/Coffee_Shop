package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.PopularLayoutBinding
import com.malhotra.coffeeshop.fragments.HomeFragmentDirections
import com.malhotra.coffeeshop.modelclass.MenuItems

class PopularAdapter(val context: Context, val menu_list : ArrayList<MenuItems>)
    :RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(LayoutInflater
            .from(context).inflate(R.layout.popular_layout, parent, false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val menuList = menu_list[position]

        holder.binding.menuTitle.text = menuList.title
        holder.binding.menuImage.setImageResource(menuList.image)
        holder.binding.price.text = menuList.price.toString()

        holder.binding.popularLayout.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(HomeFragmentDirections
                    .actionHomeFragmentToDescriptionFragment(menuList))
        }
    }

    override fun getItemCount(): Int {
        return menu_list.size
    }

    class PopularViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = PopularLayoutBinding.bind(view)
    }
}