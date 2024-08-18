package com.example.hw7_1.ui.fragment.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_1.databinding.ItemHeroBinding
import com.example.hw7_1.model.entity.HeroesEntity

class HeroesAdapter(
    private val onHeroClick: (HeroesEntity) -> Unit
) : ListAdapter<HeroesEntity, HeroesAdapter.HeroesViewHolder>(HeroesDiffCallback()) {

    inner class HeroesViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val hero = getItem(adapterPosition)
                onHeroClick(hero)
            }
        }

        fun bind(hero: HeroesEntity) {
            val fullName = hero.name
            binding.checkBoxHero.text = fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HeroesDiffCallback : DiffUtil.ItemCallback<HeroesEntity>() {
    override fun areItemsTheSame(oldItem: HeroesEntity, newItem: HeroesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HeroesEntity, newItem: HeroesEntity): Boolean {
        return oldItem == newItem
    }
}