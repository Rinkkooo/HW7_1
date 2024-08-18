package com.example.hw7_1.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<binding : ViewBinding, VM>(private val bindingInflater: (LayoutInflater) -> binding) : Fragment() {

    private var _binding : binding ?= null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupRecycler()
        setupListener()
        initViews()
        loadData()
    }

    protected open fun setupListener(){}
    protected open fun setupObserver(){}
    protected open fun setupRecycler(){}
    protected open fun initViews(){}
    protected open fun loadData(){}

    protected fun showAlertDialog(
        title: String,
        message: String,
        onPositiveClick: () -> Unit
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Yes") { _, _ -> onPositiveClick() }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}