@file:Suppress("DEPRECATION")

package com.malhotra.coffeeshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.adapters.CartAdapter
import com.malhotra.coffeeshop.adapters.OnDeleteListener
import com.malhotra.coffeeshop.databinding.ActivityCartBinding
import com.malhotra.coffeeshop.modelclass.CartList
import com.malhotra.coffeeshop.modelclass.HistoryList
import com.malhotra.coffeeshop.modelclass.SummaryList
import com.malhotra.coffeeshop.viewmodel.CartViewModel
import com.malhotra.coffeeshop.viewmodel.HistoryViewModel
import com.malhotra.coffeeshop.viewmodel.SummaryViewModel
import java.util.*

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    val viewModel : CartViewModel by viewModels()
    val historyViewModel : HistoryViewModel by viewModels()
    lateinit var orderItem : HistoryList
    var total : Double? = null
    var summaryList: SummaryList? = null
    lateinit var cartList : List<CartList>
    var hList : Int? = null
    val summaryViewModel : SummaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        historyViewModel.getOrderList().observe(this){
            hList = it.size
        }

        val callback = object : OnDeleteListener {
            override fun onDeleteClick(list: CartList) {
                viewModel.deleteItem(list)
            }

            override fun onQuantityChange(quantity: Int, id: Int) {
                viewModel.updateQuantity(quantity, id)
            }

        }

        viewModel.getList().observe(this){ list ->
            cartList = list


            if (list.isEmpty()){
                binding.rvCart.visibility = View.INVISIBLE
                binding.cartEmpty.visibility = View.VISIBLE
            }else {
                binding.rvCart.layoutManager = LinearLayoutManager(this)
                binding.rvCart.adapter = CartAdapter(this, list, callback)
            }
            val itemsPrice = viewModel.totalPrice()
            binding.itemsTotalPrice.text = String.format("%.2f", itemsPrice)

            val deliveryPrice = 0.2 * viewModel.totalPrice()
            binding.deliveryPrice.text = String.format("%.2f", deliveryPrice)

            val taxPrice = 0.13 * viewModel.totalPrice()
            binding.taxPrice.text = String.format("%.2f", taxPrice )

            total = itemsPrice + deliveryPrice + taxPrice

            binding.totalPrice.text = String.format("%.2f", total)

            binding.cartItems.text = getString(R.string.total_cart_items, viewModel.totalItems().toString())

        }
        
        binding.checkoutButton.setOnClickListener {

            val bottomSheet = BottomSheetDialog(this)
            bottomSheet.setContentView(R.layout.checkout_dialog)

            val yes = bottomSheet.findViewById<TextView>(R.id.yes)
            val no = bottomSheet.findViewById<TextView>(R.id.no)
            bottomSheet.show()

            yes?.setOnClickListener {
                val orderNo = hList!! + 1
                val date = DateFormat.format("MMMM d, yyyy ", Date().time)

                orderItem = HistoryList(orderNo,total!!.toDouble(),
                    viewModel.totalItems(), date.toString()
                )
                bottomSheet.dismiss()
                historyViewModel.insertOrder(orderItem)
                for (i in cartList){
                    summaryList = (SummaryList(null,orderNo,i.title, i.image, i.price, i.quantity))
                    summaryViewModel.insertOrder(summaryList!!)
                    summaryList = null
                }
                Toast.makeText(this, "Order Has Been Placed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                viewModel.deleteList()
            }

            no?.setOnClickListener {
                bottomSheet.dismiss()
            }

        }

    }

}