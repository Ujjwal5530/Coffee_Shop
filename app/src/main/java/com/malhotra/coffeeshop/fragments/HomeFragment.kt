package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.adapters.CategoryAdapter
import com.malhotra.coffeeshop.adapters.PopularAdapter
import com.malhotra.coffeeshop.databinding.FragmentHomeBinding
import com.malhotra.coffeeshop.modelclass.CategoryList
import com.malhotra.coffeeshop.modelclass.MenuItems
import com.malhotra.coffeeshop.viewmodel.ProfileViewModel

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: CategoryAdapter
    lateinit var oldList: ArrayList<CategoryList>
    val profileViewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.hide()

        profileViewModel.getInfo(1).observe(viewLifecycleOwner) {
            binding.homeName.text = getString(R.string.hi_name, it.name)
        }

        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        adapter = CategoryAdapter(requireContext(), getCategoryList())
        binding.rvCategories.adapter = adapter
        oldList = getCategoryList()


        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvPopular.adapter = PopularAdapter(requireContext(), getMenuList())

        binding.findBar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //newCategoryList(p0)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newCategoryList(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
                //newCategoryList(p0)
            }

        })

        return binding.root
    }

   fun newCategoryList(newText : CharSequence?){
       val newList = arrayListOf<CategoryList>()

       for (i in oldList){
           if (i.title!!.lowercase().contains(newText.toString().lowercase())){
               newList.add(i)
           }
       }
       if(newList.isEmpty()){
           adapter.newCategoryList(oldList)
           binding.findBar.error = "No Category Found"
       } else adapter.newCategoryList(newList)
   }

    private fun getCategoryList() : ArrayList<CategoryList>{
        val categoryList = arrayListOf<CategoryList>()

        categoryList.add(CategoryList(1,R.drawable.coffee_mug, "Hot Drinks"))
        categoryList.add(CategoryList(2,R.drawable.latte_art, "Latte"))
        categoryList.add(CategoryList(3,R.drawable.iced_coffee, "Iced Coffee"))
        categoryList.add(CategoryList(4,R.drawable.donut, "Donuts"))
        categoryList.add(CategoryList(5,R.drawable.bagels, "Bagels"))
        categoryList.add(CategoryList(6,R.drawable.muffin, "Muffins"))

        return categoryList
    }

    private fun getMenuList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Iced Caramel", R.drawable.iced_caramel_latte,3.19, getString(R.string.iced_caramel_latte)))
        menuList.add(MenuItems("Creamy Maple", R.drawable.creamy_maple,2.29, getString(R.string.creamy_maple)))
        menuList.add(MenuItems("French Vanilla", R.drawable.french_vanilla,2.49, getString(R.string.french_vanilla)))
        menuList.add(MenuItems("Pumpkin Spice", R.drawable.pumkin_spice_iced,3.99, getString(R.string.pumpkin_spice_iced)))
        menuList.add(MenuItems("Fruit Muffin", R.drawable.fruit_muffin,1.99, getString(R.string.fruit_muffin)))
        menuList.add(MenuItems("Four Cheese", R.drawable.four_cheese_bagel,2.49, getString(R.string.four_cheese_bagel)))

        return menuList
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}