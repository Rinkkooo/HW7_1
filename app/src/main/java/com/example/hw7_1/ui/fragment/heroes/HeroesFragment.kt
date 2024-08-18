package com.example.hw7_1.ui.fragment.heroes

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw7_1.databinding.FragmentHeroesBinding
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesFragment :
    BaseFragment<FragmentHeroesBinding, HeroesViewModel>(FragmentHeroesBinding::inflate) {

    private val heroesAdapter by lazy {
        HeroesAdapter { hero ->
            onHeroClicked(hero)
        }
    }

    override val viewModel: HeroesViewModel by viewModel()

    override fun setupListener() {
        super.setupListener()
        with(binding) {
            btnAdd.setOnClickListener {
                val action = HeroesFragmentDirections.actionHeroesFragmentToHeroesDetailFragment(0)
                findNavController().navigate(action)
            }
            imgClearHeroes.setOnClickListener {
                showAlertDialog(
                    title = "Clear History",
                    message = "Do you really want to clear the history?",
                    onPositiveClick = {
                        viewModel.clearAll()
                    }
                )
            }
            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun loadData() {
        super.loadData()
        viewModel.allData.observe(viewLifecycleOwner) { heroes ->
            heroesAdapter.submitList(heroes)
        }
    }

    override fun setupRecycler() {
        super.setupRecycler()
        binding.rvHeroes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = heroesAdapter
        }
    }

    private fun onHeroClicked(hero: HeroesEntity) {
        val action = HeroesFragmentDirections.actionHeroesFragmentToHeroesDetailFragment(hero.id)
        findNavController().navigate(action)
    }
}