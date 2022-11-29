package com.malhotra.coffeeshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.databinding.ActivityLoginBinding
import com.malhotra.coffeeshop.modelclass.ProfileInfo
import com.malhotra.coffeeshop.viewmodel.ProfileViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    val profileViewModel : ProfileViewModel by viewModels()
    lateinit var profileItem : ProfileInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

                binding.signButton.setOnClickListener {
                    binding.progressBar.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        insertInfo()
                        finishAffinity()
                    }, 1500)
                }
        }

    private fun insertInfo() {
        if(binding.editName.text.isNotEmpty()
            && binding.editEmail.text.isNotEmpty()
            && binding.editPhone.text.isNotEmpty()){

            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()
            val email = binding.editEmail.text.toString()

            profileItem = ProfileInfo(null, name, phone, email)
            profileViewModel.insertInfo(profileItem)
            startActivity(Intent(this, MainActivity::class.java))

        } else {
            binding.editName.error = "All Fields Required"
            binding.editPhone.error = "All Fields Required"
            binding.editEmail.error = "All Fields Required"
        }


    }


    }