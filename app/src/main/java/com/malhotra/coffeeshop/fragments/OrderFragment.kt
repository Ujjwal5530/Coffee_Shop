@file:Suppress("DEPRECATION")

package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.adapters.OrderAdapter
import com.malhotra.coffeeshop.databinding.FragmentOrderBinding
import com.malhotra.coffeeshop.modelclass.HistoryList
import com.malhotra.coffeeshop.viewmodel.HistoryViewModel

class OrderFragment : Fragment() {

    private var _binding : FragmentOrderBinding? = null
    private val binding get() = _binding!!
    val historyViewModel : HistoryViewModel by viewModels()
    lateinit var adapter : OrderAdapter
    lateinit var oldList: List<HistoryList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.show()
        historyViewModel.getOrderList().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                oldList = it as List<HistoryList>
                binding.historyRv.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = OrderAdapter(requireContext(), it)
                binding.historyRv.adapter = adapter
                setHasOptionsMenu(true)
            } else {
                binding.historyText.visibility = View.VISIBLE
                binding.historyRv.visibility = View.INVISIBLE
                setHasOptionsMenu(false)
            }

        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.bar_search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Find by date or order no..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newOrderList(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun newOrderList(newText : String?){
        val newList = mutableListOf<HistoryList>()

        for (i in oldList){
            if (i.date!!.lowercase().contains(newText.toString().lowercase()) || i.id.toString().contains(newText.toString())){
                newList.add(i)
            }
        }
        if (newList.isEmpty()) {
            binding.historyText.text = "Order not Found"
            binding.historyText.visibility = View.VISIBLE
        } else {
            binding.historyText.visibility = View.INVISIBLE
            adapter.filterList(newList)
        }
        adapter.filterList(newList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}