package com.example.hw7_1.ui.fragment.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_1.databinding.FragmentLocationBinding
import com.example.hw7_1.databinding.ItemLocationBinding
import com.example.hw7_1.model.entity.LocationEntity

class LocationAdapter(private val onLocationClick: (LocationEntity) -> Unit) :
    ListAdapter<LocationEntity, LocationAdapter.LocationViewHolder>(LocationDiffCallback()) {

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val location = getItem(adapterPosition)
                onLocationClick(location)
            }
        }

        fun bind(location: LocationEntity) {
            binding.checkBoxLocation.text = location.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding =
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LocationDiffCallback() : DiffUtil.ItemCallback<LocationEntity>() {
    override fun areItemsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
        return oldItem == newItem
    }

}