package com.example.hw7_1.ui.fragment.history


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_1.databinding.ItemHistoryBinding
import com.example.hw7_1.model.entity.HistoryEntity

class HistoryAdapter(private val onItemClick: (HistoryEntity) -> Unit) :
    ListAdapter<HistoryEntity, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            binding.tvTitle.text = history.name
            binding.tvDescription.text = history.description
            binding.root.setOnClickListener {
                onItemClick(history)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryEntity>() {
    override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
        return oldItem == newItem
    }

}