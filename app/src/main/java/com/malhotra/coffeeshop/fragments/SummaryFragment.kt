@file:Suppress("DEPRECATION")
package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.adapters.SummaryAdapter
import com.malhotra.coffeeshop.databinding.FragmentSummaryBinding
import com.malhotra.coffeeshop.modelclass.HistoryList
import com.malhotra.coffeeshop.modelclass.SummaryList
import com.malhotra.coffeeshop.viewmodel.HistoryViewModel
import com.malhotra.coffeeshop.viewmodel.SummaryViewModel
import java.util.*

class SummaryFragment : Fragment() {

    val summary by navArgs<SummaryFragmentArgs>()
    private var _binding : FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    val summaryViewModel : SummaryViewModel by viewModels()
    val historyViewModel : HistoryViewModel by viewModels()
    lateinit var orderItem : HistoryList
    lateinit var cartList : List<SummaryList>
    var hList : Int? = null
    var summaryList: SummaryList? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.show()

        historyViewModel.getOrderList().observe(viewLifecycleOwner){
            hList = it.size
        }

        summary.summary.id?.let {
            summaryViewModel.getOrder(it).observe(viewLifecycleOwner){
                cartList = it as List<SummaryList>
                binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCart.adapter = SummaryAdapter(requireContext(), it)
            }
        }

        binding.totalPrice.text = String.format("%.2f",summary.summary.total)
        binding.cartItems.text = getString(R.string.total_order_items, summary.summary.items.toString())
        val itemsTotal = summary.summary.total / 1.33
        binding.itemsTotalPrice.text = String.format("%.2f", itemsTotal)
        binding.deliveryPrice.text = String.format("%.2f", 0.2 * itemsTotal)
        binding.taxPrice.text = String.format("%.2f", 0.13 * itemsTotal)

        binding.checkoutButton.setOnClickListener { view ->
            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.checkout_dialog)

            val yes = bottomSheet.findViewById<TextView>(R.id.yes)
            val no = bottomSheet.findViewById<TextView>(R.id.no)
            bottomSheet.show()

            yes?.setOnClickListener {
                val orderNo = hList!! + 1
                val date = DateFormat.format("MMMM d, yyyy ", Date().time)
                orderItem = HistoryList(orderNo,summary.summary.total,
                    summary.summary.items, date.toString()
                )
                bottomSheet.dismiss()
                historyViewModel.insertOrder(orderItem)
                for (i in cartList){
                    summaryList = (SummaryList(null,orderNo,i.title, i.image, i.price, i.quantity))
                    summaryViewModel.insertOrder(summaryList!!)
                    summaryList = null
                }
                Toast.makeText(requireContext(), "Order Has Been Placed", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_summaryFragment_to_order)
            }

            no?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
