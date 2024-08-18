package com.example.hw7_1.ui.fragment.history

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw7_1.databinding.FragmentHistoryBinding
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, Any?>(FragmentHistoryBinding::inflate) {

    override val viewModel: HistoryViewModel by viewModel()

    private val historyAdapter = HistoryAdapter { historyItem ->
        onClicked(historyItem)
    }

    private fun onClicked(historyItem: HistoryEntity) {
        val action =
            HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(historyItem.id)
        findNavController().navigate(action)
    }

    override fun setupListener() {
        binding.btnAdd.setOnClickListener {
            val action =
                HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(0)
            findNavController().navigate(action)
        }
    }

    override fun setupRecycler() {
        super.setupRecycler()
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    override fun loadData() {
        viewModel.allData.observe(viewLifecycleOwner) { story ->
            historyAdapter.submitList(story)
        }
    }
}
