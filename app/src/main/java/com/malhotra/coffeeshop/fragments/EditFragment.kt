package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.malhotra.coffeeshop.R
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.databinding.FragmentEditBinding
import com.malhotra.coffeeshop.databinding.FragmentFavBinding
import com.malhotra.coffeeshop.modelclass.ProfileInfo
import com.malhotra.coffeeshop.viewmodel.ProfileViewModel

class EditFragment : Fragment() {

    val info by navArgs<EditFragmentArgs>()
    private var _binding : FragmentEditBinding? = null
    private val binding get() = _binding!!
    val profileViewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.show()

        binding.editName.setText(info.profile.name)
        binding.editPhone.setText(info.profile.phone)
        binding.editEmail.setText(info.profile.email)

        binding.signButton.setOnClickListener {

            Toast.makeText(requireContext(), "Saved Details", Toast.LENGTH_SHORT).show()
            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()
            val email = binding.editEmail.text.toString()

            profileViewModel.updateInfo(ProfileInfo(info.profile.id, name, phone, email))
            Navigation.findNavController(it).navigate(R.id.action_editFragment_to_profile)
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

