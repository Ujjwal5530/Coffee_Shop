@file:Suppress("DEPRECATION")

package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.adapters.MenuAdapter
import com.malhotra.coffeeshop.databinding.FragmentMenuBinding
import com.malhotra.coffeeshop.modelclass.MenuItems

class MenuFragment : Fragment() {

    val menuItems by navArgs<MenuFragmentArgs>()
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuList : ArrayList<MenuItems>
    lateinit var adapter : MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.show()

        binding.category.text = menuItems.menu.title

        when (menuItems.menu.id) {
            1 -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                //binding.rvMenu.adapter = null
                adapter = MenuAdapter(requireContext(), getHotList() )
                binding.rvMenu.adapter = adapter
                menuList = getHotList()
            }

            2 -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                //binding.rvMenu.adapter = null
                adapter = MenuAdapter(requireContext(), getLatteList() )
                binding.rvMenu.adapter = adapter
                menuList = getLatteList()
            }

            3 -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = MenuAdapter(requireContext(), getIcedList() )
                binding.rvMenu.adapter = adapter
                menuList = getIcedList()
            }

            4 -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = MenuAdapter(requireContext(), getDonutsList() )
                binding.rvMenu.adapter = adapter
                menuList = getDonutsList()
            }

            5 -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = MenuAdapter(requireContext(), getBagelsList() )
                binding.rvMenu.adapter = adapter
                menuList = getBagelsList()
            }

            else -> {
                binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = MenuAdapter(requireContext(), getMuffinsList() )
                binding.rvMenu.adapter = adapter
                menuList = getMuffinsList()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.bar_search)
        val searchView : SearchView = item.actionView as SearchView

        searchView.queryHint = "Find Menu Items..."
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                newMenuList(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun newMenuList(newText : String?){
        val newList = arrayListOf<MenuItems>()
        for (i in menuList){
            if (i.title.toString().lowercase().contains(newText!!.lowercase()))
                newList.add(i)
        }
        adapter.searchList(newList)
    }

    private fun getHotList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Coffee Mocha", R.drawable.coffee_mocha,2.19, getString(R.string.coffee_mocha)))
        menuList.add(MenuItems("Hot Chocolate", R.drawable.hot_chocolate,2.29, getString(R.string.hot_chocolate)))
        menuList.add(MenuItems("French Vanilla", R.drawable.french_vanilla,2.49, getString(R.string.french_vanilla)))
        menuList.add(MenuItems("Steeped Tea", R.drawable.steeped_tea,1.89, getString(R.string.steeped_tea)))
        menuList.add(MenuItems("White Chocolate", R.drawable.white_hot_chocolate,1.79, getString(R.string.white_hot_chocolate)))

        return menuList
    }

    private fun getLatteList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Hot Latte", R.drawable.latte,3.49, getString(R.string.hot_latte)))
        menuList.add(MenuItems("Mocha Latte", R.drawable.mocha_latte,3.79, getString(R.string.mocha_latte)))
        menuList.add(MenuItems("Vanilla Latte", R.drawable.vanilla_latte,3.69, getString(R.string.vanilla_latte)))
        menuList.add(MenuItems("Caramel Latte", R.drawable.caramel_latte,3.69, getString(R.string.caramel_latte)))

        return menuList
    }

    private fun getIcedList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Lemonade", R.drawable.lemonade,2.29, getString(R.string.lemonade)))
        menuList.add(MenuItems("Iced Caramel", R.drawable.iced_caramel_latte,3.19, getString(R.string.iced_caramel_latte)))
        menuList.add(MenuItems("Hazelnut Brew", R.drawable.hazelnut_cold_brew,2.99, getString(R.string.hazelnut_cold_brew)))
        menuList.add(MenuItems("Pumpkin Spice", R.drawable.pumkin_spice_iced,3.99, getString(R.string.pumpkin_spice_iced)))
        menuList.add(MenuItems("Peach Quencher", R.drawable.peach_quencher,2.79, getString(R.string.peach_quencher)))
        menuList.add(MenuItems("Vanilla Brew", R.drawable.vanilla_cold_brew,2.49, getString(R.string.vanilla_cold_brew)))

        return menuList
    }

    private fun getDonutsList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Creamy Maple", R.drawable.creamy_maple,2.29, getString(R.string.creamy_maple)))
        menuList.add(MenuItems("Boston Cream", R.drawable.boston_cream,2.19, getString(R.string.boston_cream)))
        menuList.add(MenuItems("Vanilla Dip", R.drawable.vanilla_dip,1.99, getString(R.string.vanilla_dip)))
        menuList.add(MenuItems("Canadian Maple", R.drawable.canadian_maple,1.79, getString(R.string.canadian_maple)))
        menuList.add(MenuItems("Honey Dip", R.drawable.honey_dip,2.09, getString(R.string.honey_dip)))

        return menuList
    }

    private fun getBagelsList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Plain Bagel", R.drawable.plain_bagel,2.29, getString(R.string.plain_bagel)))
        menuList.add(MenuItems("Four Cheese", R.drawable.four_cheese_bagel,2.49, getString(R.string.four_cheese_bagel)))
        menuList.add(MenuItems("12 Grain Bagel", R.drawable._2_grain_bagel,2.19, getString(R.string.grain_bagel) ))
        menuList.add(MenuItems("Everything Bagel", R.drawable.everything_bagel,1.99, getString(R.string.everything_bagel)))
        menuList.add(MenuItems("Jalapeno Bagel", R.drawable.jalapeno_bagel,2.49, getString(R.string.jalapeno_bagel)))


        return menuList
    }

    private fun getMuffinsList() : ArrayList<MenuItems>{
        val menuList = arrayListOf<MenuItems>()

        menuList.add(MenuItems("Blueberry Muffin", R.drawable.blueberry_muffin,2.29, getString(R.string.blueberry_muffin)))
        menuList.add(MenuItems("Walnut Muffin", R.drawable.walnut_muffin,2.49, getString(R.string.walnut_muffin)))
        menuList.add(MenuItems("Chocolate Muffin", R.drawable.chocolate_muffin,2.19, getString(R.string.chocolate_muffin)))
        menuList.add(MenuItems("Fruit Muffin", R.drawable.fruit_muffin,1.99, getString(R.string.fruit_muffin)))

        return menuList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}