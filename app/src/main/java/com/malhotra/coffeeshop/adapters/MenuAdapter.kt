package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.MenuLayoutBinding
import com.malhotra.coffeeshop.fragments.MenuFragmentDirections
import com.malhotra.coffeeshop.modelclass.MenuItems

class MenuAdapter(val context: Context, var menu_list : ArrayList<MenuItems>)
    :RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){

    fun searchList(newList : ArrayList<MenuItems>){
        menu_list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater
            .from(context).inflate(R.layout.menu_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuList = menu_list[position]

        holder.binding.menuTitle.text = menuList.title
        holder.binding.menuImage.setImageResource(menuList.image)
        holder.binding.price.text = menuList.price.toString()

        holder.binding.menuLayout.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(MenuFragmentDirections.actionMenuFragmentToDescriptionFragment(menuList))
        }
    }

    override fun getItemCount(): Int {
        return menu_list.size
    }

    class MenuViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = MenuLayoutBinding.bind(view)
    }
}