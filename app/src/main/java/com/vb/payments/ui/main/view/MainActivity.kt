package com.vb.payments.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vb.payments.databinding.ActivityMainBinding
import com.vb.payments.ui.card.widget.ProgressScreen

class MainActivity : AppCompatActivity(), ProgressProvider {
    override val progress: ProgressScreen
        get() = binding.cardProgress

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
