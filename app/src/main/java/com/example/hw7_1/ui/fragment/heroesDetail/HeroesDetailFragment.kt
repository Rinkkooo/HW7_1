package com.example.hw7_1.ui.fragment.heroesDetail

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw7_1.databinding.FragmentHeroesDetailBinding
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HeroesDetailFragment :
    BaseFragment<FragmentHeroesDetailBinding, HeroesDetailViewModel>(FragmentHeroesDetailBinding::inflate) {


    private val args: HeroesDetailFragmentArgs by navArgs()
    private val heroesDetailViewModel: HeroesDetailViewModel by viewModel()
    private var isModified = false

    override val viewModel: HeroesDetailViewModel
        get() = heroesDetailViewModel

    override fun setupObserver() = with(binding) {
        super.setupObserver()
        viewModel.loadHistory(args.heroesId)
        viewModel.hero.observe(viewLifecycleOwner) { hero ->
            hero?.let {
                etName.setText(it.name)
                isModified = it.name != viewModel.hero.value?.name
                btnSave.visibility =
                    if (args.heroesId == 0 || isModified) View.VISIBLE else View.GONE
            }
        }
    }

    override fun setupListener() = with(binding) {
        super.setupListener()
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val heroesId = if (args.heroesId == 0) 0 else args.heroesId

            val heroModel = HeroesEntity(
                id = heroesId,
                name = name
            )
            viewModel.saveHistory(heroModel)
            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = etName.text.toString()
                isModified = name != (viewModel.hero.value?.name ?: "")
                btnSave.visibility =
                    if (name.isNotEmpty() && (args.heroesId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}