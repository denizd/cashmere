package com.denizd.cashmere.sheet

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.net.toFile
import androidx.lifecycle.Observer
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.SheetAddClothingBinding
import com.denizd.cashmere.fragment.ClosetFragment
import com.denizd.cashmere.model.Clothing
import com.denizd.cashmere.util.TemperatureRating
import com.denizd.cashmere.util.trimmed
import com.denizd.cashmere.util.viewBinding
import java.io.File
import java.io.FileOutputStream

class AddClothingSheet : BaseSheet(R.layout.sheet_add_clothing) {

    private val binding: SheetAddClothingBinding by viewBinding(SheetAddClothingBinding::bind)
    private val temperatureValues: Array<TemperatureRating> = TemperatureRating.values()
    private var selectedCategoryId: Long = -1L
    private var temperatureRating: String = ""
    private var bitmap: Bitmap? = null
    private lateinit var closetFragment: ClosetFragment

    override fun isCancelable(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closetFragment = targetFragment as ClosetFragment

        binding.apply {
            imagePickerButton.setOnClickListener {
                startActivityForResult(
                    Intent().apply {
                        type = "image/*"
                        action = Intent.ACTION_OPEN_DOCUMENT
                    }, 42
                )
            }

            categoryPicker.also { picker ->
                var categoryIds = listOf<Long>()
                closetFragment.getAllCategories().observe(viewLifecycleOwner, Observer { categories ->
                    picker.setAdapter(
                        ArrayAdapter(
                            context,
                            R.layout.dropdown_item,
                            categories.map { c -> c.name }
                        )
                    )
                    categoryIds = categories.map { c -> c.categoryId }.toList()
                })
                picker.setOnItemClickListener { _, _, position, _ ->
                    selectedCategoryId = categoryIds[position]
                }
//                picker.setText(context.getString(temperatureValues[0].stringId), false)
//                temperatureRating = picker.text.toString()
//                picker.setOnItemClickListener { _, _, position, _ ->
//                    temperatureRating = temperatureValues[position].name
//                }
            }
            temperaturePicker.also { picker ->
                picker.setAdapter(
                    ArrayAdapter(
                        context,
                        R.layout.dropdown_item,
                        temperatureValues.map { t -> context.getString(t.stringId)}
                    )
                )
                picker.setText(context.getString(temperatureValues[0].stringId), false)
                temperatureRating = picker.text.toString()
                picker.setOnItemClickListener { _, _, position, _ ->
                    temperatureRating = temperatureValues[position].name
                }
            }

            cancelButton.setOnClickListener { dismiss() }
            addButton.setOnClickListener {
                saveClothing()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (resultCode == Activity.RESULT_OK) {
                loadImage(data.data ?: throw IllegalArgumentException("Uri ${data.data} not valid"))
            }
        }
    }

    private fun loadImage(uri: Uri) {
        context.contentResolver.also { contentResolver ->
            contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            }
            binding.imageView.setImageBitmap(bitmap)
        }
    }

    private fun saveClothing() {
        val imageUri = bitmap?.save() ?: ""
        if (temperatureRating.isEmpty()) return // TODO add more return or error conditions
        with (binding) {
            if (selectedCategoryId == -1L) {
                categoryPicker.error = getString(R.string.no_category_selected)
                return
            }
            closetFragment.insertClothing(
                Clothing(
                    brand = brandTextInput.trimmed(),
                    colour = colourTextInput.trimmed(),
                    size = sizeTextInput.trimmed(),
                    note = noteTextInput.trimmed(),
                    temperatureRating = temperatureRating,
                    imageUri = imageUri,
                    clothingId = 0L,
                    categoryId = selectedCategoryId
                )
            )
        }
        dismiss()
    }

    // TODO do this asynchronously
    private fun Bitmap.save(): String {
        val file = File(context.filesDir, "${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { outputStream ->
            // writes compressed image to output stream
            this.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        Log.d("LL", Uri.fromFile(file).toString())
        return Uri.fromFile(file).toString()
    }
}