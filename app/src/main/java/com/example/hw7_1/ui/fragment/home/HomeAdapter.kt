package com.example.hw7_1.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_1.databinding.ItemListsBinding
import com.example.hw7_1.model.entity.HomeModel


class HomeAdapter(
    private val onItemClick: (HomeModel) -> Unit
) : ListAdapter<HomeModel, HomeAdapter.ListViewHolder>(ListsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class ListViewHolder(private val binding: ItemListsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeModel, onItemClick: (HomeModel) -> Unit) {
            binding.tvTitle.text = item.title
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    class ListsDiffCallback : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem == newItem
        }
    }
}