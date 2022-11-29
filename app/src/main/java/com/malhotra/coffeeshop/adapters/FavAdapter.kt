package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.FavRvBinding
import com.malhotra.coffeeshop.fragments.FavFragmentDirections
import com.malhotra.coffeeshop.modelclass.FavList
import com.malhotra.coffeeshop.modelclass.MenuItems

class FavAdapter(val context: Context, var favList : List<FavList>,
                 val listener: OnRemoveListener)
    :RecyclerView.Adapter<FavAdapter.FavViewHolder>(){

    fun filterList(newList: List<FavList>) {
        favList = newList
        notifyDataSetChanged()
    }

    private lateinit var menuList : MenuItems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.fav_rv, parent, false))
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val favList = favList[position]

        holder.binding.favTitle.text = favList.title
        holder.binding.favImage.setImageResource(favList.image)
        holder.binding.price.text = favList.price.toString()

        holder.binding.removeBtn.setOnClickListener {
            listener.onRemoveClicked(favList)
            Toast.makeText(context,
                "${favList.title} removed from Favourites",
                Toast.LENGTH_SHORT)
                .show()
            notifyDataSetChanged()
        }
        holder.binding.favLayout.setOnClickListener {
            menuList = MenuItems(favList.title, favList.image, favList.price, favList.des)
            val action = FavFragmentDirections.actionFavFragmentToDescriptionFragment2(menuList)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FavRvBinding.bind(view)
    }
}

interface OnRemoveListener {
    fun onRemoveClicked(list: FavList)
}