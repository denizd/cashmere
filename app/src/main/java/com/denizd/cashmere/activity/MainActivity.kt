package com.denizd.cashmere.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.denizd.cashmere.database.AppRepository
import com.denizd.cashmere.databinding.ActivityMainBinding
import com.denizd.cashmere.util.viewBinding
import com.denizd.cashmere.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // as MainViewModel holds a reference to AppRepository.companion.instance,
        // AppRepository has to be instantiated before MainViewModel
        AppRepository.init(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}