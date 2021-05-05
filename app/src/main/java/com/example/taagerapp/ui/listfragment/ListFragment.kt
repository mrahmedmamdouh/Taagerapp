package com.example.taagerapp.ui.listfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taagerapp.R
import com.example.taagerapp.databinding.FragmentListBinding
import com.example.taagerapp.model.Product
import com.example.taagerapp.ui.adapter.ProductAdapter
import com.example.taagerapp.ui.adapter.ProductCallBack
import com.example.taagerapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ProductCallBack {
    private lateinit var binding: FragmentListBinding
    private val viewModel by activityViewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null){
            viewModel.fetchProducts()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        binding.lifecycleOwner = this

        binding.apply {
           list.layoutManager = LinearLayoutManager(context)
        }

        viewModel.repositoriesLiveData.observe(viewLifecycleOwner, Observer {
            val productAdapter = ProductAdapter(it, viewModel,this)
            binding.list.adapter = productAdapter
        })
    }

    override fun onClick(product: Product) {
        //navigate to details fragment
    }

}