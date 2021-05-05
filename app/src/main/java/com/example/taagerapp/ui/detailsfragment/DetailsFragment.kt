package com.example.taagerapp.ui.detailsfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.taagerapp.R
import com.example.taagerapp.databinding.FragmentDetailsBinding
import com.example.taagerapp.model.Product
import com.example.taagerapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        val _product: Product = requireArguments().getParcelable("product")!!

        binding.apply {
            viewmodel = viewModel
            product = _product
        }
    }
}