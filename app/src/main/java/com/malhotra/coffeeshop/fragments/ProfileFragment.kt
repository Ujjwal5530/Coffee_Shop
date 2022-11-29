package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.databinding.FragmentOrderBinding
import com.malhotra.coffeeshop.databinding.FragmentProfileBinding
import com.malhotra.coffeeshop.modelclass.ProfileInfo
import com.malhotra.coffeeshop.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel : ProfileViewModel by viewModels()
    private lateinit var name : String
    private lateinit var phone : String
    private lateinit var email : String
    private var id : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.show()

        profileViewModel.getInfo(1).observe(viewLifecycleOwner) {
            name = it.name.toString()
            phone = it.phone.toString()
            email = it.email.toString()
            id = it.id

            binding.name.text = name
            binding.phone.text = phone
            binding.email.text = email
        }

        binding.editAccount.setOnClickListener {
            val profileItem = ProfileInfo(id, name, phone, email)
            val action = ProfileFragmentDirections.actionProfileToEditFragment(profileItem)
            Navigation.findNavController(it).navigate(action)
        }


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}