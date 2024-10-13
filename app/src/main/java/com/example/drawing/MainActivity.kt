package com.example.drawing

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.drawing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TestView.Listener {
    lateinit var binding : ActivityMainBinding
    private val menuList = listOf(
        "Edit", "Delete", "Menu",
        "Job", "Balanc", "Exit"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.figure.listener = this
    }

    override fun onClick(index: Int) {
        binding.textView.text = menuList[index]
    }
}