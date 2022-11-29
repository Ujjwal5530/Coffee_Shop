package com.malhotra.coffeeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.malhotra.coffeeshop.activity.MainActivity
import com.malhotra.coffeeshop.databinding.FragmentDescriptionBinding
import com.malhotra.coffeeshop.modelclass.CartList
import com.malhotra.coffeeshop.modelclass.FavList
import com.malhotra.coffeeshop.viewmodel.CartViewModel
import com.malhotra.coffeeshop.viewmodel.FavViewModel

class DescriptionFragment : Fragment() {

    val menuLists by navArgs<DescriptionFragmentArgs>()


    private var _binding : FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    lateinit var cartItem : CartList
    lateinit var favItem : FavList
    val viewModel : CartViewModel by viewModels()
    val favViewModel : FavViewModel by viewModels()
    lateinit var cartList: List<CartList>
    lateinit var favList: List<FavList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.show()

        val desTitle = menuLists.menu.title
        val desPrice = menuLists.menu.price
        val desImage = menuLists.menu.image
        val des = menuLists.menu.description

        binding.desTitle.text = desTitle
        binding.desPrice.text = "$ ${desPrice}"
        binding.desImage.setImageResource(desImage)
        binding.des.text = des

        var quantity = menuLists.menu.quantity

        binding.quantity.text = quantity.toString()

        binding.plus.setOnClickListener {
            while (quantity < 9) {
                quantity++
                binding.quantity.text = quantity.toString()
                break
            }
        }

        binding.minus.setOnClickListener {
            while (quantity > 1) {
                quantity--
                binding.quantity.text = quantity.toString()
                break
            }
        }
        viewModel.getList().observe(viewLifecycleOwner) {
            cartList = it
        }

        binding.cartButton.setOnClickListener {

            cartItem = CartList(desImage, desTitle, desImage, desPrice, quantity)

                if (cartList.contains(cartItem)) {
                    Toast.makeText(
                        requireContext(),
                        "${cartItem.title} is already in your Cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else {
                    Toast.makeText(
                        requireContext(),
                        "$desTitle added to your Cart",
                        Toast.LENGTH_SHORT
                    ).show()

                    viewModel.insertList(cartItem)
                }
            }

        favViewModel.getFavList().observe(viewLifecycleOwner){
            favList = it
        }

        binding.favBtn.setOnClickListener {

            favItem = FavList(desImage, desTitle.toString(), desImage, desPrice, des.toString())

                if (favList.contains(favItem)){
                    Toast.makeText(requireContext(), "${favItem.title} is already in your Favourites", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "$desTitle added to your Favourites", Toast.LENGTH_SHORT)
                        .show()
                    favViewModel.insertFav(favItem)
                }
            }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
