package com.malhotra.coffeeshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.ActivityMainBinding
import com.malhotra.coffeeshop.modelclass.CartList
import com.malhotra.coffeeshop.viewmodel.CartViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    val viewModel : CartViewModel by viewModels()
    var cartList : List<CartList>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)

        viewModel.getList().observe(this){ cartList = it}

        binding.cartBtn.setOnClickListener {
            if (cartList?.isEmpty() == true){
                Toast.makeText(this,
                    "Cart is Empty, Add something to your Cart", Toast.LENGTH_SHORT)
                    .show()
            }else {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }

        val bottomBar = binding.bottomNavigationView

        NavigationUI.setupWithNavController(bottomBar, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}