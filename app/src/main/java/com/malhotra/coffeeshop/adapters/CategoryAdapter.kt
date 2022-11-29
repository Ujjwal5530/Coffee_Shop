package com.malhotra.coffeeshop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.CategoriesLayoutBinding
import com.malhotra.coffeeshop.fragments.HomeFragmentDirections
import com.malhotra.coffeeshop.modelclass.CategoryList

class CategoryAdapter(private val context: Context, private var category_list : ArrayList<CategoryList>)
    :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    fun newCategoryList(newList : ArrayList<CategoryList>){
        category_list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.categories_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryList = category_list[position]

        holder.binding.categoryImage.setImageResource(categoryList.image)
        holder.binding.categoryTitle.text = categoryList.title

        holder.binding.categoryBtn.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToMenuFragment(categoryList)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return category_list.size
    }

    class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = CategoriesLayoutBinding.bind(view)
    }
}