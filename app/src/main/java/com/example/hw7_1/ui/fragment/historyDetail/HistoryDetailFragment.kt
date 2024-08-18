package com.example.hw7_1.ui.fragment.historyDetail

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw7_1.R
import com.example.hw7_1.databinding.FragmentHistoryDetailBinding
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException

class HistoryDetailFragment :
    BaseFragment<FragmentHistoryDetailBinding, HistoryDetailViewModel>(FragmentHistoryDetailBinding::inflate) {

    private val args: HistoryDetailFragmentArgs by navArgs()
    private val historyDetailViewModel: HistoryDetailViewModel by viewModel()
    private var isModified = false
    private var imageUri: Uri? = null
    private var selectedLocation: String? = null

    override val viewModel: HistoryDetailViewModel
        get() = historyDetailViewModel

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    try {
                        val selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            selectedImageUri
                        )
                        binding.imgHistory.setImageBitmap(selectedImageBitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    imageUri = selectedImageUri
                }
            }

        }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.loadHistory(args.historyId)
        viewModel.history.observe(viewLifecycleOwner) { history ->
            history?.let {
                binding.etTitle.setText(it.name)
                binding.etDescription.setText(it.description)
                isModified = it.name != viewModel.history.value?.name ||
                        it.description != viewModel.history.value?.description
                binding.btnSave.visibility =
                    if (args.historyId == 0 || isModified) View.VISIBLE else View.GONE

                if (it.image != null) {
                    val imageFile = File(it.image)
                    if (imageFile.exists()) {
                        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                        binding.imgHistory.setImageBitmap(bitmap)
                    } else {
                        binding.imgHistory.setImageResource(R.drawable.img_history)
                    }
                } else {
                    binding.imgHistory.setImageResource(R.drawable.img_history)
                }

                selectedLocation = it.location
                binding.tvLocation.text = selectedLocation
            }
        }

    }

    override fun setupListener() = with(binding){
        super.setupListener()
        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val historyId = if (args.historyId == 0) 0 else args.historyId

            val historyModel = HistoryEntity(
                id = historyId,
                name = title,
                description = description,
                image = imageUri?.path,
                location = selectedLocation ?: " "
            )
            viewModel.saveHistory(historyModel)
            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        imgHistory.setOnClickListener {
            imageChooser()
        }
        btnChooseHero.setOnClickListener {
            findNavController().navigate(R.id.action_historyDetailFragment_to_heroesFragment)
        }
        btnChooseLocation.setOnClickListener {
            val action =
                HistoryDetailFragmentDirections.actionHistoryDetailFragmentToLocationFragment()
            findNavController().navigate(action)
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()
        etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                isModified = title != (viewModel.history.value?.name ?: "") ||
                        description != (viewModel.history.value?.description ?: "")
                btnSave.visibility =
                    if (title.isNotEmpty() && description.isNotEmpty() && (args.historyId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                isModified = title != (viewModel.history.value?.name ?: "") ||
                        description != (viewModel.history.value?.description ?: "")
                btnSave.visibility =
                    if (title.isNotEmpty() && description.isNotEmpty() && (args.historyId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun imageChooser() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        pickImageLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }
}