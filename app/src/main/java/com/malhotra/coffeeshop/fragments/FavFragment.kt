@file:Suppress("DEPRECATION")

package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.adapters.FavAdapter
import com.malhotra.coffeeshop.adapters.OnRemoveListener
import com.malhotra.coffeeshop.databinding.FragmentFavBinding
import com.malhotra.coffeeshop.modelclass.FavList
import com.malhotra.coffeeshop.viewmodel.FavViewModel

class FavFragment : Fragment() {
    private var _binding : FragmentFavBinding? = null
    private val binding get() = _binding!!
    lateinit var oldList : List<FavList>
    lateinit var adapter: FavAdapter

    val favViewModel : FavViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.show()
        favViewModel.getFavList().observe(viewLifecycleOwner){
            oldList = it as List<FavList>
            if (it.isEmpty()){
                binding.textView7.visibility = View.VISIBLE
                binding.favRv.visibility = View.INVISIBLE
                setHasOptionsMenu(false)
            }else {
                setHasOptionsMenu(true)
                binding.favRv.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = FavAdapter(requireContext(), it, object : OnRemoveListener{
                    override fun onRemoveClicked(list: FavList) {
                        favViewModel.deleteFav(list)
                        binding.favRv.adapter?.notifyDataSetChanged()
                    }
                })
                binding.favRv.adapter = adapter
            }

        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.bar_search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Find your Favourites"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredList(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filteredList(newItem : String?){
        val newList = mutableListOf<FavList>()

        for (i in oldList){
            if (i.title.lowercase().contains(newItem.toString().lowercase())){
                newList.add(i)
            }
        }
        adapter.filterList(newList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}