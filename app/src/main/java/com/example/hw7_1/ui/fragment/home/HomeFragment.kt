package com.example.hw7_1.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw7_1.R
import com.example.hw7_1.databinding.FragmentHomeBinding
import com.example.hw7_1.model.entity.HomeModel

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var listsAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        val items = listOf(
            HomeModel("History", R.id.action_homeFragment_to_historyFragment),
            HomeModel("Heroes", R.id.action_homeFragment_to_heroesFragment),
            HomeModel("Location", R.id.action_homeFragment_to_locationFragment)
        )

        listsAdapter = HomeAdapter { listModel ->
            findNavController().navigate(listModel.actionId)
        }

        rvLists.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listsAdapter
        }

        listsAdapter.submitList(items)
    }
}