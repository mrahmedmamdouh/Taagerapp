package com.example.taagerapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taagerapp.databinding.ItemListBinding
import com.example.taagerapp.model.Product
import com.example.taagerapp.ui.viewmodel.MainViewModel

class ProductAdapter(private val products: List<Product>,
                     private val _viewmodel : MainViewModel,
                     private val callBack: ProductCallBack
) :
    RecyclerView.Adapter<ProductAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = products[position]
        holder.bind(currentItem)
    }

    inner class VH(private var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.apply {
                product = item
                viewmodel = _viewmodel
                callback = callBack
            }
        }
    }
}